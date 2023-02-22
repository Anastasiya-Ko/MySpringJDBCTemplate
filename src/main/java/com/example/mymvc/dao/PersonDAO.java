package com.example.mymvc.dao;

import com.example.mymvc.models.Person;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class PersonDAO {

    public int PEOPLE_COUNT;

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static String USERNAME = "postgres";
    private static String PASSWORD = "admin";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Person> index() {
        List<Person> people = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM Person";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));

                people.add(person);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return people;
    }

    public Person show (int id) throws RuntimeException{
        // return people.get(id);
        // return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);

//        for (Person person : people) {
//            if (person.getId() == id) return person;
//        } throw new RuntimeException("Не найден People с таким id !");
    return null;
    }

    public Person showName(String name) {
//        for (Person person : people) {
//            if (person.getName().equals(name)) return person;
//        }
        return null;
//        return people.stream().filter(person -> person.getName().equals(name)).findAny().orElse(null);
    }

    public Person save(Person person) {
//        person.setId(++PEOPLE_COUNT);
//        people.add(person);
        return person;
    }

    public void delete(int id) {
//        for (int i = 0; i < people.size(); i++) {
//            if (people.get(i).getId() == id) people.remove(i);
//        }
    //    people.removeIf(person -> person.getId() == id);
    }

    public void update(int id, Person updatePerson) {
        Person personToBeUpdate = show(id);
        personToBeUpdate.setName(updatePerson.getName());
        personToBeUpdate.setAge(updatePerson.getAge());
        personToBeUpdate.setEmail(updatePerson.getEmail());
    }



}
