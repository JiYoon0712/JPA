package ch08;

import org.hibernate.Hibernate;

import javax.persistence.*;

// [ 프록시 ]
// * PersistenceUnitUtil().isLoaded(Object entity) : 프록시 인스턴스의 초기화 여부 확인
// * entity.getClass.getName(); : 프록시 클래스 확인 방법
// * Hibernate.initialize(entity);  : 프록시 강제 초기화
public class Ex06 {
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
            /*
            refMember.getUsername();    // 초기화 (강제 호출)
            System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(refMember));   // true. 프록시 인스턴스의 초기화 여부 확인.
            */
            
            Hibernate.initialize(refMember);    // 강제 초기화.
                                                // JPA 표준은 강제 초기화 없음.

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
