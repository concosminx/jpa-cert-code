package com.nimsoc.jpa;

import com.nimsoc.jpa.entities.Employee;
import com.nimsoc.jpa.entities.Product;
import com.nimsoc.jpa.entities.Student;
import com.nimsoc.jpa.entities.keys.StudentKey;
import com.nimsoc.jpa.persistence.CustomPersistenceUnitInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;

public class Main {
  public static void main(String[] args) {

    Map<String, String> props = new HashMap<>();
    props.put("hibernate.show_sql", "true");
    //https://vladmihalcea.com/hibernate-hbm2ddl-auto-schema/
    //none, create-only, drop, create, create-drop, validate, update
    props.put("hibernate.hbm2ddl.auto", "create");


    EntityManagerFactory emf = new HibernatePersistenceProvider()
            .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(), props);

//    EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
    EntityManager em = emf.createEntityManager(); //the context

    try {
      em.getTransaction().begin();


      //em.persist();     -> Adding an entity in the context
      //em.find();        -> Finds by PK. Get from DB and add it to the context if it doesn't already exists
      //em.remove();      -> Marking entity for removal
      //em.merge();       -> Merges and entity from outside the context to the context (the instance should exist in the db)
      //em.refresh();     -> Mirrors the context from the database
      //em.detach();      -> Taking the entity out of the context (only)
      //em.getReference() -> Similar with find, will not issue the query to the DB (only after you will do something with the reference, the query will be issued)

      var e1 = new Employee();
      e1.setName("John Doe");
      e1.setAddress("Str. Victoriei");

      em.persist(e1);

      var p1 = new Product();
      p1.setCode("KA");
      p1.setNumber(2L);
      p1.setColor("red");

      em.persist(p1);

      var s1 = new Student();
      var s1Key = new StudentKey("STD", 2L);
      s1.setKey(s1Key);
      s1.setName("John Doe");

      em.persist(s1);


      em.getTransaction().commit();
    } finally {
      em.close();
    }


  }
}