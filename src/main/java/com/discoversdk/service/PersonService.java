package com.discoversdk.service;

import com.discoversdk.model.Person;

import java.util.List;

public interface PersonService {

    Person savePerson(Person person);

    Person getPerson(Long id);

    List<Person> getAllPerson();

    void deletePerson(Long id);

    void deletePerson(Person person);

    Person updatePerson(Person person);

    long countPerson();
}
