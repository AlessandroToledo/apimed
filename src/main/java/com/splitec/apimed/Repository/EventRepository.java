package com.splitec.apimed.Repository;

import com.google.api.services.calendar.Calendar;

import java.io.IOException;

public class EventRepository {

  public com.google.api.services.calendar.model.Event getEvent(String calendarId, String eventId,
                                                                  Calendar service) throws IOException {
    return service.events().get(calendarId, eventId).execute();
  }

  public String deleteEvent(String calendarId, String eventId,
                                                               Calendar service) throws IOException {
    service.events().delete(calendarId, eventId).execute();
    return "DELETED";
  }

  public com.google.api.services.calendar.model.Event insertEvent(String calendarId,
                                                                  com.google.api.services.calendar.model.Event event,
                                                                  Calendar service) throws IOException {
    return service.events().insert(calendarId, event).execute();
  }
}
