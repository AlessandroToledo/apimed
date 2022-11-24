package com.splitec.apimed.Service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;
import com.splitec.apimed.Constants;
import com.splitec.apimed.Repository.EventRepository;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EventService extends EventRepository {
  String calendarId = "";

  Calendar service = buildService();

  public EventService() throws GeneralSecurityException, IOException {
  }

  public com.google.api.services.calendar.model.Event addEvent(String title, String description, String date, String customerEmail) throws IOException {

    Event event = new Event()
        .setSummary(title)
        .setLocation(Constants.LOCATION)
        .setDescription(description);

    DateTime startDateTime = new DateTime(date);
    EventDateTime start = new EventDateTime()
        .setDateTime(startDateTime);
    event.setStart(start);

    DateTime endDateTime = new DateTime(start.getDateTime().getValue() + 30L * 60L * 1000L);
    EventDateTime end = new EventDateTime()
        .setDateTime(endDateTime);
    event.setEnd(end);

    EventAttendee[] attendees = new EventAttendee[]{
        new EventAttendee().setEmail(customerEmail),
        new EventAttendee().setEmail("alematheustoledo@gmail.com")
    };
    event.setAttendees(Arrays.asList(attendees));
    return insertEvent(calendarId, event, service);
  }

  public String removeEvent(String eventId) throws IOException {
    return deleteEvent(calendarId, eventId, service);
  }

  public Event retrieveEvent(String eventId) throws IOException {
    return getEvent(calendarId, eventId, service);
  }

  private Calendar buildService() throws GeneralSecurityException, IOException {
    final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
    Calendar service =
        new Calendar.Builder(HTTP_TRANSPORT, GsonFactory.getDefaultInstance(), getCredentials(HTTP_TRANSPORT))
            .setApplicationName("APIMED")
            .build();

    if (calendarId.isBlank()) {
      com.google.api.services.calendar.model.Calendar insertCalendar =
          new com.google.api.services.calendar.model.Calendar();
      insertCalendar.setSummary("APIMED Calendar");

      com.google.api.services.calendar.model.Calendar createdCalendar = service.calendars().insert(insertCalendar).execute();
      calendarId = createdCalendar.getId();
    }
    return service;
  }

  private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT)
      throws IOException {

    final List<String> SCOPES =
        Collections.singletonList(CalendarScopes.CALENDAR);

    InputStream in = new ByteArrayInputStream(Constants.CREDENTIALS.getBytes());
    GoogleClientSecrets clientSecrets =
        GoogleClientSecrets.load(GsonFactory.getDefaultInstance(), new InputStreamReader(in));

    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
        HTTP_TRANSPORT, GsonFactory.getDefaultInstance(), clientSecrets, SCOPES)
        .setDataStoreFactory(new FileDataStoreFactory(new java.io.File("tokens")))
        .setAccessType("offline")
        .build();

    LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
    return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
  }
}
