package ch04;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Member {
    @Id
    private Long id;

    // DB 컬럼명 : name, 객체 이름 : username
    @Column(name = "name")
    private String username;

    // DB에 Integer에 가장 적절한 타입으로.
    private Integer age;

    // DB에는 EnumType이 없음. 어노테이션 사용해주면 알아서.
    // ★ EnumType 기본값이 ORIDINAL > 반드시 EnumType.STRING로 설정.
    @Enumerated(EnumType.STRING)
    private RoleType roleType;



    // 자바 Date 타입 : 날짜 시간 모두 포함
    // DB에는 DATE(날짜), TIME(시간), TIMESTAMP(날짜,시간) 구분해서 사용
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    // 최신) LocalDate, LocalDateTime는 매핑 생략 가능.
    private LocalDate testLocalDate;
    private LocalDateTime testLocalDateTime;



    // 지정 속성 없음.
    // 매핑 문자면 CLOB, 나머지는 BLOB으로 매핑
    @Lob
    private String description;

    public Member(){
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

}
