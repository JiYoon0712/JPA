package ch02;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

// 준영속 상태로 만드는 방법
// 1) em.detach(entity) : 특정 엔티티만 준영속 상태로 전환
// 2) em.clear() : 영속성 컨텍스트를 완전히 초기화
// 3) em.close() : 영속성 컨텍스트를 종료
public class ex06 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 영속
            Member member = em.find(Member.class, 150L);    // 150 없음을 확인하고 영속성 컨텍스트에 올림.
            member.setName("AAAAA");    // 값 변경. 변경내역 추적하여 update 쿼리

            // 준영속
            // : 영속성 컨텍스트에서 제외.
            // : JPA에서 관리하지 않음 > 트랜잭션 커밋하는 경우 아무일도 일어나지 않음.

            // 1) 특정 엔티티만 준영속 상태로 전환
            // em.detach(member);
            
            // 2) 영속성 컨텍스트를 완전히 초기화
            // : 1차 캐시를 통으로 다 지운다.
            // ( 1차 캐시 상관없이 테스트 케이스 작성 등 눈으로 보고싶은 경우 사용 )
            em.clear();
            Member member2 = em.find(Member.class, 150L);   // 초기화 된 이후 다시 올림

            // 3) 영속성 컨텍스트를 종료
            // : 더이상 관리 x, 데이터를 변경해도 변경이 안됨.
            // em.close();

            System.out.println("=========================");
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
