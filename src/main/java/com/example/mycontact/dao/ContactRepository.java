package com.example.mycontact.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.mycontact.entities.Contact;

public interface ContactRepository extends CrudRepository<Contact, Integer> {
    List<Contact> findByNameContaining(String term);
}
