package domainmodel;

import java.time.LocalDate;
import java.util.ArrayList;

public class MemberCollection {
    private ArrayList<Member> members;

    public MemberCollection(){
        members = new ArrayList<>();
    }

    public void addMember(String firstName, String lastName, LocalDate birthday){
        Member member = new Member(firstName, lastName, birthday);
        members.add(member);
    }
    public ArrayList<Member> getMembers(){
        return members;
    }



}
