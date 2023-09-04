package ch08;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


// [ 프록시 ]
// - 영속성 컨텍스트에 찾는 엔티티가 이미 있으면 em.getReference()를 호출해도 실제 엔티티가 반환
public class Ex03 {
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

            Member8 m1 = em.find(Member8.class, member1.getId());
            System.out.println("m1 = " + m1.getClass());    // m1 = class ch08.Member8

            Member8 reference = em.getReference(Member8.class, member1.getId());
            System.out.println("reference = " + reference.getClass());  // reference = class ch08.Member8 >> 영속성 컨텍스트에 찾는 엔티티가 이미 있으면 실제 엔티티를 반환한다.

            System.out.println("a == a:" + (m1 == reference));  // true.

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();

    }
}
