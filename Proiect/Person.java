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

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

}
