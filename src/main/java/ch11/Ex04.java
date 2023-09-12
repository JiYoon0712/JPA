package ch11;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Ex04 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team2 teamA = new Team2();
            teamA.setName("팀A");
            em.persist(teamA);

            Team2 teamB = new Team2();
            teamB.setName("팀B");
            em.persist(teamB);

            Member12 member1 = new Member12();
            member1.setUsername("회원1");
            member1.setTeam(teamA);
            em.persist(member1);

            Member12 member2 = new Member12();
            member2.setUsername("회원2");
            member2.setTeam(teamA);
            em.persist(member2);

            Member12 member3 = new Member12();
            member3.setUsername("회원3");
            member3.setTeam(teamB);
            em.persist(member3);

            em.flush();
            em.clear();

            // 엔티티를 파라미터로 전달 > 실행된 SQL을 확인하면 식별자를 전달한 것과 동일.
            // String query = "select m from Member12  m where m.id = :memberId"
            String query = "select m from Member12  m where m = :member";

            Member12 findMember = em.createQuery(query, Member12.class)
                            .setParameter("member", member1)
                                    .getSingleResult();

            System.out.println("findMember : " + findMember);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
