package ch09;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class Ex04 {
public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

    EntityManager em = emf.createEntityManager();

    EntityTransaction tx = em.getTransaction();
    tx.begin();

    try {
        Address address = new Address("city", "street","10000");

        Member10 member = new Member10();
        member.setUsername("member1");
        member.setHomeAddress(address);
        em.persist(member);

        // 불변 변경 이후 > 값 변경하고 싶은 경우
        Address newAddress = new Address("NewCity",address.getStreet(),address.getZipcode());
        member.setHomeAddress(newAddress);


        tx.commit();
    } catch (Exception e) {
        tx.rollback();
    } finally {
        em.close();
    }

    emf.close();
}
}
