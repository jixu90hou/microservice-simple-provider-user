package com.weweb.cloud.controller;

import com.weweb.cloud.entity.Person;
import com.weweb.cloud.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jackshen on 2017/3/27.
 */
@RestController
public class PersonController {
    @Autowired
    private PersonRepository personRepository;
    @GetMapping("/simple/{id}")
    public Person findById(@PathVariable Long id){
        return this.personRepository.findOne(id);
    }
}
