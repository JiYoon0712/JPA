package ch05;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

// ★ 양방향 관계에서는 양쪽 다 세팅.
public class Ex02 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member6 member = new Member6();
            member.setUsername("member1");
            // member.changeTeam(team);   // **이거 넣거나** 연관관계 주인 : member
           // team.getMembers().add(member);  // member.setTeam(team); 없이 이 코드만 작성하면 member의 TEAM_ID는 null이 된다.
            em.persist(member);

            team.addMember(member); // **이거 넣거나**

            // * flush, clear 주석처리 > 필요.
//            team.getMembers().add(member);       // >> changeTeam(setTeam)에 같이 만들어버려.

            // * flush, clear 주석처리 > 아래 == 사이의 코드가 출력되지 않음. > team.getMembers().add(member); 추가 필요
            em.flush();
            em.clear();

            Team findTeam = em.find(Team.class, team.getId());  // * flush, clear 주석처리 > 1차 캐시
            List<Member6> members = findTeam.getMembers();

            System.out.println("===================");
            for(Member6 m : members){
                System.out.println("m = " + m.getUsername());
            }
            System.out.println("===================");

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
