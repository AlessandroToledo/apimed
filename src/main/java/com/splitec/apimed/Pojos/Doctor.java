package com.splitec.apimed.Pojos;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Entity;
import java.util.Map;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Doctor {
  private String name;
  private String email;
  private Map<String, Object> availableCalendar;

  public Doctor() {
  }

  public Doctor(String name, String email, Map<String, Object> availableCalendar) {
    this.availableCalendar = availableCalendar;
    this.name = name;
    this.email = email;
  }

  public String getEmail() {
    return this.email;
  }

  public String getName() {
    return this.name;
  }

  public Map<String, Object> getCalendar() {
    return this.availableCalendar;
  }
}
