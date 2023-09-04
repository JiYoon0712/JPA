package ch08;

import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

// [ 지연로딩 ]
public class Ex07 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member8 member1 = new Member8();
            member1.setUsername("member1");
            member1.setTeam(team);

            em.persist(member1);

            em.flush();
            em.clear();

            Member8 m = em.find(Member8.class, member1.getId());    // fetch = FetchType.LAZY > Member8만 조회
                                                                    // ( fetch = FetchType.EAGER > Member8와 Team를 조인해서 같이 조회 > 프록시 필요 없음. )

            System.out.println("m = " + m.getTeam().getClass());    // LAZY > Proxy. m = class ch08.Team$HibernateProxy$PRfQOxEv

            System.out.println("=============");
            m.getTeam().getName();  // LAZY > 초기화. 실제 Team의 속성을 사용한 시점에 Proxy 객체가 초기화되면서 DB에서 값을 가져옴.
            System.out.println("=============");
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }  

        emf.close();

    }
}
