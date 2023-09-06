package ch10;

import ch09_2.Member11;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

// Criteria (( 실무에서 사용 X ))
// 장점 )
// 1. 잘못 작성 > 컴파일 오류
// 2. 동적 커리 깔끔

// 단점 ) SQL 같지 않음.
public class Criteria1 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            //Criteria 사용 준비
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Member11> query = cb.createQuery(Member11.class);

            //루트 클래스 (조회를 시작할 클래스)
            Root<Member11> m = query.from(Member11.class);

            CriteriaQuery<Member11> cq = query.select(m).where(cb.equal(m.get("username"), "kim"));

            List<Member11> resultList = em.createQuery(cq)
                            .getResultList();

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
