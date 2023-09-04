package ch08;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


// [ 프록시 ]
public class Ex01 {
public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

    EntityManager em = emf.createEntityManager();

    EntityTransaction tx = em.getTransaction();
    tx.begin();

    try {

        Member8 member = new Member8();
        member.setUsername("hello");

        em.persist(member);

        em.flush();
        em.clear();

//        Member findMember = em.find(Member.class, member.getId());
        Member8 findMember = em.getReference(Member8.class, member.getId());    // 가짜인 프록시 객체 조회
        System.out.println("before findMember = " + findMember.getClass());            // before findMember = class ch08.Member8$HibernateProxy$4QOnov4J >> 하이버네이트가 강제로 만든 가짜 클래스

        System.out.println("findMember.id = " + findMember.getId());             // member.getId는 가지고 있어서 쿼리 x
        System.out.println("findMember.username = " + findMember.getUsername()); // 영속성 컨텍스트한테 요청해서 proxy에 없는 값을 DB에서 가져와서 실제 Entity를 가져옴.
        System.out.println("findMember.username = " + findMember.getUsername()); // 이미 target에 갖고 있으므로 초기화 필요 없이 출력

        System.out.println("after findMember = " + findMember.getClass());            // after findMember = class ch08.Member8$HibernateProxy$4QOnov4J >> 교체되는 것이 아님. 프록시는 유지. target 값만 채워지는 것.

        tx.commit();
    } catch (Exception e) {
        tx.rollback();
    } finally {
        em.close();
    }

    emf.close();
}
}
