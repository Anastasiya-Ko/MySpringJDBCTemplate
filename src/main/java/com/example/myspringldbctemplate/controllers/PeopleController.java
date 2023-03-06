package com.example.myspringldbctemplate.controllers;

import com.example.myspringldbctemplate.models.Person;
import com.example.myspringldbctemplate.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/people")
@Tag(name = "Контроллер для работы со списком людей")
public class PeopleController {

    private final PersonService personService;

    public PeopleController(PersonService personService) {
        this.personService = personService;
    }

    @Operation(summary = "Отображает всех людей из списка", description = "Список людей находится в ДАО")
    @GetMapping("/get-all")
    public List<Person> index() {
        return personService.index();
    }

    @Operation(summary = "Получения одного человека по id")
    @GetMapping("/get-one/{id}")
    public Person showWithId(@PathVariable("id") int id) {
        return personService.show(id);
    }

    @Operation(summary = "Получение одного человека по имени")
    @GetMapping("/get-name/{name}")
    public Person showWithName(@PathVariable("name") String name) {
        return personService.showName(name);
    }

    @Operation(summary = "Создание нового человека", description = "При создании человека поля валидируются, согласно заданным условиям")
    @PostMapping
    public ResponseEntity<Person> createPeople(@Valid @RequestBody Person person) {
        personService.save(person);
        return ResponseEntity.ok().body(person);
    }

    @Operation(summary = "Внесение изменений в учётную запись человека по его id", description = "Новая информация должна валидироваться, согласно заданным условиям")
    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable int id,
                                               @Valid @RequestBody Person person) {
        personService.update(id, person);
        return ResponseEntity.ok().body(person);
    }

    @Operation(summary = "Удаление учётной записи человека по его id")
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        personService.delete(id);

        List<String> list = new ArrayList<>();
        list.add(String.format("Человек c номером %d успешно удалён из списка", id));

        return ResponseEntity.ok().body(list);

    }

}
