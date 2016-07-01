package com.mindtree.eltest.main;

import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.eclipse.persistence.jpa.JpaEntityManager;

import com.mindtree.eltest.entities.User;
import com.mindtree.eltest.entities.UserAddress;

public class ELTestMain {
	
	private static final String PERSISTENCE_UNIT_NAME = "ELTest";
	  private static EntityManagerFactory factory;


	public static void main(String[] args) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		
		// Transaction begin
		em.getTransaction().begin();
		
		// Getting existing user
		User user = em.find(User.class, 1);
		System.out.println("..got user ..." + user);
		
		// Adding address
		UserAddress ua = new UserAddress();
		ua.setAddressLine1("line1");
		ua.setAddressLine2("line2");
		// ua.setTenant(new Tenant(1, ""));
		user.setUserAddressList(Arrays.asList(new UserAddress[] { ua }));

		// Temp fix - uncomment below lines
		// final JpaEntityManager jpaEntityManager = (JpaEntityManager) em.getDelegate();
		// jpaEntityManager.getUnitOfWork().getCurrentChanges();
		
		// final merge
		em.merge(user);
		em.flush();
        System.out.println("..user after flush..." + user);

        // Transaction end
        em.getTransaction().commit();
        
        // Getting user again
        user = em.find(User.class, 1);
        System.out.println("..got user ..." + user);
        
        em.close();

	}

}
