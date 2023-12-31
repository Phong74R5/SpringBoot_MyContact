package com.example.mycontact.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mycontact.dao.ContactRepository;
import com.example.mycontact.entities.Contact;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public Iterable<Contact> findAll() {
        return contactRepository.findAll();
    }

    @Override
    public List<Contact> search(String term) {
        return contactRepository.findByNameContaining(term);
    }

    @Override
    public Contact findById(Integer id) {
        return contactRepository.findById(id).get();
    }

    @Override
    public void save(Contact contact) {
        contactRepository.save(contact);
    }

    @Override
    public void delete(Integer id) {
        Contact contact = contactRepository.findById(id).get();
        contactRepository.delete(contact);
    }

}
