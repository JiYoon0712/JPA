package ch03;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// @Entity(name = "Member")
// - JPA에서 사용할 엔티티 이름 설정
// - 기본값 : 클래스 이름 그대로
// - 같은 클래스 이름이 없으면 가급적 기본값을 사용한다.
@Entity
@Table(name = "MBR")    // name : 매핑할 테이블 이름
public class Member {
    @Id // 기본키
    private Long id;

    @Column(unique = true, length = 10)
    private String name;

    public Member(){
    }

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
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
}
