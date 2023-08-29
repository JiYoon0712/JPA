package ch04;

import javax.persistence.*;

// 기본키 매핑 방법 - 자동생성(2) : SEQUENCE
@Entity
@SequenceGenerator(name="member_seq_generator", sequenceName = "member_seq")
public class Member3 {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_generator" )
    private Long id;

    @Column(name = "name",nullable = false)
    private String username;

    public Member3(){
    }

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

}
