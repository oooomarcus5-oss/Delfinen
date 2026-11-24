package domainmodel;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Member {
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private MembershipType membershipType;
    private ActivityForm activityForm;
    private static int nextId = 1;
    private int memberId;
    private boolean paid;

    public Member(String firstName, String lastName, LocalDate birthday, MembershipType membershipType, ActivityForm activityForm) {
        this.memberId = nextId;
        nextId++;
        this.firstName = firstName;
        this.lastName = lastName;
        setBirthday(birthday);
        setMembershipType(membershipType);
        setActivityForm(activityForm);
        this.memberId = nextId;
        nextId++;
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
    private void validateMembership(MembershipType type, ActivityForm form){
        if (type == MembershipType.PASSIVE && form != ActivityForm.NONE){
            throw new IllegalArgumentException("Et passivt medlem kan ikke være Motionist eller Konkurrencesvømmer");
        }
        if (type == MembershipType.ACTIVE && form == ActivityForm.NONE){
            throw new IllegalArgumentException("Et Aktivt medlem skal enten være Motionist eller Konkurrencesvømmer");
        }
    }

    public void setMembershipType(MembershipType membershipType){
        this.membershipType = membershipType;
    }
    public MembershipType getMembershipType() {
        return membershipType;
    }
    public void setActivityForm(ActivityForm activityForm){
        this.activityForm = activityForm;
    }
    public ActivityForm getActivityForm() {
        return activityForm;
    }

    public String getInfo() {
        String result = "\n";
        result += "Medlemsnummer: " + this.memberId + ", ";
        result += "\nNavn: " + this.getName();
        result += "\nAlder: " + this.getAge(LocalDate.now()) + " år";
        return result;
    }

    public boolean isActive() {
        return membershipType == MembershipType.ACTIVE;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }


    public double getAnnualFee() {
        if (!isActive()) return 500;
        int age = getAge(LocalDate.now());
        if (age < 18) return 1000;
        if (age >= 60) return 1600 * 0.75;
        return 1600;
    }
}
