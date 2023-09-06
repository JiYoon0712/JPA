package ch10;

import ch09_2.Member11;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Ex02 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member11 member = new Member11();
            member.setUsername("member1");
            em.persist(member);

            // flush -> commit, query

            // 결과 0 => JDBC를 직접 사용하는 경우에는 수동으로 플러시 해줘야 함. => em.flush();
            // dbconn.executeQuery("select * from member");

            List<Member11> resultList =  em.createNativeQuery("select MEMBER_ID, city, street, zipcode, USERNAME from MEMBER11 ", Member11.class)
                            .getResultList();

            for (Member11 member1 : resultList) {
                System.out.println("member1 = " + member1);
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
