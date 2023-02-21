package com.example.mymvc.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/first")
@Tag(name = "Тестовый контроллер", description = "Используется для отработки навыков создания RESTapi")
public class FirstController {

    @GetMapping("/hello")
    @Operation(summary = "Отображает приветствие пользователю", description = "Возвращает строковое представление")
    public String sayHello(@RequestParam(value = "name", required = false) String name,
                           @RequestParam(value = "surname", required = false) String surname) {


        System.out.println("Hello, " + name + " " + surname);

        return "hello";
    }
}
