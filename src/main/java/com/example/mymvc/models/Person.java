package com.example.mymvc.models;


import jakarta.validation.constraints.*;

public class Person {

    private int id;
    @NotEmpty(message = "У человека должно быть имя!")
    @Size(min = 2, max = 30, message = "Имя должно быть длиной от 2 до 30 букв")
    private String name;

    @Min(value = 0, message = "Возраст должен быть больше 0")
    private int age;

    @NotNull(message = "Поле имейл должно быть заполнено")
    @Email(message = "Имейл должен иметь вид имяПользователя@доменноеИмяСервера")
    private String email;

    public Person(int id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
