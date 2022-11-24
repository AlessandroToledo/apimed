package com.splitec.apimed.Repository;

import com.splitec.apimed.Pojos.Doctor;

import java.util.ArrayList;
import java.util.List;

public class DoctorRepository {

    private static List<Doctor> doctors = new ArrayList<>();

    public String addDoc(Doctor doctor){
        doctors.add(doctor);
        return "CREATED: " + doctor.getId();
    }

    public Doctor getDocById(int id) {

        Doctor doctor = new Doctor();
        for (int i = 0; i < doctors.size(); i++) {
            if (doctors.get(i).getId() == id) {
                doctor = doctors.get(i);
            }
        }
        return doctor;
    }

    public String removeDoc(int id) {
        for (int i = 0; i < doctors.size(); i++) {
            if (doctors.get(i).getId() == id) {
                doctors.remove(i);
            }
        }
        return "REMOVED";
    }

    public String alterDoc(String docName, String docEmail, String docFunction, int id) {
        for (int i = 0; i < doctors.size(); i++) {
            if (doctors.get(i).getId() == id) {
                Doctor doctor = doctors.get(i);
                doctor.setName(docName);
                doctor.setName(docEmail);
                doctor.setName(docFunction);
            }
        }
        return "UPDATED";
    }

    public Doctor getDoc(){
        List<Doctor> docResponse = new ArrayList<>();

        for(doctors. : docResponse)
    }
}
