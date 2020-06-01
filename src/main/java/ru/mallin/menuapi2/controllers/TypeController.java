package ru.mallin.menuapi2.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.mallin.menuapi2.entity.Type;
import ru.mallin.menuapi2.repos.TypeRepo;

import java.util.List;

@RestController
public class TypeController {

    private final TypeRepo repo;

    public TypeController(TypeRepo repo) {
        this.repo = repo;
    }

    @GetMapping("/types")
    public List<Type> getTypes(){
        return (List<Type>) repo.findAll();
    }

    @PostMapping("/types/add")
    public Type saveType(@RequestBody Type type){
        return repo.save(type);
    }
}
