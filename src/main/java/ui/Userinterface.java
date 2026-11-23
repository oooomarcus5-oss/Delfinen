package ui;

import domainmodel.MemberCollection;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Userinterface {
    Scanner scanner = new Scanner(System.in);
    MemberCollection memberCollection = new MemberCollection();


    public void start(){
        System.out.println("1. Opret nyt medlem");
        System.out.println("0. Afslut");

        boolean running = true;
        int choice = readInt("Vælg: ");
        while(running){
            switch(choice){
                case 1 -> System.out.println("Opret medlem");
                case 2 -> running = false;
                default -> System.out.println("Ukendt valg, vælg igen.");
            }
        }
    }


    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("\"" + input + "\" er ikke et helt tal. Prøv igen.");
            }
        }
    }
    private void addMember(){
        System.out.println("Indtast fornavn: ");
        String firstName = scanner.nextLine();

        System.out.println("Indtast efternavn: ");
        String lastName = scanner.nextLine();

        try{
            System.out.println("Indtast fødselsdato (yyyy-mm-dd): ");
            LocalDate birthday = LocalDate.parse(scanner.nextLine());

            memberCollection.addMember(firstName, lastName, birthday);
            System.out.println("Medlem oprettet.");
        }
        catch(DateTimeParseException e){
            System.out.println("Fejl: Fødselsdatoen har et ugyldigt format.");
        }
        catch (IllegalArgumentException e) {
            System.out.println("Fejl: " + e.getMessage());
        }
    }

}


