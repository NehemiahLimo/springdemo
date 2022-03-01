package com.example.demo.dao;

import com.example.demo.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class PersonDataAccessService implements PersonDao{
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertPerson(UUID id, Person person) {
        return 0;
    }

    @Override
    public List<Person> selectAllPeople() {
        final String sql = "SELECT id,name FROM person";
        return jdbcTemplate.query(sql,(resultset,i)->{
            return new Person(UUID.fromString(resultset.getString("id")),resultset.getString("name"));
        });
        //return people;

//        return List.of(new Person(UUID.randomUUID(),"Person from db"));
    }
    @Override
    public Optional<Person> selectPersonById(UUID id) {
        final String sql = "SELECT id,name FROM person WHERE id= ?";

        Person person = jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                (resultset, i)->{
                    UUID personId = UUID.fromString(resultset.getString("id"));
                    String name = resultset.getString("name");
                    return new Person(personId,name);
                });
        return Optional.ofNullable(person);

    }

    @Override
    public int deletePerson(UUID id) {
        return 0;
    }

    @Override
    public int updatePersonById(UUID id, Person person) {
        return 0;
    }
}
