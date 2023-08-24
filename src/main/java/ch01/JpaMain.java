package ch01;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        // persistence.xml 의 <persistence-unit name="hello"> name 부분 : ("hello");
        // ★ EntityManagerFactory는 애플리케이션 로딩시점에 딱 1개만 만들어야 함.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // 실제 DB에 저장하는 하나의 트랜잭션 단위마다 EntityManager를 생성.
        // 고객의 요청이 올때마다. ex) 고객이 상품을 장바구니에 담는다. 등
        // ★ EntityManager는 쓰레드간에 공유되면 안된다.
        // ★ JPA의 모든 데이터 변경은 트랜잭션 안에서 실행.
        // (em : 자바 컬렉션이라고 생각하면 됨.)
        EntityManager em = emf.createEntityManager();

        // JPA의 모든 데이터 변경은 트랜잭션 안에서 일어나야 한다.
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try { // 실제로 돌아가는 code 부분은 try 문 안에 작성.

            // 1) insert
            /*
            Member member = new Member();
            member.setId(2L);
            member.setName("HelloB");

            em.persist(member); // 멤버 저장
            */


            // 2) select
            /*
            Member findMember = em.find(Member.class, 1L);
            System.out.println("findMember.id = " + findMember.getId());
            System.out.println("findMember.name = " + findMember.getName());
            */


            // 3) delete
            /*
            Member findMember = em.find(Member.class, 1L);
            em.remove(findMember);
            */

            // 4) update
            /*
            Member findMember = em.find(Member.class, 1L);
            findMember.setName("HelloJPA");
            // em.persist(findMember); 없이도 수정이 가능함. > 이유 : 자바 컬렉션을 다루는 듯이 설계된 것.
            */


            //  [ JPQL ] ------------------------------------------------------------------------------
            //  : JPA는 SQL을 추상화한 JPQL 객체 지향 쿼리 언어 제공
            //  : 테이블이 아닌 객체를 대상으로 검색하는 객체 지향 쿼리
            //  * JPQL : 엔티티 객체를 대상으로 쿼리 / * SQL : 데이터베이스 테이블을 대상으로 쿼리
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(5)  // 5번부터
                    .setMaxResults(8)   // 8개 가져오라.
                    .getResultList();

            for (Member member : result){
                System.out.println("member.name = " + member.getName());
            }

            // 커밋 반드시 해야 함.
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
