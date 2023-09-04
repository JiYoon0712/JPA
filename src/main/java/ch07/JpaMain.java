package ch07;

import ch01.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

// 상속관계 매핑
// 1) 조인 전략
// 2) 단일 테이블 전략
// 3) 구현 클래스마다 테이블 전략
public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 1) 조인 전략
            // Item : id, name, price 저장 
            // Movie : id, director, actor 저장
            Movie movie = new Movie();
            movie.setDirector("a감독");
            movie.setActor("b배우");
            movie.setName("바람과함께사라지다");
            movie.setPrice(10000);

            em.persist(movie);

            // 영속성 컨텍스트에 담긴 것 DB로 보내고 1차 캐시 비우기.
            em.flush();
            em.clear();

            // inner join해서 select
            Movie findMovie = em.find(Movie.class, movie.getId());
            System.out.println("findMovie = " + findMovie);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
