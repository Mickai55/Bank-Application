package com.company;

public class Client extends Bank {

    protected Person person;
    protected String cardCode;
    protected String PIN;
    protected double balance;
    protected String currency;
    protected int no;

    public Client() { }
    
    public Client(Person person, String cardCode, String PIN, int balance, String currency, Bank bank) {
        this.person = person;
        this.cardCode = cardCode;
        this.PIN = PIN;
        this.balance = balance;
        this.currency = currency;
        this.no = numberOfClients;

        bank.numberOfClients++;
        bank.addClient(this);
    }

    @Override
    public String toString() {
        return "Client number " + no + " {" +
                "lastName = '" + person.getLastName() + '\'' +
                ", firstName = '" + person.getFirstName() + '\'' +
                ", balance = '" + balance + '\'' +
                '}';
    }

    public String name()
    {
        return person.getLastName() + " " + person.getFirstName();
    }

    public void displayBalance(){
        System.out.println("You have " + balance + " " + currency + ".");
    }

    public void addBalance(int x){ this.balance += x;}

    public void withdraw(int x) {
        if (this.balance - x < 0)
            System.out.println("(" + this.name() + ") " + "You don't have enough money." + "(" + x + ")");
        else if (x <= 200)
            this.balance -= x;
        else
            System.out.println("(" + this.name() + ") " + "You are not allowed to withdraw more than 200 in one go.");

    }

    public double getBalance(){
        return balance;
    }

    public void setBalance(double balance){ this.balance = balance; }

    public Person getPerson() {
        return person;
    }

    public void loan(int sum, Bank bank){
        balance += sum;
        bank.setBalance(bank.getBankBalance() - sum);

        System.out.print("(" + this.name() + ") ");
        System.out.println("You have received " + sum + ". You are in debt " + sum * 1.2 + " (20% commission) to the bank " + bank.getName() + ".");
    }

    public void sendMoney(Client client, double sum) {
        if (this.getBalance() - sum >= 0) {
            this.setBalance(this.getBalance() - sum);
            client.setBalance(client.getBalance() + sum);
        }
        else
            System.out.println("You don't have the requested sum disposable! (" + this.name() + ", " + sum + ").");
    }

    // method for the 'Savings' class
    public void sendToSavings(int i) {
    }
}
