package ch08;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


// [ 프록시 ]
// - 영속성 컨텍스트의 도움을 받을 수 없는 준영속 상태일 때, 프록시를 초기화하면 문제가 발생한다.
public class Ex05 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member8 member1 = new Member8();
            member1.setUsername("member1");
            em.persist(member1);

            em.flush();
            em.clear();

            Member8 refMember = em.getReference(Member8.class, member1.getId());
            System.out.println("refMember = " + refMember.getClass());    // Proxy

            // detach > "나 영속성 컨텍스트 관리 안 해!!!"  +  em.close();, em.clear(); 도 마찬가지이다.
            em.detach(refMember);

            // 영속성 컨텍스트의 도움을 받아서 초기화해야 하는데 detach로 인해 불가능 > 예외발생 > no Session
            refMember.getUsername();

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
