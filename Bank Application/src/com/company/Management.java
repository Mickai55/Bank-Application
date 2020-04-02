package com.company;
import java.util.Scanner;

public class Management{

    public void manage() {

        Bank bank1 = new Bank("BRD", 999999);
        Person p;

        Credit c1 = new Credit(p = new Person("Costel", "Ion", "Male"), "22222222", "1235", 100, "Lei", bank1);
        Savings c2 = new Savings(p = new Person("Voica", "Mihai", "Male"), "11111111", "1234", 10, "Lei", bank1);
        Premium c3 = new Premium(p = new Person("Ionescu", "Andrei", "Male"), "33333333", "1223", 200, "Lei", bank1);

        Login(bank1);

    }

    public void Login(Bank bank)
    {
        Scanner scan = new Scanner(System.in);
        int choice1 = 0;

        outerloop:
        while (true) {
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
                                        break;
                                    }

                                    // Withdraw
                                    case 2: {
                                        System.out.println("Sum to withdraw: ");
                                        int s = scan.nextInt();
                                        bank.clients.get(r).withdraw(s);
                                        break;
                                    }

                                    // Add balance
                                    case 3: {
                                        System.out.println("Sum to add: ");
                                        int s = scan.nextInt();
                                        bank.clients.get(r).addBalance(s);
                                        break;
                                    }

                                    // Loan
                                    case 4: {
                                        System.out.println("Sum to loan: ");
                                        int s = scan.nextInt();
                                        bank.clients.get(r).loan(s, bank);
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
                                            bank.clients.get(r).sendMoney(bank.clients.get(n), S);

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
                                                bank.convertToPremium(bank.clients.get(r));
                                                break;
                                            case 2:
                                                bank.convertToCredit(bank.clients.get(r));
                                                break;
                                            case 3:
                                                bank.convertToSavings(bank.clients.get(r));
                                                break;
                                            case 4:
                                                bank.convertToRevolving(bank.clients.get(r));
                                                break;
                                            case 5:
                                                bank.convertToInstallment(bank.clients.get(r));
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
                                            System.out.println(bank.clients.get(r).name() + " deleted!");
                                            bank.removeClientByNo(r);
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
                    Person p = new Person(firstName, lastName, sex);

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
                            c = new Client(p, cardCode, pin, 0, "Lei", bank);
                            break;
                        case 2:
                            c = new Premium(p, cardCode, pin, 0, "Lei", bank);
                            break;
                        case 3:
                            c = new Credit(p, cardCode, pin, 0, "Lei", bank);
                            break;
                        case 4:
                            c = new Savings(p, cardCode, pin, 0, "Lei", bank);
                            break;
                        case 5:
                            c = new Revolving(p, cardCode, pin, 0, "Lei", bank);
                            break;
                        case 6:
                            c = new Installment(p, cardCode, pin, 0, "Lei", bank);
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + choice4);
                    }


                    System.out.println("Hello, " + firstName + " " + lastName + "! (" + c.getClass().getSimpleName() + "). " + "Your unique card code is: " + cardCode + ".");
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
