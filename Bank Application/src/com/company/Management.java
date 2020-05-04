package com.company;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Management{

    ServiceCSV service = ServiceCSV.getInstance();
    public static List<Bank> banks = new ArrayList<>(); // private!!

    public void manage() {

        service.readBanks();
        service.readCients();

        Menu(banks.get(0));
    }

    public void Menu(Bank bank)
    {
        Scanner scan = new Scanner(System.in);
        int choice1 = 0;

        outerloop:
        while (true) {
            System.out.println("Bank: " +  bank.getName());
            System.out.println("Choose:");
            System.out.println("1 -> Login.");
            System.out.println("2 -> Create a new account.");
            System.out.println("3 -> See clients.");
            System.out.println("4 -> Exit.");
            System.out.print("Choice ->");
            choice1 = scan.nextInt();

            switch (choice1) {

                //Login
                case 1: {
                    scan = new Scanner(System.in);
                    System.out.print("Card Code:");
                    String cc = scan.nextLine();
                    System.out.print("PIN:");
                    String pin = scan.nextLine();

                    // Searching client
                    int r = bank.ClientLogin(cc, pin);
                    switch (r) {
                        case -1:
                            System.out.println("Wrong PIN.");
                            break;
                        case -2:
                            System.out.println("Client not found.");
                            break;
                        default: {
                            int choice2 = 0;
                            System.out.println("\nLogin successful!");
                            service.writeTimestamp(bank.clients.get(r).name() + " logged in");
                            System.out.println("Welcome " + bank.clients.get(r).name() + "! (" + bank.clients.get(r).getClass().getSimpleName() + ")");

                            outerloop2:
                            while (true) {
                                System.out.println("Choose:");
                                System.out.println("1 -> Display balance.");
                                System.out.println("2 -> Withdraw.");
                                System.out.println("3 -> Add balance.");
                                System.out.println("4 -> Make a loan.");
                                System.out.println("5 -> Send money.");
                                System.out.println("6 -> Convert.");
                                System.out.println("7 -> Delete account.");
                                System.out.println("8 -> Log Out.");
                                System.out.print("Choice ->");
                                choice2 = scan.nextInt();

                                switch (choice2) {

                                    // Display balance
                                    case 1: {
                                        bank.clients.get(r).displayBalance();
                                        service.writeTimestamp(bank.clients.get(r).name() + " displayed balance");
                                        break;
                                    }

                                    // Withdraw
                                    case 2: {
                                        System.out.println("Sum to withdraw: ");
                                        int s = scan.nextInt();
                                        if (bank.clients.get(r).withdraw(s)) {
                                            service.writeTimestamp(bank.clients.get(r).name() + " withdrawed " + s + " " + bank.clients.get(r).currency);
                                            service.replace("clients.csv", bank.clients.get(r).cardCode, String.valueOf(bank.clients.get(r).balance), "Balance");
                                        }
                                        else
                                            service.writeTimestamp(bank.clients.get(r).name() + " failed to withdraw " + s + " " + bank.clients.get(r).currency);
                                        break;
                                    }

                                    // Add balance
                                    case 3: {
                                        System.out.println("Sum to add: ");
                                        int s = scan.nextInt();
                                        bank.clients.get(r).addBalance(s);
                                        service.writeTimestamp(bank.clients.get(r).name() + " added " + s + " " + bank.clients.get(r).currency);
                                        service.replace("clients.csv", bank.clients.get(r).cardCode, String.valueOf(bank.clients.get(r).balance), "Balance");
                                        break;
                                    }

                                    // Loan
                                    case 4: {
                                        System.out.println("Sum to loan: ");
                                        int s = scan.nextInt();
                                        if (bank.clients.get(r).loan(s, bank)) {
                                            service.writeTimestamp(bank.clients.get(r).name() + " loaned " + s + " " + bank.clients.get(r).currency);
                                            service.replace("clients.csv", bank.clients.get(r).cardCode, String.valueOf(bank.clients.get(r).balance), "Balance");
                                        }
                                        else
                                            service.writeTimestamp(bank.clients.get(r).name() + " failed to loan " + s + " " + bank.clients.get(r).currency);
                                        break;
                                    }

                                    // Send Money
                                    case 5: {
                                        System.out.println("Enter the Card Code of the person you want to send money to:");
                                        scan = new Scanner(System.in);
                                        String s = scan.nextLine();
                                        int n = bank.findClient(s);
                                        if (n == -1)
                                            System.out.println("Client not found!");
                                        else {
                                            System.out.println("Sum to send: ");
                                            int S = scan.nextInt();
                                            if (bank.clients.get(r).sendMoney(bank.clients.get(n), S)) {
                                                service.writeTimestamp(bank.clients.get(r).name() + " sent " + S + " " + bank.clients.get(r).currency +
                                                        " to " + bank.clients.get(n).name());

                                                service.replace("clients.csv", bank.clients.get(r).cardCode, String.valueOf(bank.clients.get(r).balance), "Balance");
                                                service.replace("clients.csv", bank.clients.get(n).cardCode, String.valueOf(bank.clients.get(n).balance), "Balance");
                                            }
                                            else
                                                service.writeTimestamp(bank.clients.get(r).name() + " failed to send " + S + " " + bank.clients.get(r).currency +
                                                        " to " + bank.clients.get(n).name());

                                            System.out.println("Success!");
                                        }

                                        break;
                                    }

                                    // Convert account
                                    case 6: {
                                        System.out.println("To which account type you want to convert:");
                                        System.out.println("1 -> Premium");
                                        System.out.println("2 -> Credit");
                                        System.out.println("3 -> Savings");
                                        System.out.println("4 -> Revolving");
                                        System.out.println("5 -> Installment");
                                        System.out.print("Choice ->");

                                        int choice3 = scan.nextInt();

                                        switch (choice3) {
                                            case 1:
                                                service.writeTimestamp(bank.clients.get(r).name() + " converted to Premium");
                                                service.replace("clients.csv", bank.clients.get(r).cardCode, "Premium", "Type");
                                                bank.convertToPremium(bank.clients.get(r));
                                                break;
                                            case 2:
                                                bank.convertToCredit(bank.clients.get(r));
                                                service.writeTimestamp(bank.clients.get(r).name() + " converted to Credit");
                                                service.replace("clients.csv", bank.clients.get(r).cardCode, "Credit", "Type");
                                                break;
                                            case 3:
                                                bank.convertToSavings(bank.clients.get(r));
                                                service.writeTimestamp(bank.clients.get(r).name() + " converted to Savings");
                                                service.replace("clients.csv", bank.clients.get(r).cardCode, "Savings", "Type");
                                                break;
                                            case 4:
                                                bank.convertToRevolving(bank.clients.get(r));
                                                service.writeTimestamp(bank.clients.get(r).name() + " converted to Revolving");
                                                service.replace("clients.csv", bank.clients.get(r).cardCode, "Revolving", "Type");
                                                break;
                                            case 5:
                                                bank.convertToInstallment(bank.clients.get(r));
                                                service.writeTimestamp(bank.clients.get(r).name() + " converted to Installment");
                                                service.replace("clients.csv", bank.clients.get(r).cardCode, "Installment", "Type");
                                                break;
                                            default:
                                                throw new IllegalStateException("Unexpected value: " + choice3);
                                        }

                                        break;
                                    }

                                    // Delete account
                                    case 7: {
                                        scan = new Scanner(System.in);
                                        System.out.println("Are you sure? (y/n)");
                                        String s = scan.nextLine();
                                        if (s.equals("y")) {
                                            service.writeTimestamp(bank.clients.get(r).name() + " deleted account");
                                            System.out.println(bank.clients.get(r).name() + " deleted!");
                                            bank.removeClientByNo(r);
                                            service.replace("clients.csv", bank.clients.get(r).cardCode, "???", "Delete");
                                        }
                                        break;
                                    }

                                    case 8:
                                        break outerloop2;
                                    default:
                                        throw new IllegalStateException("Unexpected value: " + choice2);
                                }
                            }
                        }
                    }
                    break;
                }

                //Create a new account
                case 2: {
                    scan = new Scanner(System.in);
                    System.out.print("Last Name:");
                    String lastName = scan.nextLine();
                    System.out.print("First Name:");
                    String firstName = scan.nextLine();
                    System.out.print("Sex:");
                    String sex = scan.nextLine();
                    String cardCode = bank.generateCode();
                    System.out.print("Pin:");
                    String pin = scan.nextLine();

                    System.out.println("Choose the type of your account:");
                    System.out.println("1 -> Client");
                    System.out.println("2 -> Premium");
                    System.out.println("3 -> Credit");
                    System.out.println("4 -> Savings");
                    System.out.println("5 -> Revolving");
                    System.out.println("6 -> Installment");
                    System.out.print("Choice ->");
                    int choice4 = scan.nextInt();

                    Client c;

                    switch (choice4) {
                        case 1:
                            c = new Client(new Person(lastName, firstName, sex), cardCode, pin, 0, "Lei", bank);
                            break;
                        case 2:
                            c = new Premium(new Person(lastName, firstName, sex), cardCode, pin, 0, "Lei", bank);
                            break;
                        case 3:
                            c = new Credit(new Person(lastName, firstName, sex), cardCode, pin, 0, "Lei", bank);
                            break;
                        case 4:
                            c = new Savings(new Person(lastName, firstName, sex), cardCode, pin, 0, "Lei", bank);
                            break;
                        case 5:
                            c = new Revolving(new Person(lastName, firstName, sex), cardCode, pin, 0, "Lei", bank);
                            break;
                        case 6:
                            c = new Installment(new Person(lastName, firstName, sex), cardCode, pin, 0, "Lei", bank);
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + choice4);
                    }

                    // daca nu este utilizat deja numele???
                    service.writeFile(lastName + "," + firstName + "," + sex + "," + cardCode + "," + pin + "," + 0 + "," + "Lei" + "," + bank.indexOf + "," + c.getClass().getSimpleName(), "clients.csv");

                    service.writeTimestamp(lastName + firstName + " created a new account");
                    System.out.println("Hello, " + lastName + " " + firstName + "! (" + c.getClass().getSimpleName() + "). " + "Your unique card code is: " + cardCode + ".");
                    System.out.println("Now you can login!");
                    break;
                }

                //Bank clients
                case 3: {
                    manageClients(bank);
                    break;
                }

                // Exit
                case 4:
                    break outerloop;
                default:
                    throw new IllegalStateException("Unexpected value: " + choice1);

            }
        }
    }


    public void manageClients(Bank bank)
    {
        Scanner scan = new Scanner(System.in);
        int choice1 = 0;
        while (choice1 <= 3) {
            System.out.println("Choose:");
            System.out.println("1 -> Display all clients.");
            System.out.println("2 -> Display all clients sorted by name.");
            System.out.println("3 -> Display all clients sorted by balance.");
            System.out.println("4 -> Back.");
            System.out.print("Choice ->");
            choice1 = scan.nextInt();

            switch (choice1) {
                case 1:
                    bank.displayClients();
                    break;
                case 2:
                    bank.sortClientsByName();
                    bank.displayClients();
                    bank.sortClientsDefault();
                    break;
                case 3:
                    bank.sortClientsByBalance();
                    bank.displayClients();
                    bank.sortClientsDefault();
                    break;
                case 4:
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + choice1);
            }
        }
    }
}
