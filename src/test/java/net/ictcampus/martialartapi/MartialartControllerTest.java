package net.ictcampus.martialartapi;

import net.ictcampus.martialartapi.controller.controllers.MartialartController;
import net.ictcampus.martialartapi.controller.services.MartialartService;
import net.ictcampus.martialartapi.controller.services.UserDetailsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.TimeZone;

import static net.ictcampus.martialartapi.utils.TestDataUtil.getTestMartialarts;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = MartialartController.class)
@AutoConfigureMockMvc(addFilters = false)
public class MartialartControllerTest {

    // JSON-Gerüst für alle Martial Arts (entspricht dem Gerüst von TestDataUtil: Martial Arts())
    private static final String JSON_ALL_MARTIAL_ARTS = "[]";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MartialartService martialartService;

    // muss mitgegeben werden, da wir für Requests angemeldet sein müssen
    @MockBean
    private UserDetailsServiceImpl userDetailsService;

    // dieser kommt zusätzlich mit, da das Passwort verschlüsselt und entschlüsselt wird
    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // vor jedem Testfall wird die Zeitzone gemäss der DB-Einstellungen gesetzt, ansonsten könnte es
    // zu Fehler bei der Abfrage kommen
    @BeforeEach
    public void prepare() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    /**
     * GET-Request: Alle Martial Arts werden herausgegeben und getestet, ob sie im richtigen JSON-Format
     * geschickt werden
     * @throws Exception
     */
    @Test
    public void checkGet_whenNoParam_thenAllMartialartsReturned() throws Exception {
        // gibt alle Martial Arts aus, sobald findAll im gemockten MartialartService aufgerufen wird
        doReturn(getTestMartialarts()).when(martialartService).findAll();

        // GET-Request über localhost:8080/movies "geschickt"
        mockMvc.perform(get("/martialarts"))
                // 200 (OK) wird erwartet -> bei erfolgreicher Abfrage, bekommen wir in der Regel
                // den Statuscode 200 zurück
                .andExpect(status().isOk())
                // wir erwarten, dass der Inhalt der Abfrage mit unserem JSON-Gerüst (unsere oben
                // definierte Konstante) übereinstimmt
                .andExpect(content().json(JSON_ALL_MARTIAL_ARTS));
    }


    /**
     *  GET-Request: Der richtige Martial Art über name-Query wird getestet
     * @throws Exception
     */
    @Test
    public void checkGet_whenValidName_thenMartialartIsReturned() throws Exception {
        // lokale Variable für den Martial Art Name, dass variabel getestet werden kann
        String martialartName = "Martial Art 3";

        // gibt den Martial Art "Martial Art 3" aus sobald findByName im MartialartService aufgerufen wird
        doReturn(getTestMartialarts().subList(2, 3)).when(martialartService).findByName(martialartName);

        // GET-Request über localhost:8080/movies "geschickt"
        mockMvc.perform(get("/martialarts")
                        // unserer URL wird zusätzlich ein Query-Parameter mitgegeben (unser Martial Art Name)
                        // -> localhost:8080/martialart?name=Martial Art 3
                        .queryParam("name", martialartName))
                // Statuscode 200 (OK) wird erwartet
                .andExpect(status().isOk())
                // auf der ersten Ebene des JSONs wird erwartet, dass der Name Martial Art 3 auftaucht
                .andExpect(jsonPath("$[0].name").value(martialartName));
    }

    /**
     * POST-Request: neuer Martial Art wird geaddet und überprüft
     * @throws Exception
     */
    @Test
    public void checkPost_whenNewMartialart_thenIsOk() throws Exception {

        // POST-Request über localhost:8080/martialarts "geschickt"
        mockMvc.perform(post("/martialarts")
                        // der Inhalt in unserem Body entspricht einem JSON
                        .contentType("application/json")
                        // ein neues Martialart-Objekt wird als JSON in den Body gegeben und mitgeschickt
                        .content("{\"name\": \"NewMartialart\"}"))
                // wir erwarten den Status 201 (CREATED)
                .andExpect(status().isCreated());
    }

    /**
     *  DELETE-Request: Martial Art mit der ID 1 wird gelöscht und überprüft
     * @throws Exception
     */
    @Test
    public void checkDelete_whenIdGiven_thenIsOk() throws Exception {
        // DELETE-Request über localhost:8080/martialarts/1 wird "ausgeführt"
        mockMvc.perform(delete("/martialarts/1"))
                // Status 200 (OK) wird erwartet
                .andExpect(status().isOk());

        // über Mockito wird verifiziert, ob die ID bei deleteById der ID 1 entspricht
        Mockito.verify(martialartService).deleteById(eq(1));
    }
}
