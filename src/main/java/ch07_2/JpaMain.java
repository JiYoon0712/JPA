package ch07_2;

import ch05.Member6;
import ch05.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

// @MappedSuperclass : 공통 매핑 정보가 필요한 경우 사용
// 1. BaseEntity에 @MappedSuperclass 매핑
// 2. TEAM, MEMBER > extends BaseEntity

    public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setUsername("user1");
            member.setCreatedBy("Kim");
            member.setCreatedDate(LocalDateTime.now());

            em.persist(member);

            em.flush();
            em.clear();

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        // 실제 애플리케이션이 끝나면 EntityManagerFactory를 닫아줘야 함.
        emf.close();
    }
}
