package co.fullstacklabs.battlemonsters.challenge.service.impl;



import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import co.fullstacklabs.battlemonsters.challenge.dto.MonsterDTO;
import co.fullstacklabs.battlemonsters.challenge.model.Monster;
import co.fullstacklabs.battlemonsters.challenge.repository.MonsterRepository;

@Service
public class MonsterExtendedService extends MonsterServiceImpl{
    public MonsterExtendedService(MonsterRepository monsterRepository, ModelMapper modelMapperParameter) {
        super(monsterRepository, modelMapperParameter);
    }
    
    public List<MonsterDTO> getAll() {
        //TODO Implement
        throw new UnsupportedOperationException("Not implemented");
    }



}
