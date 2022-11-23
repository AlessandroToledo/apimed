package com.splitec.apimed.Pojos;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Event {
  private String title;
  private Date datetime;
  private String email;
  String medicalBranch;
  String doctor;

  public Event() {
  }

  public Event(String title, Date datetime, String email, String medicalBranch, String doctor) {
    this.datetime = datetime;
    this.title = title;
    this.email = email;
    this.doctor = doctor;
    this.medicalBranch = medicalBranch;
  }

  public String getEmail() {
    return this.email;
  }

  public String getTitle() {
    return this.title;
  }

  public String getDoctor() {
    return this.doctor;
  }

  public Date getIdade() {
    return this.datetime;
  }

  public String getMedicalBranch() {
    return this.medicalBranch;
  }

  public void setDoctor(String doctor) {
    this.doctor = doctor;
  }

}
