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
@Tag(name = "Контроллер для работы сос списком людей")
public class PeopleController {

    private PersonDAO personDAO;

    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    /**
     * Получаем всех людей их DAO
     */
    @Operation(summary = "Отображает всех людей из списка", description = "Список людей находится в ДАО")
    @GetMapping("/all")
    public List<Person> index() {

       return personDAO.index();
    }

    /**
     * Получим одного человека по id из DAO
     */
    @GetMapping("/one/{id}")
    public Person showWithId(@PathVariable("id") int id) {
        return personDAO.show(id);
    }

    @GetMapping("/getName/{name}")
    public Person showWithName(@PathVariable("name") String name) {
        return personDAO.showName(name);
    }

    @PostMapping
    public ResponseEntity<PersonDAO> createPeople(@Valid @RequestBody Person person) {

            personDAO.save(person);

 //       return new ResponseEntity<>(personDAO, HttpStatus.OK);
        return ResponseEntity.ok().body(personDAO);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<PersonDAO> updatePerson(@PathVariable int id,
                                                  @Valid @RequestBody Person person) {

            personDAO.update(id, person);

        return ResponseEntity.ok().body(personDAO);
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return String.format("Данные о человеке с id=%d успешно удалены", id);
    }





//    @PostMapping("/sann")
//    public ResponseEntity<?> create(
//            @RequestBody @Valid Person person,
//            BindingResult bindingResult) {
//
//        try{
//            personDAO.save(person);
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//
//
//
//        return new ResponseEntity<>(person, HttpStatus.OK);
//    }
}
