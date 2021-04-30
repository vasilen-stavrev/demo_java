package com.javatask.hibernate;

import javax.persistence.*;


@Entity
@Table(name = "T_PEOPLE")
public class People {
    
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "FULL_NAME")
    private String full_name;
	
	@Column(name = "PIN")
    private String pin;
    
	// @OneToMany(mappedBy = "addresses")
    // private Addresses address;
	// @OneToMany(mappedBy = "mails")
    // private Mails mails;
    
    //
    public People() {
    }
    
    public People( long id ) {
    	this.id = id;
    }
    
    public People( String full_name, String pin ) throws Exception {
    	this.setFullName(full_name);
    	this.setPin(pin);
    }
    
    public People( long id, String full_name, String pin ) throws Exception {
    	this.setId(id);
    	this.setFullName(full_name);
    	this.setPin(pin);
    }
    

	public long getId() {
		return id;
	}
    
	public void setId(long id) {
		this.id = id;
	}
	
	public String getFullName() {
		return full_name;
	}

	public void setFullName( String full_name ) throws Exception {
		// Full name: not null and "[a-zA-Zà-ÿÀ-ß -]+"
    	if(full_name == null || !full_name.matches("[a-zA-Zà-ÿÀ-ß -]+")) {
    		throw new Exception("Error: full name. The name can include Latin or Cyrillic letters, dashes or spaces.");
    	}
		this.full_name = full_name;
	}
	
	public String getPin() {
		return pin;
	}
	
	public void setPin(String pin) throws Exception {
		// PIN: "[0-9]{10}" or null
    	if( pin != null && !pin.matches("[0-9]{10}")) {
    		throw new Exception("Error: PIN. It can only include numbers and be 10 characters long.");
    	}
		this.pin = pin;
	}
	
	/*
    public Mails getMail() {
        return mails;
    }
	public void setMail( Mails mails ) {
		this.mails = mails;
	}
	
	
	public Addresses getAddress() {
		return address;
	}
	public void setAddress( Addresses address ) {
		this.address = address;
	} */
}
