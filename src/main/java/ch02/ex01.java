package ch02;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

// 비영속, 영속
public class Ex01 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 비영속
            Member member = new Member();
            member.setId(101L);
            member.setName("HelloJPA");

            // 영속
            System.out.println("=== BEFORE ===");
            em.persist(member);     // 여기서 DB 저장이 되는 것이 아님.
            // em.detach(member);      // 영속성 컨텍스트를 다시 지움.
            // em.remove(member);      // 실제 DB 삭제를 요청
            System.out.println("=== AFTER ===");

            // 조회를 했는데 DB의 SELECT 쿼리문이 보이지 않음.
            // 1차 캐시에 있는 것 먼저 조회했기 때문
            Member findMember = em.find(Member.class, 101L);
            System.out.println("findMember.id = " + findMember.getId());
            System.out.println("findMember.name = " + findMember.getName());

            tx.commit();    // 트랜잭션을 커밋하는 순간에 DB로
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
