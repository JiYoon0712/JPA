package ch09;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

// 값 타입 복사
public class Ex03 {
public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

    EntityManager em = emf.createEntityManager();

    EntityTransaction tx = em.getTransaction();
    tx.begin();

    try {
        // address 를 member, member2에서 공유 > 위험
        Address address = new Address("city", "street","10000");

        Member10 member = new Member10();
        member.setUsername("member1");
        member.setHomeAddress(address);
        em.persist(member);

        // 값 타입을 복사해서 사용
        Address copyAddress = new Address(address.getCity(),address.getStreet(),address.getZipcode());

        Member10 member2 = new Member10();
        member2.setUsername("member1");
        member2.setHomeAddress(copyAddress);
        em.persist(member2);

        // 의도대로 member만 변경
        // member.getHomeAddress().setCity("newCity");

        tx.commit();
    } catch (Exception e) {
        tx.rollback();
    } finally {
        em.close();
    }

    emf.close();
}
}
