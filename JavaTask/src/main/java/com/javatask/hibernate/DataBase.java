package com.javatask.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

//
//
public class DataBase {
	protected SessionFactory sessionFactory;
	
	public void init() {
    	final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
    	        .configure() // configures settings from hibernate.cfg.xml
    	        .build();
    	try {
    	    sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    	}
    	catch (Exception ex) {
    	    StandardServiceRegistryBuilder.destroy( registry );
    	}
    }
	
	public void exit() {
    	sessionFactory.close();
    }
	
	//
    //	a.	Търсене на лице по име и визуализиране на списък с намерени резултати – 
	//		търсенето по име не трябва да зависи от начина на изписване в базата данни на името – Case Insensitive search; 
	@SuppressWarnings("unchecked")
	public JSONArray search( String term ) {
		// select, obtains the session
    	Session session = sessionFactory.openSession();
		
    	JSONObject json;
        JSONArray jarray = new JSONArray();
        
    	// mails, addresses
        List<Mails> mails;
        List<Addresses> addresses;
        // queries all people
        List<People> list = session.createQuery( "from People where full_name like '%" + term + "%'" ).getResultList();
        for( People people : list ) {
            mails = session.createQuery( "from Mails where t_people_id = " + people.getId()).getResultList();
            addresses = session.createQuery( "from Addresses where t_people_id = " + people.getId()).getResultList();
            
    		//
            json = new JSONObject();
            
            json.put("id", people.getId());
            json.put("full_name", people.getFullName());
            json.put("pin", people.getPin());
            //
            Mails mail = mails.size() > 0 ? mails.get(0) : null;
            json.put( "email", mail != null ? mail.getEmail() : null );
            
            Addresses addr = addresses.size() > 0 ? addresses.get(0) : null;
            json.put("address", addr != null ? addr.getAddress() : null );
    		
            //
    		jarray.add(json);
        }
        
        //
        session.close();
        return jarray;
	}
    
    //
    //	b.	Създаване на запис за лице в базата данни – ограниченията да бъдат дефинирани на ниво web услуга
	//
	public void create( String full_name, String pin, String email, String address ) throws Exception {
		
		//
		People people = new People( full_name, pin );
		Mails mails = new Mails( "WORK", email );
		Addresses addresses = new Addresses( "HOME", address );
		
		//
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        
        long id = (Long)session.save( people );
        
        mails.setPeopleId(id);
        session.save( mails );
        
        addresses.setPeopleId(id);
        session.save( addresses );
        
        session.getTransaction().commit();        
        session.close();
    }
	
	//
    //	c.	Редактиране на съществуващ запис
	//
	@SuppressWarnings("unchecked")
	public void modify( long id, String full_name, String pin, String email, String address ) throws Exception { 
		//
		People people = full_name != null ? new People( id, full_name, pin ) : null;
		Mails mails = email != null ? new Mails( id, null, email ) : null;
		Addresses addresses = address != null ? new Addresses( id, null, address) : null;
		
		//
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		//
		if( people != null ) { 
			session.update( people ); 
		}
		
		if( email != null ) {
			List<Mails> list = session.createQuery( "from Mails where t_people_id = " + id ).getResultList();
			for( Mails mail: list ) { session.delete( mail ); }
			session.save( mails );
		}
		
		if( addresses != null ) {
			List<Addresses> list = session.createQuery( "from Addresses where t_people_id = " + id ).getResultList();
			for( Addresses addr: list ) { session.delete( addr ); }
			session.save( addresses );
		}
		
        //
        session.getTransaction().commit();
        session.close();
    }
	
	
	//
    //	d.	Изтриване на избран запис
	//
    @SuppressWarnings("unchecked")
	public void delete( long id ) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        //
        List<Addresses> addresses = session.createQuery( "from Addresses where t_people_id = " + id).getResultList();
		for( Addresses addr: addresses ) { session.delete( addr ); }
		
        List<Mails> mails = session.createQuery( "from Mails where t_people_id = " + id ).getResultList();
		for( Mails mail: mails ) { session.delete( mail ); }
		//
		session.delete( new People( id ) );
		
        session.getTransaction().commit();
        session.close();
    }
	
    
    // List 
	@SuppressWarnings("unchecked")
	public JSONArray list() {
		// select, obtains the session
    	Session session = sessionFactory.openSession();
		
    	JSONObject json;
        JSONArray jarray = new JSONArray();
        
    	// mails, addresses
        List<Mails> mails;
        List<Addresses> addresses;
        // queries all people
        List<People> list = session.createQuery( "from People" ).getResultList();
        for( People people : list ) {
            mails = session.createQuery( "from Mails where t_people_id = " + people.getId()).getResultList();
            addresses = session.createQuery( "from Addresses where t_people_id = " + people.getId()).getResultList();
            
    		//
            json = new JSONObject();
            
            json.put("id", people.getId());
            json.put("full_name", people.getFullName());
            json.put("pin", people.getPin());
            //
            Mails mail = mails.size() > 0 ? mails.get(0) : null;
            json.put( "email", mail != null ? mail.getEmail() : null );
            
            Addresses addr = addresses.size() > 0 ? addresses.get(0) : null;
            json.put("address", addr != null ? addr.getAddress() : null );
    		
            //
    		jarray.add(json);
        }
        
        //
        session.close();
        return jarray;
	}
	
    @SuppressWarnings("unchecked")
    public JSONObject get( long id ) {
        // select, obtains the session
    	Session session = sessionFactory.openSession();
    	
    	JSONObject json = null;
    	//
        People people = session.get( People.class, id );	// ID
        if( people != null ) {
        	// mails
			List<Mails> mails = session.createQuery( "from Mails where t_people_id = " + people.getId()).getResultList();	
			// addresses
        	List<Addresses> addresses = session.createQuery( "from Addresses where t_people_id = " + people.getId()).getResultList();	
		
        	json = new JSONObject();
            
            json.put("id", people.getId());
            json.put("full_name", people.getFullName());
            json.put("pin", people.getPin());
            //
            Mails mail = mails.size() > 0 ? mails.get(0) : null;
            json.put( "email", mail != null ? mail.getEmail() : null );
            
            Addresses addr = addresses.size() > 0 ? addresses.get(0) : null;
            json.put("address", addr != null ? addr.getAddress() : null );
        }
        
		//
        session.close();
        return json;
    }
    
    
    @SuppressWarnings("unchecked")
	public void mailsList() {
    	Session session = sessionFactory.openSession();
		
		// queries all mails
        List<Mails> list = session.createQuery( "from Mails" ).getResultList();
        
        String str;
        People p = null;
        
        for( Mails mail : list ) {
        	// p = mail.getPeople();
        	str = "(T_MAILS)ID: " + mail.getId() + ", Type/Email: " + mail.getEmailType() + "/" + mail.getEmail();
        	if( p != null ) { 
        		str += "ID: " + p.getId() + ", Full Name: " + p.getFullName() + ", PIN: " + p.getPin();
            }
        	
            //
            System.out.println( str );
        }
        //
        session.close();
	}
}
