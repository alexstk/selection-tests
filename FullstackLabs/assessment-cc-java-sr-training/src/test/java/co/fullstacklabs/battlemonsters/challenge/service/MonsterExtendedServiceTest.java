package co.fullstacklabs.battlemonsters.challenge.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import co.fullstacklabs.battlemonsters.challenge.exceptions.UnprocessableFileException;
import co.fullstacklabs.battlemonsters.challenge.repository.MonsterRepository;
import co.fullstacklabs.battlemonsters.challenge.service.impl.MonsterExtendedService;
import co.fullstacklabs.battlemonsters.challenge.testbuilders.MonsterTestBuilder;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
public class MonsterExtendedServiceTest {

    @InjectMocks
    private MonsterExtendedService monsterExtendedService;

    @Mock
    private MonsterRepository monsterRepository;

    @Mock
    private ModelMapper mapper;

    @Test
    public void testDeleteMonsterSuccessfully() {
      when(monsterRepository.findById(any()))
          .thenReturn(Optional.of(MonsterTestBuilder.builder().build()));
      doNothing().when(monsterRepository).delete(any());

      monsterExtendedService.delete(1);
      verify(monsterRepository).delete(any());
    }

     @Test
     void testImportCsvSuccessfully() throws Exception {
       InputStream inputStream = new FileInputStream("data/monsters-correct.csv");
       assertDoesNotThrow(() -> monsterExtendedService.importFromInputStream(inputStream));
     }

    @Test
    public void whenImportFromInputStreamThrowsIOException_thenUnprocessableFileExceptionIsThrown() throws Exception {
      InputStream inputStream = new FileInputStream("data/monsters-empty-monster.csv");
      assertThrows(UnprocessableFileException.class,
          () -> monsterExtendedService.importFromInputStream(inputStream));
    }

}
