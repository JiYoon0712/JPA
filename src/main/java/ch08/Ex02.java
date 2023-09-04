package ch08;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


// [ 프록시 ]
public class Ex02 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member8 member1 = new Member8();
            member1.setUsername("member1");
            em.persist(member1);

            Member8 member2 = new Member8();
            member2.setUsername("member2");
            em.persist(member2);

            em.flush();
            em.clear();

            Member8 m1 = em.find(Member8.class, member1.getId());
            Member8 m2 = em.getReference(Member8.class, member2.getId());
            // Member8 m2 = em.find(Member8.class, member2.getId());

            logic(m1, m2);   // ★ 타입 비교는 ==이 아닌 instance of 사용해야 함.

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();

    }

    // 실제 비즈니스 로직에서는 프록시로 넘어올지 실제로 넘어올지 모르기 때문에
    // 절대로!! 타입 비교를 ==으로 하면 안 된다.
    private static void logic(Member8 m1, Member8 m2) {
//        System.out.println("m1 == m2 : " + (m1.getClass() == m2.getClass())); // m1 == m2 : false
       System.out.println("m1 == m2 : " + (m1 instanceof Member8)); // m1 == m2 : true
       System.out.println("m1 == m2 : " + (m2 instanceof Member8));// m1 == m2 : true
    }
}
