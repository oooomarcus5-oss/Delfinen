package domainmodel;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Member {
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private static int nextId = 1;
    private int memberId;

    public Member(String firstName, String lastName, LocalDate birthday) {
        this.memberId = nextId;
        nextId++;
        this.firstName = firstName;
        this.lastName = lastName;
        setBirthday(birthday);
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getMemberId() {
        return memberId;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public int getAge(LocalDate date) {
        long years = ChronoUnit.YEARS.between(birthday, date);
        return (int) years;
    }

    public void setBirthday(LocalDate birthday) {
        if (birthday.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Fødselsdag må ikke være i fremtiden.");
        }
        this.birthday = birthday;
    }

    public String getInfo() {
        String result = "\n";
        result += "Medlemsnummer: " + this.memberId + ", ";
        result += "\nNavn: " + this.getName();
        result += "\nAlder: " + this.getAge(LocalDate.now()) + " år";
        return result;
    }

    private boolean active = true;
    private boolean paid;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }


    public double getAnnualFee() {
        if (!active) return 500;
        int age = getAge(LocalDate.now());
        if (age < 18) return 1000;
        if (age >= 60) return 1600 * 0.75;
        return 1600;
    }
}
