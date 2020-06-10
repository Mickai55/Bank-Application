package com.company;

public class Client extends Bank {

    protected Person person;
    protected String cardCode;
    protected String PIN;
    protected double balance;
    protected String currency;
    protected int no;

    public Client() { }
    
    public Client(Person person, String cardCode, String PIN, double balance, String currency, Bank bank) {
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

    public void addBalance(double x){ this.balance += x; }

    public boolean withdraw(double x) {
        if (this.balance - x < 0) {
            System.out.println("(" + this.name() + ") " + "You don't have enough money." + "(" + x + ")");
            return false;
        }
        else if (x <= 200) {
            this.balance -= x;
            return true;
//            File file = new File("clients.csv");
//            try {
//                Scanner inputStream = new Scanner(file);
//                inputStream.next(); // first line is the header
//                while (inputStream.hasNext()){
//                    String line = inputStream.next();
//                    String[] words = line.split(",");
//                    String code = words[3];
//                    int money = Integer.parseInt(words[5]);
//                    if (code.equals(this.cardCode))
//                        money -= x;
//
//                }
//                inputStream.close();
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
        }
        else {
            System.out.println("(" + this.name() + ") " + "You are not allowed to withdraw more than 200 in one go.");
            return false;
        }

    }

    public double getBalance(){
        return balance;
    }

    public void setBalance(double balance){ this.balance = balance; }

    public Person getPerson() {
        return person;
    }

    public boolean loan(double sum, Bank bank){
        if (sum < 1000) {
            balance += sum;
            bank.setBalance(bank.getBankBalance() - sum);

            System.out.print("(" + this.name() + ") ");
            System.out.println("You have received " + sum + ". You are in debt " + sum * 1.2 + " (20% commission) to the bank " + bank.getName() + ".");

            return true;
        }
        else
            System.out.println("You are not allowed to loan that sum.");
        return false;
    }

    public boolean sendMoney(Client client, double sum) {
        if (this.getBalance() - sum >= 0) {
            this.setBalance(this.getBalance() - sum);
            client.setBalance(client.getBalance() + sum);
            return true;
        }
        else
            System.out.println("You don't have the requested sum disposable! (" + this.name() + ", " + sum + ").");
        return false;
    }
}
