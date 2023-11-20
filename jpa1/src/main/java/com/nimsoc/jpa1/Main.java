package com.nimsoc.jpa1;

import com.nimsoc.jpa1.entities.Employee;
import com.nimsoc.jpa1.entities.Product;
import com.nimsoc.jpa1.persistence.CustomPersistenceUnitInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;

public class Main {
  public static void main(String[] args) {

    EntityManagerFactory emf = new HibernatePersistenceProvider()
            .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(), new HashMap<>());

//    EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
    EntityManager em = emf.createEntityManager(); //the context

    try {
      em.getTransaction().begin();

      //add a new product
      //Product p = new Product();
      //p.setId(3);
      //p.setName("Beer 3");
      //em.persist(p); //add this to the context -> NOT AN INSERT QUERY

      //add a new employee
      //Employee e = new Employee();
      //e.setId(1);
      //e.setName("Cosmin");
      //e.setAddress("Victoriei, nr. 23");
      //em.persist(e);

      //find an employee and change his name; remove it from the context; add a similar object and persist it
      Employee employee = em.find(Employee.class, 1);

      if (employee != null) {
        employee.setName("John Doe");
        System.out.println(employee);
        em.remove(employee); //remove the record from the context
      }

      Employee e2 = new Employee();
      e2.setId(1);
      e2.setName("John Doe");
      e2.setAddress("Victoriei, nr. 23");
      em.persist(e2);

      //em.persist(); -> Adding an entity in the context
      //em.find();    -> Finds by PK. Get from DB and add it to the context if it doesn't already exists
      //em.remove();  -> Marking entity for removal
      //em.merge();   -> Merges and entity from outside the context to the context (the instance should exist in the db)
      //em.refresh(); -> Mirrors the context from the database
      //em.detach();  -> Taking the entity out of the context (only)
      //em.getReference() ...

      em.getTransaction().commit();
    } finally {
      em.close();
    }


  }
}