package com.dci.hibernate.oneToMany;

import java.io.File;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

//make sure to import the entities from correct package 
//com.dci.hibernate.oneToMany.foreignKeyAsso or com.dci.hibernate.oneToMany.joinTable
import com.dci.hibernate.oneToMany.foreignKeyAsso.AccountEntity;
import com.dci.hibernate.oneToMany.foreignKeyAsso.EmployeeEntity;


public class HibernateUtil {
	private static final SessionFactory sessionFactory = buildSessionFactory();
	 
    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            
            Configuration configuration = new Configuration();
            configuration.configure("/hibernate.cfg-one-to-many.xml");
            configuration.addAnnotatedClass(EmployeeEntity.class);
            configuration.addAnnotatedClass(AccountEntity.class);
            ServiceRegistry srvcReg = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            return configuration.buildSessionFactory(srvcReg);
 
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
 
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
 
    public static void shutdown() {
    	// Close caches and connection pools
    	getSessionFactory().close();
    }
}
