package com.company;

public class Premium extends Client {

    public Premium() { }

    public Premium(Person person, String cardCode, String PIN, double balance, String currency, Bank bank){
        super(person, cardCode, PIN, balance, currency, bank);
    }

    public Premium(Client x){
        this.person = x.getPerson();
        this.cardCode = x.cardCode;
        this.PIN = x.PIN;
        this.balance = x.balance;
        this.currency = x.currency;
        this.no = x.no;
        //bank??
    }

    @Override
    public String toString() {
        return "Client number " + no + " {" +
                "lastName = '" + this.getPerson().getLastName() + '\'' +
                ", firstName = '" + this.getPerson().getFirstName() + '\'' +
                ", balance = '" + this.getBalance() + '\'' +
                "} (Premium)";
    }

    public boolean withdraw(int x) {
        if (this.balance - x < 0) {
            System.out.println("(" + this.name() + ") " + "You don't have enough money." + "(" + x + ")");
            return false;
        }
        else if (x <= 400) {
            this.balance -= x;
            return true;
        }
        else {
            System.out.println("(" + this.name() + ") " + "You are not allowed to withdraw more than 200 in one go.");
            return false;
        }

    }

}
