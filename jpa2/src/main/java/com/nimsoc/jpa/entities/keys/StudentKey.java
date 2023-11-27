package com.nimsoc.jpa.entities.keys;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StudentKey implements Serializable {

  public StudentKey(String code, long number) {
    this.code = code;
    this.number = number;
  }

  public StudentKey() {
  }

  private String code;
  private long number;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public long getNumber() {
    return number;
  }

  public void setNumber(long number) {
    this.number = number;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    StudentKey that = (StudentKey) o;
    return number == that.number && Objects.equals(code, that.code);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, number);
  }
}
