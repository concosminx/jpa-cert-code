package com.nimsoc.jpa.entities;

import com.nimsoc.jpa.entities.keys.StudentKey;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class Student {

  @EmbeddedId
  private StudentKey key;

  private String name;

  public StudentKey getKey() {
    return key;
  }

  public void setKey(StudentKey key) {
    this.key = key;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
