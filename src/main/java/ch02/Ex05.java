package ch02;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

// 영속성 컨텍스트를 플러시하는 방법
// 1) em.flush() - 직접 호출
// 2) 트랜잭션 커밋 - 플러시 자동 호출
// 3) JPQL 쿼리 실행 - 플러시 자동 호출
public class Ex05 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 영속
            Member member = new Member(200L, "member200");
            em.persist(member);
            
            // 1) 직접 호출
            // 트랜잭션 COMMIT 이전에 미리 DB에 반영하거나, 쿼리를 보고싶은 경우 강제호출
            em.flush();

            System.out.println("=========================");
            tx.commit();    // 2) 트랜잭션 커밋시 플러시 자동 호출
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
