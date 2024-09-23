package co.fullstacklabs.battlemonsters.challenge.service.impl;

import co.fullstacklabs.battlemonsters.challenge.dto.BattleDTO;
import co.fullstacklabs.battlemonsters.challenge.exceptions.ResourceNotFoundException;
import co.fullstacklabs.battlemonsters.challenge.model.Battle;
import co.fullstacklabs.battlemonsters.challenge.model.Monster;
import co.fullstacklabs.battlemonsters.challenge.repository.BattleRepository;
import co.fullstacklabs.battlemonsters.challenge.repository.MonsterRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BattleExtendedService extends BattleServiceImpl {

    private BattleRepository battleRepository;
    private ModelMapper modelMapper;
    private MonsterRepository monsterRepository;

    public BattleExtendedService(BattleRepository battleRepository, ModelMapper modelMapper,
            MonsterRepository monsterRepository) {
        super(battleRepository, modelMapper);
        this.battleRepository = battleRepository; // Because BattleServiceImpl cannot be modified
        this.modelMapper = modelMapper; // Because BattleServiceImpl cannot be modified
        this.monsterRepository = monsterRepository;
    }
    
    @Override
    public BattleDTO startBattle(BattleDTO battle) {
        Monster monsterA = findMonsterById(battle.getMonsterA().getId());
        Monster monsterB = findMonsterById(battle.getMonsterB().getId());
        Monster monsterWinner;
        Battle finishedBattle;

        String firstAttacker = getFirstAttacker(monsterA, monsterB);
        if (firstAttacker.equals("A"))
            monsterWinner = initBattle(monsterA, monsterB);
        else
            monsterWinner = initBattle(monsterB, monsterA);

        finishedBattle = saveResults(monsterA, monsterB, monsterWinner);

        return modelMapper.map(finishedBattle, BattleDTO.class);
    }

    private Monster findMonsterById(int id) {
        return monsterRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Monster not found"));
    }

    private String getFirstAttacker(Monster monsterA, Monster monsterB) {
        if (monsterA.getSpeed() > monsterB.getSpeed())
            return "A";
        else if (monsterA.getSpeed() < monsterB.getSpeed())
            return "B";
        else if (monsterA.getSpeed() == monsterB.getSpeed() && monsterA.getAttack() > monsterB.getAttack())
            return "A";
        else if (monsterA.getSpeed() == monsterB.getSpeed() && monsterA.getAttack() < monsterB.getAttack())
            return "B";
        else
            return "A";
    }

    private Monster initBattle(Monster attacker, Monster defender) {
        int damage;
        int hp;
        Monster temp;

        while (attacker.getHp() > 0 && defender.getHp() > 0) {
            damage = calculateDamage(attacker, defender);
            hp = defender.getHp() - damage;
            defender.setHp(hp);

            temp = defender;
            defender = attacker;
            attacker = temp;
        }

        return getWinner(attacker, defender);
    }

    private int calculateDamage(Monster attacker, Monster defender) {
        if (attacker.getAttack() <= defender.getDefense())
            return 1;
        else
            return attacker.getAttack() - defender.getDefense();
    }

    private Monster getWinner(Monster attacker, Monster defender) {
        if (attacker.getHp() > defender.getHp())
            return attacker;
        return defender;
    }

    private Battle saveResults(Monster monsterA, Monster monsterB, Monster monsterWinner) {
        Battle finishedBattle = new Battle();
        finishedBattle.setMonsterA(monsterA);
        finishedBattle.setMonsterB(monsterB);
        finishedBattle.setWinner(monsterWinner);

        monsterRepository.save(monsterA);
        monsterRepository.save(monsterB);
        battleRepository.save(finishedBattle);
        return finishedBattle;
    }

    @Override
    public boolean delete(int id) {
        Battle battle = battleRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Battle not found"));
        battleRepository.delete(battle);
        return true;
    }
}
