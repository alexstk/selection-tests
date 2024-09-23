package co.fullstacklabs.battlemonsters.challenge.service.impl;

import co.fullstacklabs.battlemonsters.challenge.dto.BattleDTO;
import co.fullstacklabs.battlemonsters.challenge.dto.MonsterDTO;
import co.fullstacklabs.battlemonsters.challenge.exceptions.ResourceNotFoundException;
import co.fullstacklabs.battlemonsters.challenge.model.Battle;
import co.fullstacklabs.battlemonsters.challenge.model.Monster;
import co.fullstacklabs.battlemonsters.challenge.repository.BattleRepository;
import co.fullstacklabs.battlemonsters.challenge.repository.MonsterRepository;
import java.util.Optional;
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

    private BattleRepository battleRepository;
    private MonsterRepository monsterRepository;
    private ModelMapper modelMapper;

    public BattleExtendedService(BattleRepository battleRepository, ModelMapper modelMapper,
            MonsterRepository monsterRepository) {
        super(battleRepository, modelMapper);
        this.battleRepository = battleRepository;
        this.monsterRepository = monsterRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public BattleDTO startBattle(BattleDTO battle) {
        Monster monsterA = monsterRepository.findById(battle.getMonsterA().getId())
            .orElseThrow(() -> new ResourceNotFoundException("Monster not found"));
        Monster monsterB = monsterRepository.findById(battle.getMonsterB().getId())
            .orElseThrow(() -> new ResourceNotFoundException("Monster not found"));
        Monster winnerMonster;
        Battle battleResult;

        String firstAttacker = getFirstAttacker(monsterA, monsterB);
        if ("A".equals(firstAttacker))
            winnerMonster = initBattle(monsterA, monsterB);
        else
            winnerMonster = initBattle(monsterB, monsterA);

        battleResult = saveResults(monsterA, monsterB, winnerMonster);

        return modelMapper.map(battleResult, BattleDTO.class);
    }

    private String getFirstAttacker(Monster monsterA, Monster monsterB) {
        if (monsterA.getSpeed() > monsterB.getSpeed())
            return "A";
        else if (monsterA.getSpeed() < monsterB.getSpeed())
            return "B";
        else if (monsterA.getSpeed() == monsterB.getSpeed()) {
            if (monsterA.getAttack() > monsterB.getAttack())
                return "A";
            else
                return "B";
        }
        else
            return "A";
    }

    private Monster initBattle(Monster attacker, Monster defender) {
        int damage;
        int hp;
        Monster tempMonster;

        while (attacker.getHp() > 0 && defender.getHp() > 0) {
            damage = calculateDamage(attacker, defender);
            hp = defender.getHp() - damage;
            defender.setHp(hp);

            tempMonster = attacker;
            attacker = defender;
            defender = tempMonster;
        }

        return getWinner(attacker, defender);
    }

    private Battle saveResults(Monster monsterA, Monster monsterB, Monster winnerMonster) {
        monsterRepository.save(monsterA);
        monsterRepository.save(monsterB);

        Battle battle = new Battle();
        battle.setMonsterA(monsterA);
        battle.setMonsterB(monsterB);
        battle.setWinner(winnerMonster);
        battleRepository.save(battle);

        return battle;
    }

    private Monster getWinner(Monster attacker, Monster defender) {
        if (attacker.getHp() > 0)
            return attacker;
        else
            return defender;
    }

    private int calculateDamage(Monster attacker, Monster defender) {
        if (attacker.getAttack() <= defender.getDefense())
            return 1;
        else
            return attacker.getAttack() - defender.getDefense();
    }

    @Override
    public boolean delete(int id) {
        Battle battle = battleRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Battle not found"));
        battleRepository.delete(battle);
        return true;
    }

}
