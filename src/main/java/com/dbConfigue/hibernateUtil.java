package com.dbConfigue;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import com.bankmanagement.model.Account;

public class hibernateUtil {

	public static SessionFactory getSessionFactory() {
		// Db properties: Driver ,URl, User, Pass
		Map <String ,String>map=new HashMap();
		map.put(Environment.DRIVER,"com.mysql.cj.jdbc.Driver");
		map.put(Environment.URL,"jdbc:mysql://localhost:3306/banksbi");
		map.put(Environment.USER,"root");
		map.put(Environment.PASS, "Root@1996");
		
		//Hibernate properties: HBM2DDL_AUTO, DIALECT, SHOW_SQL, FORMAT_SQL
		
		map.put(Environment.HBM2DDL_AUTO,"update");
		map.put(Environment.DIALECT,"org.hibernate.dialect.MySQL5Dialect");
		map.put(Environment.SHOW_SQL, "false");
		map.put(Environment.FORMAT_SQL, "false");
		
		
		StandardServiceRegistry registry=new StandardServiceRegistryBuilder().applySettings(map).build();
		
		MetadataSources mds= new MetadataSources(registry);
		
		mds.addAnnotatedClass(Account.class);
		Metadata md = mds.getMetadataBuilder().build();
		SessionFactory sf = md.buildSessionFactory();
		

		return sf;
	}

}
