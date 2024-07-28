package org.example.env;

import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public class EnvInit implements EnvironmentContacts{
    @Value("${app.file-for-save}")
    private String contacts;
    @Override
    public List<String> getPaths() {
        return List.of(contacts);
    }

    @Override
    public String getContactsPath() {
        return contacts;
    }
}
