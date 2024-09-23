package co.fullstacklabs.battlemonsters.challenge.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/battle")
public class BattleExtendedController {

    @PostMapping
    public int startBattle() {
        return 0;
    }

    @DeleteMapping("/{id}")
    public int delete() {
        return 0;
    }

}
