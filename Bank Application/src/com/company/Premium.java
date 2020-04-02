package com.company;

public class Premium extends Client {

    public Premium() { }

    public Premium(Person person, String cardCode, String PIN, int balance, String currency, Bank bank){
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

    public void withdraw(int x) {
        if (this.balance - x < 0)
            System.out.println("(" + this.name() + ") " + "You don't have enough money." + "(" + x + ")");
        else if (x <= 400)
            this.setBalance(this.getBalance() - x);
        else
            System.out.println("(" + this.getName() + ") " + "You are not allowed to withdraw more than 400 in one go. (Premium)");

    }

}
