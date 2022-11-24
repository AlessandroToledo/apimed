package com.splitec.apimed.Controller;

import com.splitec.apimed.Service.DoctorService;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/doctor")
public class DoctorController {

  DoctorService doctorService = new DoctorService();

  @GetMapping(value = "/list")
  public ResponseEntity<?> listDoctor() {
    return new ResponseEntity<>(doctorService.getAllDoctors(), HttpStatus.OK);
  }

  @PostMapping(value = "/add")
  public ResponseEntity createDoctor(@RequestBody String body) {
    JSONObject payload = new JSONObject(body);
    String docName = payload.getString("name");
    String docEmail = payload.getString("email");
    String docFunction = payload.getString("function");
    String response = doctorService.addDoctor(docName, docEmail, docFunction);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @DeleteMapping(value = "/remove/{id}")
  public ResponseEntity deleteDoctor(@PathVariable int id) {
    return new ResponseEntity<>(doctorService.removeDoctor(id), HttpStatus.OK);
  }

  @PutMapping(value = "/update/{id}")
  public ResponseEntity<?> updateDoctor(@RequestBody String body, @PathVariable int id) {
    JSONObject payload = new JSONObject(body);
    String docName, docEmail, docFunction;
    if (payload.has("name")) {
      docName = payload.getString("name");
    } else {
      docName = "";
    }
    if (payload.has("email")) {
      docEmail = payload.getString("email");
    } else {
      docEmail = "";
    }
    if (payload.has("function")) {
      docFunction = payload.getString("function");
    } else {
      docFunction = "";
    }
    String response = doctorService.alterDoctor(docName, docEmail, docFunction, id);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

}
