package co.fullstacklabs.battlemonsters.challenge.controller;

import co.fullstacklabs.battlemonsters.challenge.dto.BattleDTO;
import co.fullstacklabs.battlemonsters.challenge.service.impl.BattleExtendedService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/battle")
public class BattleExtendedController {

    private BattleExtendedService battleService;

    public BattleExtendedController(BattleExtendedService battleService) {
        this.battleService = battleService;
    }

    @PostMapping
    public int startBattle(@RequestBody BattleDTO battle) {
        return battleService.startBattle(battle).getId();
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable("id") int id) {
        return battleService.delete(id);
    }

}
