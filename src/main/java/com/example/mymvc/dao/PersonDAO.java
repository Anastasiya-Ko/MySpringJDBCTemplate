package com.example.mymvc.dao;

import com.example.mymvc.models.Person;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class PersonDAO {

    public int PEOPLE_COUNT;
    public List<Person> people;
    {
        people = new ArrayList<>();

        people.add(new Person(++PEOPLE_COUNT, "Anna", 25, "anna@mail.ru"));
        people.add(new Person(++PEOPLE_COUNT, "Vanna", 35, "vanna@mail.ru"));
        people.add(new Person(++PEOPLE_COUNT, "Taya", 45, "taya@mail.ru"));
        people.add(new Person(++PEOPLE_COUNT, "Ivanka", 55, "ivanka@mail.ru"));

    }

    public List<Person> index() {
        return people;
    }

    public Person show (int id) throws RuntimeException{
        // return people.get(id);
        // return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);

        for (Person person : people) {
            if (person.getId() == id) return person;
        } throw new RuntimeException("Не найден People с таким id !");
    }

    public Person showName(String name) {
        for (Person person : people) {
            if (person.getName().equals(name)) return person;
        }
        return null;
//        return people.stream().filter(person -> person.getName().equals(name)).findAny().orElse(null);
    }

    public Person save(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
        return person;
    }

    public void delete(int id) {
        for (int i = 0; i < people.size(); i++) {
            if (people.get(i).getId() == id) people.remove(i);
        }
    //    people.removeIf(person -> person.getId() == id);
    }

    public void update(int id, Person updatePerson) {
        Person personToBeUpdate = show(id);
        personToBeUpdate.setName(updatePerson.getName());
        personToBeUpdate.setAge(updatePerson.getAge());
        personToBeUpdate.setEmail(updatePerson.getEmail());
    }



}
