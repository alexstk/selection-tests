package co.fullstacklabs.battlemonsters.challenge.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import co.fullstacklabs.battlemonsters.challenge.dto.BattleDTO;
import co.fullstacklabs.battlemonsters.challenge.dto.MonsterDTO;
import co.fullstacklabs.battlemonsters.challenge.model.Battle;
import co.fullstacklabs.battlemonsters.challenge.model.Monster;
import co.fullstacklabs.battlemonsters.challenge.repository.BattleRepository;
import co.fullstacklabs.battlemonsters.challenge.repository.MonsterRepository;
import co.fullstacklabs.battlemonsters.challenge.service.impl.BattleExtendedService;
import co.fullstacklabs.battlemonsters.challenge.testbuilders.MonsterTestBuilder;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;


@ExtendWith(MockitoExtension.class)
public class BattleExtendedServiceTest {

    @InjectMocks
    private BattleExtendedService battleExtendedService;

    @Mock
    private BattleRepository battleRepository;
    @Spy
    private ModelMapper modelMapper = new ModelMapper();
    @Mock
    private MonsterRepository monsterRepository;


    @Test
    void shouldInsertBattleWithMonsterBWinning() {
        BattleDTO battleDTO = BattleDTO.builder()
            .monsterA(MonsterDTO.builder().id(1).build())
            .monsterB(MonsterDTO.builder().id(2).build())
            .build();

        Monster monster = MonsterTestBuilder.builder().speed(1).build();
        System.out.println(monster.getId() + monster.getSpeed());

        when(monsterRepository.findById(any()))
            .thenReturn(Optional.of(MonsterTestBuilder.builder().id(1).build()))
            .thenReturn(Optional.of(MonsterTestBuilder.builder().id(2).attack(55).build()));
        when(monsterRepository.save(any())).thenReturn(null);
        when(battleRepository.save(any())).thenReturn(null);

        BattleDTO finishedBattle = battleExtendedService.startBattle(battleDTO);

        assertEquals(2, finishedBattle.getWinner().getId());
    }

    @Test
    void shouldDeleteBattleSuccessfully() {
        when(battleRepository.findById(any())).thenReturn(Optional.of(new Battle()));
        doNothing().when(battleRepository).delete(any());

        assertTrue(battleExtendedService.delete(1));
    }
}
