package ch04;

import jdk.swing.interop.SwingInterOpUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


// 기본키 매핑 방법 - 자동생성(1) : INDENTITY
public class Ex02 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member2 member = new Member2();
            member.setUsername("C");

            // INDENTITY는 DB에 넣어야 PK값을 확인할 수 있음
            // > INDENTITY 전략에서만 em.persist를 호출하는 시점에 바로 DB에 INSERT 쿼리를 날림 > 다른 전략에서는 tx.commit 시점에 쿼리를 날림.
            System.out.println("======================");
            em.persist(member);
            System.out.println("member.id = " + member.getId());
            System.out.println("======================");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
