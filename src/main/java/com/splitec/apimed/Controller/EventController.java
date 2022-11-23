package com.splitec.apimed.Controller;

import com.splitec.apimed.Pojos.Event;
import com.splitec.apimed.Pojos.User;
import com.splitec.apimed.Service.EventService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value = "/event")
public class EventController extends EventService {

  @Autowired

  @GetMapping(value = "/{id}")
  public ResponseEntity<User> eventById(@PathVariable String id) {
    retrieveEvent(id);
    return new ResponseEntity<>(null, HttpStatus.OK);
  }

  @PostMapping(value = "/add")
  public ResponseEntity createEvent(@RequestBody String body) {
    addEvent(body);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity deleteEvent(@PathVariable String id) {
    removeEvent(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
