package com.javatask.hibernate;

import java.util.regex.Pattern;

import javax.persistence.*;


@Entity
@Table(name = "T_MAILS")
public class Mails {
	
    @Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "T_PEOPLE_ID")
    private long t_people_id;
	
	@Column(name = "EMAIL_TYPE")
    private String email_type;
	
	@Column(name = "EMAIL")
    private String email;
	
	// @ManyToOne
    // @JoinColumn(name = "T_PEOPLE_ID")
    // private People people;
    
    public Mails() {
    }
    
    public Mails( String email_type, String email ) throws Exception {
    	this.setEmailType( email_type );
    	this.setEmail( email );
    }
    
    public Mails( Long peopleId, String email_type, String email ) throws Exception {
    	this.setEmailType( email_type );
    	this.setEmail( email );
    	if( peopleId != null ) { this.t_people_id = peopleId; }
    }
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public long getPeopleId() {
		return t_people_id;
	}
	public void setPeopleId(long id) {
		this.t_people_id = id;
	}
	
	public String getEmailType() {
		return email_type;
	}
	public void setEmailType(String email_type) {
		if( email_type == null || email_type.trim().length() == 0 ) { 
			email_type = "PRSNL"; 
		}
		this.email_type =  email_type.trim().toUpperCase();
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail( String email ) throws Exception {
		if( email == null ) { return; }
		Pattern pattern = Pattern.compile( 
				"^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", 
					Pattern.CASE_INSENSITIVE );
	    if( ! pattern.matcher( email ).find()) { 
	    	throw new Exception("Error email"); 
	    }
		this.email = email;
	}
	
	/*
	public People getPeople() {
	    return people;
	}
	public void setPeople(People people) {
	    this.people = people;
	}
	*/
}
