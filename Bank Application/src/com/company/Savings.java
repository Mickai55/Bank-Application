package com.company;

public class Savings extends Client {

    private double savings;

    public Savings() { }

    public Savings(Person person, String cardCode, String PIN, double balance, String currency, Bank bank){
        super(person, cardCode, PIN, balance, currency, bank);
        this.savings = 0;
    }

    public Savings(Client x){
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
                ", savings = '" + this.savings + '\'' +
                "} (Savings)";
    }

    public double getSavings() {
        return savings;
    }

    public void setSavings(double savings) {
        this.savings = savings;
    }

    public void sendToSavings(int x){

        if (x <= this.balance) {
            this.balance -= x;
            this.savings += x;
        }

    }

}
