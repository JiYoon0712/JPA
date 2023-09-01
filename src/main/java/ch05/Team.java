package ch05;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;
    private String name;

    // mappedBy : 뭐랑 연결되어 있는지
    @OneToMany(mappedBy = "team")
    private List<Member6> members = new ArrayList<>();

    public void addMember(Member6 member){
        member.setTeam(this);
        members.add(member);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Member6> getMembers() {
        return members;
    }

    public void setMembers(List<Member6> members) {
        this.members = members;
    }
}
