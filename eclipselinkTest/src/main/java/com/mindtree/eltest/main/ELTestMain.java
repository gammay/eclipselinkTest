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
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		
		//transaction begins
		em.getTransaction().begin();
		
		//getting existing user
		User user = em.find(User.class, 1);
		
		//adding address
		UserAddress ua = new UserAddress();
		ua.setAddressLine1("line1");
		ua.setAddressLine2("line2");
		// ua.setTenant(new Tenant(1, ""));
		user.setUserAddressList(Arrays.asList(new UserAddress[] { ua }));

		//adding getCurrentChange manually
		final JpaEntityManager jpaEntityManager = (JpaEntityManager) em.getDelegate();
		jpaEntityManager.getUnitOfWork().getCurrentChanges();
		
		//final merge
		em.merge(user);
		System.out.println("..user before flush..." + user);
		em.flush();
		em.close();

	}

}
