package co.fullstacklabs.battlemonsters.challenge.controller;

import co.fullstacklabs.battlemonsters.challenge.dto.MonsterDTO;
import co.fullstacklabs.battlemonsters.challenge.exceptions.UnprocessableFileException;
import co.fullstacklabs.battlemonsters.challenge.service.MonsterService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("/monster-extended")
public class MonsterExtendedController {

    private MonsterService monsterService;

    public MonsterExtendedController(MonsterService monsterService) {
        this.monsterService = monsterService;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        monsterService.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MonsterDTO create(@RequestBody MonsterDTO monster) {
        return monsterService.create(monster);
    }

    @PostMapping("/import")
    public void importCsv(@RequestParam("file") MultipartFile file) {
      try {
        monsterService.importFromInputStream(file.getInputStream());
      } catch (IOException e) {
        throw new UnprocessableFileException(e.getMessage());
      }
    }

    @GetMapping
    public int getAll() {
      return monsterService.getAll().size();
    }
}
