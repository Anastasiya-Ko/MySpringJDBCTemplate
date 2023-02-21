package com.example.mymvc.controllers;

import com.example.mymvc.dao.PersonDAO;
import com.example.mymvc.models.Person;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/people")
@Tag(name = "Контроллер для работы со списком людей")
public class PeopleController {

    private final PersonDAO personDAO;

    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Operation(summary = "Отображает всех людей из списка", description = "Список людей находится в ДАО")
    @GetMapping("/getAll")
    public List<Person> index() {
       return personDAO.index();
    }

    @Operation(summary = "Получения одного человека по id")
    @GetMapping("/getOne/{id}")
    public Person showWithId(@PathVariable("id") int id) {
        return personDAO.show(id);
    }

    @Operation(summary = "Получение одного человека по имени")
    @GetMapping("/getName/{name}")
    public Person showWithName(@PathVariable("name") String name) {
        return personDAO.showName(name);
    }

    @Operation(summary = "Создание нового человека", description = "При создании человека поля валидируются, согласно заданным условиям")
    @PostMapping
    public ResponseEntity<PersonDAO> createPeople(@Valid @RequestBody Person person) {
            personDAO.save(person);
 //       return new ResponseEntity<>(personDAO, HttpStatus.OK);
        return ResponseEntity.ok().body(personDAO);
    }

    @Operation(summary = "Внесение изменений в учётную запись человека по его id", description = "Новая информация должна валидироваться, согласно заданным условиям")
    @PutMapping("/{id}")
    public ResponseEntity<PersonDAO> updatePerson(@PathVariable int id,
                                                  @Valid @RequestBody Person person) {
            personDAO.update(id, person);
        return ResponseEntity.ok().body(personDAO);
    }

    @Operation(summary = "Удаление учётной записи человека по его id")
    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return String.format("Данные о человеке с id=%d успешно удалены", id);
    }

}
