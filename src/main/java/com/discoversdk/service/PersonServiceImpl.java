package com.discoversdk.service;

import com.discoversdk.dal.PersonRepository;
import com.discoversdk.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository repository;

    @Override
    public Person savePerson(Person person) {
        return repository.save(person);
    }

    @Override
    public Person getPerson(Long id) {
        return repository.getOne(id);
    }

    @Override
    public List<Person> getAllPerson() {
        return repository.findAll();
    }

    @Override
    public void deletePerson(Long id) {
        repository.delete(id);
    }

    @Override
    public void deletePerson(Person person) {
        repository.delete(person);
    }

    @Override
    public Person updatePerson(Person person) {
        return repository.save(person);
    }

    @Override
    public long countPerson() {
        return repository.count();
    }
}
