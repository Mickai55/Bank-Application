package com.company;

import java.io.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Scanner;

public class ServiceCSV {

    private static ServiceCSV instance = null;
    private ServiceCSV(){}

    public static ServiceCSV getInstance() {
        if(instance == null) {
            instance = new ServiceCSV();
        }
        return instance;
    }

    public static void writeFile(String text, String fileName) {
        try {
            FileWriter fw = new FileWriter(fileName, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.println(text);
            pw.flush();
            pw.close();
        }
        catch(Exception E){
            System.out.println("Failed to write to clients file.");
        }
    }

    public static void readCients(){
        File file = new File("clients.csv");
        try {
            Scanner inputStream = new Scanner(file);
            inputStream.next(); // first line is the header
            while (inputStream.hasNext()){
                String line = inputStream.next();
                String[] words = line.split(",");

                String lastName = words[0];
                String firstName = words[1];
                String sex = words[2];
                String cardCode = words[3];
                String pin = words[4];
                double balance = Double.parseDouble(words[5]);
                String currency = words[6];
                int bank = Integer.parseInt(words[7]);
                String type = words[8];

                Client c;

                switch (type) {
                    case "Client":
                        c = new Client(new Person(lastName, firstName, sex), cardCode, pin, balance, currency, Management.banks.get(bank));
                        break;
                    case "Premium":
                        c = new Premium(new Person(lastName, firstName, sex), cardCode, pin, balance, currency, Management.banks.get(bank));
                        break;
                    case "Credit":
                        c = new Credit(new Person(lastName, firstName, sex), cardCode, pin, balance, currency, Management.banks.get(bank));
                        break;
                    case "Savings":
                        c = new Savings(new Person(lastName, firstName, sex), cardCode, pin, balance, currency, Management.banks.get(bank));
                        break;
                    case "Revolving":
                        c = new Revolving(new Person(lastName, firstName, sex), cardCode, pin, balance, currency, Management.banks.get(bank));
                        break;
                    case "Installment":
                        c = new Installment(new Person(lastName, firstName, sex), cardCode, pin, balance, currency, Management.banks.get(bank));
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + type);
                }
            }
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void readBanks(){
        File file = new File("banks.csv");
        try {
            Scanner inputStream = new Scanner(file);
            inputStream.next(); // first line is the header
            while (inputStream.hasNext()){
                String line = inputStream.next();
                String[] words = line.split(",");

                String name = words[0];
                double balance = Double.parseDouble(words[1]);

                Management.banks.add(new Bank(name, balance));
            }
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void writeTimestamp(String actionName) {
        try {
            FileWriter fw = new FileWriter("timestamps.csv", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.println(actionName + ", " + new Timestamp((new Date()).getTime()).toString());
            pw.flush();
            pw.close();
        }
        catch(Exception E){
            System.out.println("Failed to write to timestamps file.");
        }
    }

    public void replace(String filePath, String code, String newVal, String whatToChange){
        File file = new File(filePath);
        File tempFile = new File("myTempFile.csv");

        try {
            Scanner inputStream = new Scanner(file);
            while (inputStream.hasNext()) {
                String line = inputStream.next();
                String[] words = line.split(",");
                switch (whatToChange) {
                    case "Balance":
                        if (words[3].equals(code)) {
                            String newLine = words[0] + "," + words[1] + "," + words[2] + "," + words[3] + "," + words[4] + "," + newVal + "," + words[6] + "," + words[7] + "," + words[8];
                            writeFile(newLine, "myTempFile.csv");
                        } else {
                            writeFile(line, "myTempFile.csv");
                        }
                        break;
                    case "Type":
                        if (words[3].equals(code)) {
                            String newLine = words[0] + "," + words[1] + "," + words[2] + "," + words[3] + "," + words[4] + "," + words[5] + "," + words[6] + "," + words[7] + "," + newVal;
                            writeFile(newLine, "myTempFile.csv");
                        } else {
                            writeFile(line, "myTempFile.csv");
                        }
                        break;
                    case "Delete":
                        if (!words[3].equals(code)) {
                            writeFile(line, "myTempFile.csv");
                        }
                        break;
                }
            }
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        file.delete();
        tempFile.renameTo(file);
    }
}
