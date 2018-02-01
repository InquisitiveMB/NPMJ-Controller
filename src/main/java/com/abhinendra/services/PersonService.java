package com.abhinendra.services;

import java.util.Date;

import com.abhinendra.domain.Person;

public interface PersonService {

    public Person savePerson(Person person) throws Exception;
    
    public void readFile(String filename);
    
    public Object findAll();
    
}
