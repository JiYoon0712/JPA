package ch04;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Ex05 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member5 member1 = new Member5();
            member1.setUsername("A");

            Member5 member2 = new Member5();
            member2.setUsername("B");

            Member5 member3 = new Member5();
            member3.setUsername("C");

            System.out.println("=============");

            //DB SEQ = 1   |  1
            //DB SEQ = 51  |  2
            //DB SEQ = 51  |  3

            em.persist(member1);    //1, 51
            em.persist(member2);    //MEM(메모리)
            em.persist(member3);    //MEM

            System.out.println("member1 = " + member1.getId());
            System.out.println("member2 = " + member2.getId());
            System.out.println("member3 = " + member3.getId());

            System.out.println("=============");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
