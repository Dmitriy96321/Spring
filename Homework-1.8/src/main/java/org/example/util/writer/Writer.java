package org.example.util.writer;

import org.example.dto.Person;
import org.example.env.EnvironmentContacts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.MessageFormat;

@Component
public class Writer {
    private final EnvironmentContacts environmentContacts;

    @Autowired
    public Writer(EnvironmentContacts environmentContacts) {
        this.environmentContacts = environmentContacts;
    }

    public void writeContact(Person person) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(environmentContacts.getContactsPath()))) {
            writer.write(personForSave(person));
            writer.newLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private String personForSave(Person person) {
        return MessageFormat
                .format("{0};+{1};{2}", person.getName(), person.getPhoneNumber(), person.getEmail());
    }
}
