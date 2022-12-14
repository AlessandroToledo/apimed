package com.splitec.apimed.Pojos;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Entity;
import java.util.Map;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Doctor {
  private String name;
  private String email;

  private String function;

  private int id;

  public Doctor() {
  }

  public Doctor(String name, String email, String function, int id) {
    this.name = name;
    this.email = email;
    this.function = function;
    this.id = id;
  }

  public String getEmail() {
    return this.email;
  }

  public String getName() {
    return this.name;
  }

  public String getFunction() {
    return this.function;
  }

  public int getId() { return this.id; }

  public void setName(String name) {
    this.name = name;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setFunction(String function) {
    this.function = function;
  }

  public void setId(int id) { this.id = id; }
}
