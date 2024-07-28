package org.example.util;

import org.example.dto.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonManager {
    public Person createPerson(String personData) {

        String[] data = personData.split(";");

        if (data.length == 3) {
            try {
                return new Person(
                        data[0],
                        Integer.parseInt(data[1].replaceAll("[^0-9]", "")),
                        data[2]);
            } catch (NumberFormatException e) {
                System.err.println(e.getMessage());
                return new Person();
            }
        }
        return new Person();
    }

}
