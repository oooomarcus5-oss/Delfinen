package domainmodel;

import java.time.LocalDate;
import java.util.ArrayList;

public class MemberCollection {
    private ArrayList<Member> members;

    public MemberCollection(){
        members = new ArrayList<>();
    }

    public void addMember(String firstName, String lastName, LocalDate birthday, MembershipType membershipType, ActivityForm activityForm){
        Member member = new Member(firstName, lastName, birthday, membershipType, activityForm);
        members.add(member);
    }
    public ArrayList<Member> getMembers(){
        return members;
    }


    public double calculateExpectedIncome() {
        double sum = 0;
        for (Member m : members) {
            sum += m.getAnnualFee();
        }
        return sum;
    }
    public boolean registerPayment(int memberId) {
        for (Member m : members) {
            if (m.getMemberId() == memberId) {
                m.setPaid(true);
                return true;
            }
        }
        return false;
    }
}
