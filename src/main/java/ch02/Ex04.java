package ch02;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

// 엔티티 수정
public class Ex04 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 영속
            // JPA는 값을 바꾸면 트랜잭션이 커밋되는 시점에 변경을 반영한다.
            Member member = em.find(Member.class, 150L);
            member.setName("ZZZZZ");

            // 필요 없는 코드
            // em.persist(member);

            System.out.println("=========================");    

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
