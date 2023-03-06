package com.example.mymvc.service;

import com.example.mymvc.models.Person;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class PersonService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) throws RuntimeException {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public Person showName(String name) {
//        for (Person person : people) {
//            if (person.getName().equals(name)) return person;
//        }
        return null;
//        return people.stream().filter(person -> person.getName().equals(name)).findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person VALUES (1, ?, ?, ?)", person.getName(), person.getAge(), person.getEmail());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);

    }

    public void update(int id, Person updatePerson) {
        jdbcTemplate.update("UPDATE Person SET name=?, age=?, email=? WHERE id=?", updatePerson.getName(), updatePerson.getAge(), updatePerson.getEmail(), id);
    }

    ////////////////////////////////////////////////////////
    ///Тестируем производительность пакетной вставки в БД///
    ////////////////////////////////////////////////////////

    /**
     * Метод для тестирования вставки 1000 записей в БД, без использования пакетной вставки
     * @return Время, затраченное на операцию
     */
    public void testMultipleUpdate() {
        List<Person> people = create1000people();

        long before = System.currentTimeMillis();

        for (Person person : people) {
            jdbcTemplate.update("INSERT INTO Person VALUES (?, ?, ?, ?)",
                    person.getId(), person.getName(), person.getAge(), person.getEmail());
        }

        long after = System.currentTimeMillis();

        System.out.println("Time: " + (after - before));
    }

    /**
     * Метод для тестирования вставки 1000 записей в БД, с использованием пакетной вставки.
     * Для этого используется jdbc template и реализуется анонимный класс
     * @return Время, затраченное на операцию
     */
    public void testBatchUpdate() {
        List<Person> people = create1000people();

        long before = System.currentTimeMillis();

        jdbcTemplate.batchUpdate("INSERT INTO Person VALUES(?, ?, ?, ?)",
        new BatchPreparedStatementSetter() {

            /**
             * В этом методе находятся все значения, которые попадут в наш пакет
             * @param ps ссылка на "INSERT INTO Person VALUES(?, ?, ?, ?)"
             * @param i указывает на текущего человека
             * @throws SQLException
             */
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setInt(1, people.get(i).getId());
                ps.setString(2, people.get(i).getName());
                ps.setInt(3, people.get(i).getAge());
                ps.setString(4, people.get(i).getEmail());
            }

            /**
             *
             * @return Возвращает размер нашего пакета
             */
            @Override
            public int getBatchSize() {
                return people.size();
            }

        });

        long after = System.currentTimeMillis();

        System.out.println("Time: " + (after - before));
    }

    /**
     * Метод для добавления 1000 людей в БД
     * @return 1000 новых людей
     */
    private List<Person> create1000people() {
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            people.add(new Person(i, "Name" + i, 30, "test" + i + "@mail.ru"));
        }
        return people;
    }


}
