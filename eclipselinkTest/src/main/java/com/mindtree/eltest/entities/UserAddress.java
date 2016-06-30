package com.mindtree.eltest.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: UserAddress
 *
 */
@Entity
public class UserAddress implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String addressLine1;
	
	@Column
	private String addressLine2;
	
	@ManyToOne
	@JoinColumn(name="Tenant",referencedColumnName="Id")
	private Tenant tenant;

	public UserAddress() {
		super();
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the addressLine1
	 */
	public String getAddressLine1() {
		return addressLine1;
	}

	/**
	 * @param addressLine1 the addressLine1 to set
	 */
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	/**
	 * @return the addressLine2
	 */
	public String getAddressLine2() {
		return addressLine2;
	}

	/**
	 * @param addressLine2 the addressLine2 to set
	 */
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	/**
	 * @return the tenant
	 */
	public Tenant getTenant() {
		return tenant;
	}

	/**
	 * @param tenant the tenant to set
	 */
	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserAddress [id=" + id + ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2
				+ ", tenant=" + tenant + "]";
	}
	
	@PrePersist
	public void UAPrePersist(){
		System.out.println("-> userAddress.prepersist, tenant="+tenant);
		this.tenant=new Tenant(1, "tenant1");
		System.out.println("<- userAddress.prepersist, tenant="+tenant+"....userAddress="+this.toString());
	}
   
}
