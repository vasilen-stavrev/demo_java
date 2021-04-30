package com.javatask.hibernate;

import javax.persistence.*;

@Entity
@Table(name = "T_ADDRESSES")
public class Addresses {
	
    @Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
    
	@Column(name = "T_PEOPLE_ID")
	private long t_people_id;
	
	@Column(name = "ADDR_TYPE")
    private String addr_type;
	
	@Column(name = "ADDR_INFO")
    private String addr_info;
	
	// @OneToOne(cascade = CascadeType.ALL)
    // @JoinColumn(name = "T_PEOPLE_ID")
    // private People people;
    
    public Addresses() {
    }
    
    public Addresses(String type, String address) {	
    	this.setAddressType( type );
    	this.setAddress( address );
    }
    
    public Addresses(Long peopleId, String type, String address) {	
    	this.setPeopleId( peopleId );
    	this.setAddressType( type );
    	this.setAddress( address );
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
	
	
	public String getAddressType() {
		return addr_type;
	}
	public void setAddressType(String addr_type) {
		if( addr_type == null || addr_type.trim().length() == 0 ) { 
			addr_type = "HOME"; 
		}
		this.addr_type = addr_type.trim().toUpperCase();
	}
	
	
	public String getAddress() {
		return addr_info;
	}
	public void setAddress(String addr_info) {
		this.addr_info = addr_info;
	}
	
	/*
	public People getPeople() {
	    return people;
	}
	public void setPeople(People people) {
	    this.people = people;
	}*/
}
