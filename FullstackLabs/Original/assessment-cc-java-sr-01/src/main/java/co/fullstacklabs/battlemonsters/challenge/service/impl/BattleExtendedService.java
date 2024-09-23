package co.fullstacklabs.battlemonsters.challenge.service.impl;

import co.fullstacklabs.battlemonsters.challenge.dto.BattleDTO;
import co.fullstacklabs.battlemonsters.challenge.dto.MonsterDTO;
import co.fullstacklabs.battlemonsters.challenge.exceptions.ResourceNotFoundException;
import co.fullstacklabs.battlemonsters.challenge.model.Battle;
import co.fullstacklabs.battlemonsters.challenge.model.Monster;
import co.fullstacklabs.battlemonsters.challenge.repository.BattleRepository;
import co.fullstacklabs.battlemonsters.challenge.repository.MonsterRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

@Service
public class BattleExtendedService extends BattleServiceImpl {
    public BattleExtendedService(BattleRepository battleRepository, ModelMapper modelMapper,
            MonsterRepository monsterRepository) {
        super(battleRepository, modelMapper);
    }
    

    public BattleDTO startBattle(BattleDTO startBattleParamter) {
        //TODO: Implement method
        throw new UnsupportedOperationException("Not implemented");
    }

    public boolean delete(int identifierBattleTodelete) {
        //TODO: Implement method
        throw new UnsupportedOperationException("Not implemented");
    }
}
