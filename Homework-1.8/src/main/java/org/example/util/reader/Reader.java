package org.example.util.reader;

import org.example.env.EnvironmentContacts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
public class Reader  {
    private final EnvironmentContacts environmentContacts;
    private final List<String> contacts;

    @Autowired
    public Reader(EnvironmentContacts environmentContacts) {
        this.contacts = new ArrayList<>();
        this.environmentContacts = environmentContacts;
    }
    @PostConstruct
    public void uploadContacts() {
        environmentContacts.getPaths().forEach(this::read);
    }


    public List<String> getContacts() {
        return contacts;
    }

    private void read(String path) {
        try {
            contacts.addAll(Files.readAllLines(Paths.get(path)));
        } catch (IOException e) {
            System.err.println(e.getClass().getName() + e.getMessage());
        }
    }


}
