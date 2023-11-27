package com.nimsoc.jpa.entities;

import com.nimsoc.jpa.entities.generators.UUIDGenerator;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity //you can give a name to the entity in the context
@Table(name = "employee") //table name in the database; used when the db table name is different
public class Employee {

  @Id
  //@GeneratedValue(strategy = GenerationType.IDENTITY)
  @GenericGenerator(name = "UUIDGenerator", type = UUIDGenerator.class)
  @GeneratedValue(generator = "UUIDGenerator")
  @Column(name = "id") //use only if needed
  private String id;

  private String name;

  private String address;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  @Override
  public String toString() {
    return "Employee{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", address='" + address + '\'' +
            '}';
  }
}
