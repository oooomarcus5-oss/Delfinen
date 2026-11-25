package ui;

import domainmodel.ActivityForm;
import domainmodel.MemberCollection;
import domainmodel.MembershipType;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Userinterface {
    Scanner scanner = new Scanner(System.in);
    MemberCollection memberCollection = new MemberCollection();


    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("\n1. Opret nyt medlem");
            System.out.println("7. Se forventet kontingentindtægt");
            System.out.println("8. Registrer kontingentbetaling");
            System.out.println("0. Afslut");

            int choice = readInt("Vælg: ");
            switch (choice) {
                case 1 -> addMember();
                case 7 -> showExpectedIncome();
                case 8 -> registerPayment();
                case 2 -> running = false;
                default -> System.out.println("Ukendt valg, vælg igen.");
            }
        }
    }

    private void showExpectedIncome() {
        double total = memberCollection.calculateExpectedIncome();
        System.out.println("Forventet kontingentindtægt: " + total + " kr.");
    }

    private void registerPayment() {
        int memberId = readInt("Indtast medlemsnummer: ");
        if (memberCollection.registerPayment(memberId)) {
            System.out.println("Betaling registreret for medlem " + memberId + ".");
        } else {
            System.out.println("Fejl: medlemsnummer " + memberId + " findes ikke.");
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

    private void addMember() {
        System.out.println("Indtast fornavn: ");
        String firstName = scanner.nextLine();

        System.out.println("Indtast efternavn: ");
        String lastName = scanner.nextLine();

        try {
            System.out.println("Indtast fødselsdato (yyyy-mm-dd): ");
            LocalDate birthday = LocalDate.parse(scanner.nextLine());
            MembershipType membershipType = readMembershipType();
            ActivityForm activityForm = readActivityForm(membershipType);

            memberCollection.addMember(firstName, lastName, birthday, membershipType, activityForm);
            System.out.println("Medlem oprettet.");
        } catch (DateTimeParseException e) {
            System.out.println("Fejl: Fødselsdatoen har et ugyldigt format.");
        } catch (IllegalArgumentException e) {
            System.out.println("Fejl: " + e.getMessage());
        }
    }

    private MembershipType readMembershipType() {
        while (true) {
            int choice = readInt("Vælg medlemskab (1 = Aktivt, 2 = Passivt): ");
            if (choice == 1) {
                return MembershipType.ACTIVE;
            } else if (choice == 2) {
                return MembershipType.PASSIVE;
            }
            System.out.println("Ugyldigt valg. Prøv igen.");
        }
    }

    private ActivityForm readActivityForm(MembershipType type) {
        if (type == MembershipType.PASSIVE) {
            return ActivityForm.NONE;
        }
        while (true) {
            int choice = readInt("Vælg Aktivitets niveau (1= motionist, 2 = Konkurrencesvømmer): ");
            if (choice == 1) {
                return ActivityForm.EXERCISER;
            } else if (choice == 2) {
                return ActivityForm.COMPETITIVE;
            }
            System.out.println("Ugyldigt valg. Prøv igen.");
        }
    }
}

