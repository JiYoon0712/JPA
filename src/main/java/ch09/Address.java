package ch09;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

// @Embeddable : 값 타입을 정의하는 곳에 표시
// 임베디드 타입안에 엔티티 포함 가능. ex) private Member9 member;
@Embeddable
public class Address {
    private String city;
    private String street;

    @Column(name = "ZIPCODE")
    private String zipcode;

    public Address(){
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getZipcode() {
        return zipcode;
    }

    // 생성자로만 값을 설정하고 값을 변경할 수 없도록. => 불변 객체
    // 1) setter를 삭제한다.
/*    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
*/
    
    // 2) setter를 private으로 설정
    private void setCity(String city) {
        this.city = city;
    }

    private void setStreet(String street) {
        this.street = street;
    }

    private void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    // equals를 재정의.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(city, address.city) && Objects.equals(street, address.street) && Objects.equals(zipcode, address.zipcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, zipcode);
    }
}
