package org.example.service;

import org.example.dto.Person;
import org.example.util.PersonManager;
import org.example.util.reader.Reader;
import org.example.util.writer.Writer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class ContactService {
    private final Map<Integer, Person> contacts;
    private final Writer writer;
    private final Reader reader;
    private final PersonManager personManager;

    @Autowired
    public ContactService(Writer writer, Reader reader, PersonManager personManager) {
        this.writer = writer;
        this.contacts = new HashMap<>();
        this.reader = reader;
        this.personManager = personManager;
    }
    @PostConstruct
    public void init() {
        reader.getContacts().forEach(contact -> {
            Person person = personManager.createPerson(contact);
            if (person.getName() != null) {
                contacts.put(person.getPhoneNumber(), person);
            }
        });
    }

    public String getAllContacts() {
        StringBuilder sb = new StringBuilder();
        contacts.values().forEach(person -> sb.append(person.toString()).append("\n"));
        return sb.toString();
    }

    public void addContact(Person person) {
        if (noEmptyFields(person)){
            System.out.println("Неверный формат ввода данных пользователя");
            return;
        }
        contacts.put(person.getPhoneNumber(), person);
        writer.writeContact(person);
    }

    public void removeContact(Person person) {
        contacts.remove(person.getPhoneNumber());
    }

    public Person getContactByEmail(String email) {
        return contacts.values().stream()
                .filter(person -> person.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    private boolean noEmptyFields(Person person) {
        return (person.getName() == null || person.getName().isEmpty())
                || (person.getEmail() == null || person.getEmail().isEmpty())
                || person.getPhoneNumber() == null;
    }

}
