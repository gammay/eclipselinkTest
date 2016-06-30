package com.mindtree.eltest.main;

import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.mindtree.eltest.entities.User;
import com.mindtree.eltest.entities.UserAddress;

public class ELTestMain {
	
	private static final String PERSISTENCE_UNIT_NAME = "ELTest";
	  private static EntityManagerFactory factory;


	public static void main(String[] args) {
		
		
		User user = new User(1, "user1-edited");
		UserAddress ua = new UserAddress();
		ua.setAddressLine1("line1");
		ua.setAddressLine2("line2");
		//ua.setTenant(new Tenant(1, ""));
		user.setUserAddressList(Arrays.asList(new UserAddress[]{ua}));
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	    EntityManager em = factory.createEntityManager();
	    em.getTransaction().begin();
	    em.merge(user);
	    em.getTransaction().commit();
	    em.close();
	    

	}

}
