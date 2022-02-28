package com.example.demo.dao;

import com.example.demo.models.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")

public class FakePersonDataAccessService implements PersonDao {
    private static List<Person> db = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {
        db.add(new Person(id, person.getName()));
    return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        return db;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return db.stream().filter(person -> person.getId().equals(id)).findFirst();
    }

    @Override
    public int deletePerson(UUID id) {
        Optional<Person> personMaybe = selectPersonById(id);
        if(personMaybe.isEmpty()){
            return 0;
        }
        db.remove(personMaybe.get());
        return 1;

    }

    @Override
    public int updatePersonById(UUID id, Person persontoUpate) {
       return selectPersonById(id).map(p->{
           int indexToUpdate = db.indexOf(p);
           if(indexToUpdate>=0){
               db.set(indexToUpdate, new Person(id, persontoUpate.getName()));
               return 1;
           }
           return 0;
       }).orElse(0);


    }
}
