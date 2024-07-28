package org.example.dto;

import java.util.Objects;

public class Person {
    private String name;
    private String email;
    private Integer phoneNumber;

    public Person() {
    }

    public Person(String name, Integer phoneNumber, String email) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return phoneNumber == person.phoneNumber
                && Objects.equals(name, person.name) && Objects.equals(email, person.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, phoneNumber);
    }

    @Override
    public String toString() {
        return name + ";+" + phoneNumber + ';' + email ;
    }
}
