package com.splitec.apimed.Controller;

import com.splitec.apimed.Pojos.User;
import com.splitec.apimed.Service.DoctorService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/doctor")
public class DoctorController extends DoctorService {

    @Autowired

    @GetMapping(value = "/list")
    public ResponseEntity<?> listDoctor() {
        return new ResponseEntity<>(getDoctors(), HttpStatus.OK);
    }

    @PostMapping(value = "/add")
    public ResponseEntity createDoctor(@RequestBody String body) {
        JSONObject payload = new JSONObject(body);
        String docName = payload.getString("name");
        String docEmail = payload.getString("email");
        String docFunction = payload.getString("function");
        String response = addDoctor(docName, docEmail, docFunction);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/remove/{id}")
    public ResponseEntity deleteDoctor(@PathVariable int id) {
        return new ResponseEntity<>(removeDoctor(id), HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateDoctor(@RequestBody String body, @PathVariable int id) {
        JSONObject payload = new JSONObject(body);
        String docName = payload.getString("name");
        String docEmail = payload.getString("email");
        String docFunction = payload.getString("function");
        String response = alterDoctor(docName, docEmail, docFunction, id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
