package com.splitec.apimed;

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
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootTest
class ApimedApplicationTests {

  @Test
  void contextLoads() throws IOException, GeneralSecurityException {

    // Inicia uma aplicação
    final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
    Calendar service =
        new Calendar.Builder(HTTP_TRANSPORT, GsonFactory.getDefaultInstance(), getCredentials(HTTP_TRANSPORT))
            .setApplicationName("APIMED")
            .build();


    //Cria um calendário
    com.google.api.services.calendar.model.Calendar insertCalendar =
        new com.google.api.services.calendar.model.Calendar();
    insertCalendar.setSummary("APIMED Calendar");

    //Cria o calendário efetivamente e salva o ID
    com.google.api.services.calendar.model.Calendar createdCalendar = service.calendars().insert(insertCalendar).execute();
    String calendarId = createdCalendar.getId();


    Event event = new Event()
        .setSummary("Dentista")
        .setLocation("Av. Paulista, 800")
        .setDescription("Cuidar dos dentes é muito bom");

    // Inicio
    DateTime startDateTime = new DateTime("2022-11-24T09:00:00");
    EventDateTime start = new EventDateTime()
        .setDateTime(startDateTime);
    event.setStart(start);

    // Fim
    DateTime endDateTime = new DateTime(start.getDateTime().getValue() + 30L * 60L * 1000L);
    EventDateTime end = new EventDateTime()
        .setDateTime(endDateTime);
    event.setEnd(end);

    // Participantes
    EventAttendee[] attendees = new EventAttendee[]{
        new EventAttendee().setEmail("gabrielsperche@gmail.com"),
        new EventAttendee().setEmail("alematheustoledo@gmail.com")
    };
    event.setAttendees(Arrays.asList(attendees));


    event = service.events().insert(calendarId, event).execute();
    String url = event.getHtmlLink();
    boolean g = true;
  }

  private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT)
      throws IOException {

    //Seleciona Escopos
    final List<String> SCOPES =
        Collections.singletonList(CalendarScopes.CALENDAR);

    String accountCredentials = "{\n" +
        "    \"installed\": {\n" +
        "        \"client_id\": \"446339443406-apgv37bv68oou0ell9sv0f85kuavfr6l.apps.googleusercontent.com\",\n" +
        "        \"project_id\": \"apimed-369516\",\n" +
        "        \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
        "        \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
        "        \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
        "        \"client_secret\": \"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\",\n" +
        "        \"redirect_uris\": [\n" +
        "            \"http://localhost\"\n" +
        "        ]\n" +
        "    }\n" +
        "}";


    //Passa credenciais para input stream
    InputStream in = new ByteArrayInputStream(accountCredentials.getBytes());
    GoogleClientSecrets clientSecrets =
        GoogleClientSecrets.load(GsonFactory.getDefaultInstance(), new InputStreamReader(in));

    // Flow do OAuth
    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
        HTTP_TRANSPORT, GsonFactory.getDefaultInstance(), clientSecrets, SCOPES)
        .setDataStoreFactory(new FileDataStoreFactory(new java.io.File("tokens")))
        .setAccessType("offline")
        .build();

    //Mostra onde retorna o codigo do flow de autorização
    LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
    return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
  }


}
