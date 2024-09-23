package co.fullstacklabs.battlemonsters.challenge.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import co.fullstacklabs.battlemonsters.challenge.ApplicationConfig;

@SpringBootTest
@AutoConfigureMockMvc
@Import(ApplicationConfig.class)
public class BattleExtendedControllerTest {

    
    @Test
    void shouldFailStartBattleWithNonexistentMonsterReturning404StatusCode() throws Exception {
        //TODO: implement
        assertEquals(1,2);
    }

    @Test
    void shouldInsertBattleWithMonsterBWinningAndReturning200StatusCode() throws Exception {
        //TODO: implement
        assertEquals(1,2);
    }

    @Test
    void shouldDeleteBattleSuccessfullyReturning200StatusCode()throws Exception {
        //TODO: implement
        assertEquals(1,2);
    }

    @Test
    void shouldFailDeletingNonexistentBattleReturning404StatusCode() throws Exception {
        //TODO: implement
        assertEquals(1,2);
    }

}
