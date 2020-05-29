package ru.mallin.menuapi2.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mallin.menuapi2.entity.Measure;
import ru.mallin.menuapi2.repos.MeasureRepo;

@RestController
public class MeasureController {

    private final MeasureRepo repo;

    public MeasureController(MeasureRepo repo) {
        this.repo = repo;
    }

    @GetMapping("/measures")
    public Iterable<Measure> getMeasures(){
        return repo.findAll();
    }

}
