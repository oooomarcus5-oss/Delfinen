package ui;

import java.util.Scanner;

public class Userinterface {
    Scanner scanner = new Scanner(System.in);


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

}


