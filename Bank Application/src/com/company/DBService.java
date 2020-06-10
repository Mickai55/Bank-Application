package com.company;

import java.sql.Timestamp;
import java.util.Date;

public class DBService {

    public static void setUp_DB() {
        SetUpData.setUpClients();
        SetUpData.setUpTimestamps();
        SetUpData.setUpBanks();
    }

    public static void readClients() {
        PersonRepository personRepository = new PersonRepository();

        int no = 0;
        String person = personRepository.getPerson(no);

        while (person != null)
        {
            String[] words = person.split(",");

            String lastName = words[0];
            String firstName = words[1];
            String sex = words[2];
            String cardCode = words[3];
            String pin = words[4];
            double balance = Double.parseDouble(words[5]);
            String currency = words[6];
            int bank = Integer.parseInt(words[7]);
            String type = words[8];

            Client c;

            switch (type) {
                case "Client":
                    c = new Client(new Person(lastName, firstName, sex), cardCode, pin, balance, currency, Management.banks.get(bank));
                    break;
                case "Premium":
                    c = new Premium(new Person(lastName, firstName, sex), cardCode, pin, balance, currency, Management.banks.get(bank));
                    break;
                case "Credit":
                    c = new Credit(new Person(lastName, firstName, sex), cardCode, pin, balance, currency, Management.banks.get(bank));
                    break;
                case "Savings":
                    c = new Savings(new Person(lastName, firstName, sex), cardCode, pin, balance, currency, Management.banks.get(bank));
                    break;
                case "Revolving":
                    c = new Revolving(new Person(lastName, firstName, sex), cardCode, pin, balance, currency, Management.banks.get(bank));
                    break;
                case "Installment":
                    c = new Installment(new Person(lastName, firstName, sex), cardCode, pin, balance, currency, Management.banks.get(bank));
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + type);
            }

            person = personRepository.getPerson(++no);
        }
    }

    public static void readBanks() {

        PersonRepository personRepository = new PersonRepository();

        int no = 0;
        String bank = personRepository.getBank(no);

        while (bank != null) {
            String[] words = bank.split(",");
            String name = words[0];
            double balance = Double.parseDouble(words[1]);
            Management.banks.add(new Bank(name, balance));

            bank = personRepository.getBank(++no);
        }
    }

    public static void writeTimestamp(String actionName) {
        SetUpData.addTimestamp(actionName, new Timestamp((new Date()).getTime()).toString());
    }

    public static void writePersonToDB(String Last_name, String First_name, String sex, String Card_code,
                                        String Pin, String Balance, String Currency, int Bank, String Type)
    {
        SetUpData.addPerson(Last_name, First_name, sex, Card_code, Pin, Balance, Currency, Bank, Type);
    }

    public static void changeBalance(String balance, String code) {
        PersonRepository personRepository = new PersonRepository();
        personRepository.updatePersonBalance(balance, code);
    }

    public static void changeType(String type, String code) {
        PersonRepository personRepository = new PersonRepository();
        personRepository.updatePersonType(type, code);
    }

    public static void delete(String code) {
        PersonRepository personRepository = new PersonRepository();
        personRepository.deletePerson(code);
    }

}
