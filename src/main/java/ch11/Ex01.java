package ch11;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Ex01 {
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

            String query = "select m From Member12 m join fetch m.team";

            List<Member12> result = em.createQuery(query, Member12.class)
                    .getResultList();

            for(Member12 member : result){
                System.out.println("member = " + member.getUsername() + ", " + member.getTeam().getName());
                // 회원1, 팀A(SQL)
                // 회원2, 팀A(1차캐시)
                // 회원3, 팀B(SQL)
            }


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}