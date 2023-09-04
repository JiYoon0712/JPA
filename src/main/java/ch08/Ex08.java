package ch08;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

// 영속성 전이 : CASCADE
// 고아객체 > 특정 엔티티가 개인소유할 때 사용. 조심히 사용해야 함.
public class Ex08 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Child child1 = new Child();
            Child child2 = new Child();

            Parent parent = new Parent();
            parent.addChild(child1);
            parent.addChild(child2);

            // cascade = CascadeType.ALL 사용 > em.persist(child1); em.persist(child2); 생략 가능
            em.persist(parent);
            em.persist(child1);
            em.persist(child2);

            em.flush();
            em.clear();

            Parent findParent = em.find(Parent.class, parent.getId());
            // findParent.getChildList().remove(0);    // cascade = CascadeType.ALL 사용 > 자식 엔티티를 컬렉션에서 제거

            em.remove(findParent);  // Parent 삭제 > Child도 함께 삭제

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
