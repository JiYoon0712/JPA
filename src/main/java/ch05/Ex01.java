package ch05;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Ex01 {
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
            member.setTeam(team);   // JPA가 알아서 team의 PK값을 꺼내서 FK로 insert할때 넣어줌.

            em.persist(member);

            // [ DB에서 가져오는 쿼리 보고싶은 경우 ]
            em.flush(); // flush로 영속성 컨텍스트 > DB로 날려버려
            em.clear(); // 영속성 컨텍스트 초기화

            /*
            // [ 양방향 연관관계 ]
            Member6 findMember = em.find(Member6.class, member.getId());
            List<Member6> members = findMember.getTeam().getMembers();

            for(Member6 m : members){
                System.out.println("m = " + m.getUsername());
            }
            */





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
