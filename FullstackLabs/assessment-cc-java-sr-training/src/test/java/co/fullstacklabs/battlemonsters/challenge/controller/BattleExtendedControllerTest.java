package co.fullstacklabs.battlemonsters.challenge.controller;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import co.fullstacklabs.battlemonsters.challenge.ApplicationConfig;
import co.fullstacklabs.battlemonsters.challenge.dto.BattleDTO;
import co.fullstacklabs.battlemonsters.challenge.dto.MonsterDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@Import(ApplicationConfig.class)
public class BattleExtendedControllerTest {

    public static final String BATTLE = "/battle";
    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();
    
    @Test
    void shouldFailStartBattleWithNonexistentMonsterReturning404StatusCode() throws Exception {
        BattleDTO battleDTO = BattleDTO.builder()
            .monsterA(MonsterDTO.builder().id(10).build())
            .monsterB(MonsterDTO.builder().id(20).build())
            .build();
        mockMvc.perform(post(BATTLE + "/start")
                .contentType("application/json").content(objectMapper.writeValueAsString(battleDTO)))
            .andDo(print())
            .andExpect(status().isNotFound());
    }

    @Test
    void shouldInsertBattleWithMonsterBWinningAndReturning200StatusCode() throws Exception {
        BattleDTO battleDTO = BattleDTO.builder()
            .monsterA(MonsterDTO.builder().id(4).build())
            .monsterB(MonsterDTO.builder().id(3).build())
            .build();
        mockMvc.perform(post(BATTLE + "/start")
            .contentType("application/json").content(objectMapper.writeValueAsString(battleDTO)))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", is(3)));
    }

    @Test
    void shouldDeleteBattleSuccessfullyReturning200StatusCode()throws Exception {
        mockMvc.perform(delete(BATTLE + "/1"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", is(true)));
    }

    @Test
    void shouldFailDeletingNonexistentBattleReturning404StatusCode() throws Exception {
        mockMvc.perform(delete(BATTLE + "/10"))
            .andDo(print())
            .andExpect(status().isNotFound());
    }

}
