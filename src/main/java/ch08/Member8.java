package ch08;


import javax.persistence.*;

@Entity
public class Member8 extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    // fetch = FetchType.LAZY : 지연로딩
    // : 프록시 객체를 조회하고, Member 클래스만 DB에서 조회한다.

    // fetch = FetchType.EAGER : 즉시로딩
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Team team;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
