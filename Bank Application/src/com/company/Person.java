package com.company;

public class Person {

    String lastName;
    String firstName;
    String sex;

    public Person(String lastName, String firstName, String sex) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.sex = sex;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

}
