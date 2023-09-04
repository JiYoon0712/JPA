package ch08;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


// [ 프록시 ]
public class Ex04 {
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

            /*
            Member8 m1 = em.getReference(Member8.class, member1.getId());
            System.out.println("m1 = " + m1.getClass());    // Proxy

            Member8 reference = em.getReference(Member8.class, member1.getId());
            System.out.println("reference = " + reference.getClass());  // Proxy
            
            System.out.println("a == a:" + (m1 == reference));  // true. true가 되어야 하기에 같은 Proxy 반환.
            */

            Member8 refMember = em.getReference(Member8.class, member1.getId());
            System.out.println("refMember = " + refMember.getClass());    // Proxy

            Member8 findMember = em.find(Member8.class, member1.getId());
            System.out.println("findMember = " + findMember.getClass());  // find임에도 불구하고 Member가 아닌 Proxy 반환

            System.out.println("refMember == findMember:" + (refMember == findMember)); // why? => ★ JPA에서는 무조건 refMember == findMember를 참을 보장해 줘야 한다.

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();

    }
}
