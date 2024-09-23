package co.fullstacklabs.battlemonsters.challenge.controller;

import co.fullstacklabs.battlemonsters.challenge.ApplicationConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@AutoConfigureMockMvc
@Import(ApplicationConfig.class)
public class MonsterExtendedControllerTest {
    protected static final String MONSTER_PATH = "/monster";
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenDeleteInexistingMonster_thenResponseIsNotFound() throws Exception {
        mockMvc.perform(delete(MONSTER_PATH + "/9999")).andExpect(status().isOk());
    }

    @Test
    void shouldCreateReturning201StatusCodeAndDeleteReturning200StatusCode() throws Exception {
        //TODO: implement
        assertEquals(1,2);

    }

    @Test
    void shouldDeleteMonsterReturning404StatusCode() throws Exception {
        //TODO: implement
        assertEquals(1,2);

    }

    @Test
    void testImportCsvSuccessfullyReturning200StatusCode() throws Exception {
        //TODO: implement
        assertEquals(1,2);
    }

    @Test
    void testImportCsvNonexistentColumnsReturningInternalServerError() throws Exception {
        //TODO: implement
        assertEquals(1,2);
    }

}
