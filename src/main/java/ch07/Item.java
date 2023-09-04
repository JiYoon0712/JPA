package ch07;

import javax.persistence.*;

/* [ 상속관계 매핑 ]
 1. 조인 전략 : @Inheritance(strategy = InheritanceType.JOINED)
 @DiscriminatorColumn : 테이블에 DTYPE 컬럼 추가. 엔티티명이 들어감. ( ex. Movie, Album, Book )
                      : (name = "DIS_TYPE")은 DTYPE이라는 컬럼명을 DIS_TYPE으로 변경


 2. 단일 테이블 전략 : @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
                   : @DiscriminatorColumn가 없어도 DTYPE 자동 필수 생성
 - MOVIE, BOOK, ALBUM 테이블이 각각 만들어지는 것이 아닌 ITEM 테이블 하나에 모든 컬럼 포함.
 - 한 테이블이다 보니 JOIN이 필요없고, INSERT, SELECT 모두 심플한 쿼리 > 성능 좋음.


 3. 구현 클래스마다 테이블 전략 : @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
                           : @DiscriminatorColumn X
 - Item 테이블을 없애고 ITEM의 각각의 속성을 하위 테이블들이 갖도록.
 - DTYPE 구분 필요 X > @DiscriminatorColumn 써도 DTYPE 사용 X. 필요하지 않음.

 */


// - Inheritance의 strategy 기본 : SINGLE_TABLE > 한 테이블에 저장. ( ITEM 테이블에 ALBUM, MOVIE, BOOK 컬럼이 모두 포함 )
// - abstract(추상 클래스) : abstract가 아니면 ITEM만 독단적으로 사용하는 경우 발생


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
public abstract class Item {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int price;

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
