package ru.mallin.menuapi2.controllers;

import org.springframework.web.bind.annotation.*;
import ru.mallin.menuapi2.entity.Measure;
import ru.mallin.menuapi2.repos.MeasureRepo;

import java.util.List;

@RestController
public class MeasureController {

    private final MeasureRepo repo;

    public MeasureController(MeasureRepo repo) {
        this.repo = repo;
    }

    @GetMapping("/measures")
    public List<Measure> getMeasures(){
        return (List<Measure>) repo.findAll();
    }

    @GetMapping("/measures/{id}")
    public Measure getMeasureById(@PathVariable long id){
        return repo.findById(id).orElse(null);
    }

    @PostMapping("/measures/add")
    public Measure saveMeasure(@RequestBody Measure measure){
        return repo.save(measure);
    }

}
