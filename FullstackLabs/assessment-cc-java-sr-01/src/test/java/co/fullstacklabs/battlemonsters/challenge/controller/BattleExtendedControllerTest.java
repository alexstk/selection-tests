package co.fullstacklabs.battlemonsters.challenge.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import co.fullstacklabs.battlemonsters.challenge.ApplicationConfig;
import co.fullstacklabs.battlemonsters.challenge.dto.BattleDTO;
import co.fullstacklabs.battlemonsters.challenge.dto.MonsterDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.Is;
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

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();
    
    @Test
    void shouldFailStartBattleWithNonexistentMonsterReturning404StatusCode() throws Exception {
        BattleDTO battle = BattleDTO.builder()
            .monsterA(MonsterDTO.builder().id(10).build())
            .monsterB(MonsterDTO.builder().id(20).build())
            .build();

        String content = objectMapper.writeValueAsString(battle);

        mockMvc.perform(post("/battle").contentType("application/json").content(content))
            .andDo(print())
            .andExpect(status().isNotFound());
    }

    @Test
    void shouldInsertBattleWithMonsterBWinningAndReturning200StatusCode() throws Exception {
        BattleDTO battle = BattleDTO.builder()
            .monsterA(MonsterDTO.builder().id(1).build())
            .monsterB(MonsterDTO.builder().id(2).build())
            .build();

        String content = objectMapper.writeValueAsString(battle);

        mockMvc.perform(post("/battle").contentType("application/json").content(content))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", Is.is(2)));
    }

    @Test
    void shouldDeleteBattleSuccessfullyReturning200StatusCode()throws Exception {
        mockMvc.perform(delete("/battle/1"))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @Test
    void shouldFailDeletingNonexistentBattleReturning404StatusCode() throws Exception {
        mockMvc.perform(delete("/battle/10"))
            .andDo(print())
            .andExpect(status().isNotFound());
    }

}
