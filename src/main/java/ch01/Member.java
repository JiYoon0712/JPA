package ch01;

import javax.persistence.Entity;
import javax.persistence.Id;

// JPA 처음 로딩될 때 인식
@Entity
public class Member {

    @Id // 기본키
    private Long id;
    private String name;

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
