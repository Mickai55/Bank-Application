package com.company;

public class Installment extends Client {

    public Installment() { }

    public Installment(Person person, String cardCode, String PIN, double balance, String currency, Bank bank){
        super(person, cardCode, PIN, balance, currency, bank);
    }

    public Installment(Client x){
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

    public boolean sendMoney(Client client, double sum) {
        if (this.getBalance() - sum >= 0) {
            this.setBalance(this.getBalance() - sum);
            client.setBalance(client.getBalance() + 1.05 * sum);
            return true;
        }
        else
            System.out.println("You don't have the requested sum disposable! (" + this.name() + ", " + sum + ").");
        return false;
    }
}
