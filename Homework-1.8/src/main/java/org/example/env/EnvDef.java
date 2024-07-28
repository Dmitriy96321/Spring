package org.example.env;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EnvDef implements EnvironmentContacts {
    @Value("${app.default-contacts-path}")
    private String defaultContacts;
    @Value("${app.file-for-save}")
    private String contacts;

    @Override
    public List<String> getPaths() {
        return List.of(defaultContacts, contacts);
    }

    @Override
    public String getContactsPath() {
        return contacts;
    }
}
