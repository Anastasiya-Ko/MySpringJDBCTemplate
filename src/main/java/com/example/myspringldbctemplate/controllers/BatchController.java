package com.example.myspringldbctemplate.controllers;

import com.example.myspringldbctemplate.models.Person;
import com.example.myspringldbctemplate.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test-batch-update")
@Tag(name = "Контроллер для тестирования производительности пакетной вставки в БД")
public class BatchController {

    private final PersonService personService;

    public BatchController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping()
    public List<Person> index(){
        return personService.index();
    }

    @Operation(summary = "Запрос к БД без использования пакетной вставки", description = "Возвращает время, потраченное на добавление 1000 человек")
    @GetMapping("/without")
    public String withoutBatch() {
        return personService.testMultipleUpdate();
    }

    @Operation(summary = "Запрос к БД С использования пакетной вставки", description = "Возвращает время, потраченное на добавление 1000 человек одним пакетом")
    @GetMapping("/with")
    public String withBatch() {
       return personService.testBatchUpdate();
    }


}
