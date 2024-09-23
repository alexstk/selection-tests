package co.fullstacklabs.battlemonsters.challenge.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import co.fullstacklabs.battlemonsters.challenge.ApplicationConfig;
import co.fullstacklabs.battlemonsters.challenge.dto.MonsterDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@AutoConfigureMockMvc
@Import(ApplicationConfig.class)
public class MonsterExtendedControllerTest {
    protected static final String MONSTER_PATH = "/monster";
    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void whenDeleteInexistingMonster_thenResponseIsNotFound() throws Exception {
        mockMvc.perform(delete(MONSTER_PATH + "/9999")).andExpect(status().isNotFound());
    }

    @Test
    void shouldCreateReturning201StatusCodeAndDeleteReturning200StatusCode() throws Exception {
        MonsterDTO monster = MonsterDTO.builder().speed(50).attack(40).defense(30).hp(60)
            .name("monsterX").imageUrl("http://images.com/54").build();

        String response = mockMvc.perform(
                post(MONSTER_PATH).contentType("application/json")
                    .content(objectMapper.writeValueAsString(monster)))
            .andDo(print())
            .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();

        MonsterDTO createdMonster = objectMapper.readValue(response, MonsterDTO.class);

        mockMvc.perform(delete(MONSTER_PATH + "/" + createdMonster.getId()))
            .andExpect(status().isOk());
    }

    @Test
    void shouldDeleteMonsterReturning404StatusCode() throws Exception {
        mockMvc.perform(delete(MONSTER_PATH + "/111")).andExpect(status().isNotFound());
    }

    @Test
    void testImportCsvSuccessfullyReturning200StatusCode() throws Exception {
        Path path = Paths.get("data", "monsters-correct.csv");
        byte[] fileBytes = Files.readAllBytes(path);

        MockMultipartFile file = new MockMultipartFile(
            "file",
            path.getFileName().toString(),
            "text/csv",
            fileBytes);

        mockMvc.perform(multipart(MONSTER_PATH + "/import").file(file))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @Test
    void testImportCsvNonexistentColumnsReturningInternalServerError() throws Exception {
        File file = new File("data/monsters-empty-monster.csv");
        InputStream inputStream = new FileInputStream(file);

        MockMultipartFile multipartFile = new MockMultipartFile(
            "file",
            file.getName(),
            "text/csv",
            inputStream);

        mockMvc.perform(multipart(MONSTER_PATH + "/import").file(multipartFile))
            .andDo(print())
            .andExpect(status().isInternalServerError());
    }

}
