package com.example.myspringldbctemplate.models;


import jakarta.validation.constraints.*;


public class Person {

    public Person() {
    }

    private int id;
    @NotEmpty(message = "У человека должно быть имя!")
    @Size(min = 2, max = 30, message = "Имя должно быть длиной от 2 до 30 букв")
    private String name;

    @Min(value = 0, message = "Возраст должен быть больше 0")
    private int age;

    /*
    Имейл должен иметь вид: имяПользователя@домен
    ^ - начало строки
    \\w - Буквенный или цифровой символ или знак подчёркивания(буквы ограничены латиницей)
    + - один или более таких символов
    \\. - точка
     */

    @NotNull(message = "Поле имейл должно быть заполнено")
    @Email()
    @Pattern(regexp = "^\\w+@\\w+\\.\\w+", message = "Имейл должен иметь вид: имяПользователя@домен")
    private String email;


    /*Страна, Город, Индекс (6 цифр)
    [A-Z] - одна заглавная буква англ. алфавита
    \\w - Буквенный или цифровой символ или знак подчёркивания(буквы ограничены латиницей)
    + - один или более таких символов
    , - должна быть запятая в адресе
    " " - должен быть один пробел
    повтор ещё один раз
    \\d - одна или более цифр
    {6} - количество повторений предыдущего символа
    */
    @Pattern(regexp = "[А-ЯЁ]\\w+, [А-ЯЁ]\\w+, \\d{6}",
            message = "Адресс должен быть в формате - Страна, Город, индекс(6 цифр)")
    private String address;

    public Person(int id, String name, int age, String email, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
