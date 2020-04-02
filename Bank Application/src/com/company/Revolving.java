package com.company;

public class Revolving extends Client {

    public Revolving() { }

    public Revolving(Person person, String cardCode, String PIN, int balance, String currency, Bank bank){
        super(person, cardCode, PIN, balance, currency, bank);
    }

    public Revolving(Client x){
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
                "} (Revolving)";
    }

    public void loan(int sum, Bank bank){
        balance += sum;
        bank.setBalance(bank.getBankBalance() - sum);

        System.out.print("(" + this.name() + ") ");
        System.out.println("You have received " + sum + ". You are in debt " + sum * 1.05 + " (5% commission) to the bank " + bank.getName() + ".");
    }
}
