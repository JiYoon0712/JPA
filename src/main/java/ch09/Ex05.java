package ch09;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

// 동일성(identity) 비교 : 인스턴스의 참조 값을 비교, == 사용
// 동등성(equivalence) 비교 : 인스턴스의 값을 비교, equals() 사용
public class Ex05 {
public static void main(String[] args) {
    int a = 10;
    int b = 10;

    System.out.println("a == b : " + (a==b));  // true

    // =====================================================================

    Address address1 = new Address("city", "street","10000");
    Address address2 = new Address("city", "street","10000");

    System.out.println("address1 == address2 : " + (address1 == address2)); // false
    System.out.println("address1 equals address2 : " + (address1.equals(address2))); // equals 재정의 전 : false => 재정의 후 : true

}
}
