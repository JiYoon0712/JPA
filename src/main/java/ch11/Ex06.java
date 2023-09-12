package ch11;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

// [ 벌크 연산 ]
// ** 주의 사항 **
// - 영속성 컨텍스트를 무시하고 데이터베이스에 직접 쿼리
//    -- 해결 1) 벌크 연산을 먼저 실행
//    -- 해결 2) 벌크 연산 수행 후 영속성 컨텍스트 초기화
public class Ex06 {
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

            // FLUSH
            // 모든 회원의 나이를 20살로 바꾸기
            // executeUpdate 의 결과 : 영향받은 엔티티의 수
            int resultCount = em.createQuery("update Member12 m set m.age = 20")
                            .executeUpdate();

            System.out.println("resultCount = " + resultCount);

            // ★ 영속성 컨텍스트 초기화
            em.clear();

            Member12 findMember = em.find(Member12.class, member1.getId());

            System.out.println("findMember = " + findMember.getAge());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
