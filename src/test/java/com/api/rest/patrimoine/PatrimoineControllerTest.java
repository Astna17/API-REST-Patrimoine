package com.api.rest.patrimoine;

import com.api.rest.patrimoine.Model.Patrimoine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PatrimoineControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private Patrimoine patrimoine;

    @BeforeEach
    public void setUp() {
        patrimoine = new Patrimoine();
        patrimoine.setPossesseur("Zety");
        patrimoine.setDerniereModification(LocalDateTime.now());
    }


    @Test
    public void testInvalidJsonFormat() throws Exception {
        String invalidJson = "{possesseur: Zety, derniereModification: 2024-09-23T10:40:30}";

        mockMvc.perform(put("/patrimoines/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateOrUpdatePatrimoine() throws Exception {
        String json = "{\"possesseur\":\"Zety\", \"derniereModification\":\"2024-04-12T11:15:30\"}";

        mockMvc.perform(put("/patrimoines/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());

        String updatedJson = "{\"possesseur\":\"Zety\", \"derniereModification\":\"2024-07-09T17:00:30\"}";
        mockMvc.perform(put("/patrimoines/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedJson))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateNonExistentPatrimoine() throws Exception {
        String json = "{\"possesseur\":\"Zety\", \"derniereModification\":\"2024-09-24T17:08:30\"}";

        mockMvc.perform(put("/patrimoines/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateOrUpdatePatrimoineWithoutPossesseur() throws Exception {
        String json = "{\"possesseur\":\"\", \"derniereModification\":\"2024-09-24T17:15:30\"}";

        mockMvc.perform(put("/patrimoines/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateOrUpdateWithFutureDate() throws Exception {
        String futureDateJson = "{\"possesseur\":\"Future Owner\", \"derniereModification\":\"2024-09-24T16:10:20\"}";

        mockMvc.perform(put("/patrimoines/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(futureDateJson))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateOrUpdateInvalidPatrimoine() throws Exception {
        String invalidJson = "{\"possesseur\":\"\", \"derniereModification\":\"\"}";

        mockMvc.perform(put("/patrimoines/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest());
    }


    @Test
    public void testGetPatrimoine() throws Exception {
        String json = "{\"possesseur\":\"Zety\", \"derniereModification\":\"2024-09-24T17:20:15\"}";
        mockMvc.perform(put("/patrimoines/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());

        mockMvc.perform(get("/patrimoines/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(json));
    }

    @Test
    public void testGetAllPatrimoines() throws Exception {
        String formattedDate = patrimoine.getDerniereModification().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        String json = "{\"possesseur\":\"Zety\", \"derniereModification\":\"" + formattedDate + "\"}";

        mockMvc.perform(put("/patrimoines/3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());

        mockMvc.perform(get("/patrimoines"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[{\"possesseur\":\"Zety\", \"derniereModification\":\"" + formattedDate + "\"}]"));
    }

}
