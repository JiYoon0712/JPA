package ch09;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

// 값 타입 공유 참조 ( 이렇게 하면 안됨 )
public class Ex02 {
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

        Member10 member2 = new Member10();
        member2.setUsername("member1");
        member2.setHomeAddress(address);
        em.persist(member2);

        // 의도 : member의 city만 바꾸고 싶음
        // 결과 : member, member2 모두 newCity로 변경
        // > 문제 해결 : 불변 객체
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
