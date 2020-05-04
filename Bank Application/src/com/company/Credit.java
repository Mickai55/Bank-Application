package com.company;

public class Credit extends Client {

    public Credit() { }

    public Credit(Person person, String cardCode, String PIN, double balance, String currency, Bank bank){
        super(person, cardCode, PIN, balance, currency, bank);
    }

    public Credit(Client x){
        this.person = x.getPerson();
        this.cardCode = x.cardCode;
        this.PIN = x.PIN;
        this.balance = x.balance;
        this.currency = x.currency;
        this.no = x.no;
    }

    @Override
    public String toString() {
        return "Client number " + no + " {" +
                "lastName = '" + this.getPerson().getLastName() + '\'' +
                ", firstName = '" + this.getPerson().getFirstName() + '\'' +
                ", balance = '" + this.getBalance() + '\'' +
                "} (Credit)";
    }

    public boolean withdraw(int x) {
        if (x <= 200) {
            this.balance -= x;
        }
        else{
            System.out.println("(" + this.name() + ") " + "You are not allowed to withdraw more than 200 in one go.");
            return false;
        }

        if (this.balance < 0)
            System.out.println("(" + this.name() + ") " + "You have negative balance!." + "(" + this.balance + ")");

        return true;
    }

}
