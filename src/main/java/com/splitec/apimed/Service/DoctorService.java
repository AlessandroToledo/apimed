package com.splitec.apimed.Service;

import com.splitec.apimed.Pojos.Doctor;
import com.splitec.apimed.Repository.DoctorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DoctorService extends DoctorRepository {

  public String addDoctor(String name, String email, String function) {
    Random random = new Random();
    int id = random.nextInt(Integer.MAX_VALUE);
    while (getDocById(id).getId() != 0) {
      id = random.nextInt(Integer.MAX_VALUE);
    }
    Doctor doctor = new Doctor(name, email, function, id);
    return addDoc(doctor);

  }

  public String removeDoctor(int id) {
    return removeDoc(id);
  }

  public String alterDoctor(String docName, String docEmail, String docFunction, int id) {
    return alterDoc(docName, docEmail, docFunction, id);
  }

  public List<Doctor> getAllDoctors() {
    return listDoc();
  }

}
