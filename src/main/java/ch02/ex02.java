package ch02;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ex02 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 영속
            // select문이 1번만 실행. 2번째부터는 1차 캐시에서 조회
            Member findMember1 = em.find(Member.class, 101L);
            Member findMember2 = em.find(Member.class, 101L);
            
            // 영속 엔티티의 동일성 보장
            System.out.println("result = " + (findMember1 == findMember2));     // result = true

            tx.commit();    // 트랜잭션을 커밋하는 순간에 DB로
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
