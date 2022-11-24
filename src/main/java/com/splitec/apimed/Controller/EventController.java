package com.splitec.apimed.Controller;

import com.splitec.apimed.Service.EventService;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;

@RestController
@RequestMapping(value = "/event")

public class EventController {

  EventService eventService = new EventService();
  public EventController() throws GeneralSecurityException, IOException {
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity eventById(@PathVariable String id) throws IOException {
    return new ResponseEntity<>(eventService.retrieveEvent(id), HttpStatus.OK);
  }

  @PostMapping(value = "/add")
  public ResponseEntity createEvent(@RequestBody String body) throws IOException {
    JSONObject payload = new JSONObject(body);
    String title = (payload.has("title")) ? payload.getString("title") : "";
    String desc = (payload.has("description")) ? payload.getString("description") : "";
    String date = (payload.has("date")) ? payload.getString("date") : "";
    String email = (payload.has("customerEmail")) ? payload.getString("customerEmail") : "";
    return new ResponseEntity<>(eventService.addEvent(title, desc, date, email), HttpStatus.CREATED);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity deleteEvent(@PathVariable String id) throws IOException {
    return new ResponseEntity<>(eventService.removeEvent(id), HttpStatus.NO_CONTENT);
  }

}
