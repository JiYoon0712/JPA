package ch09_2;

import ch07_2.Member;
import ch09.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

// @MappedSuperclass : 공통 매핑 정보가 필요한 경우 사용
// 1. BaseEntity에 @MappedSuperclass 매핑
// 2. TEAM, MEMBER > extends BaseEntity

public class JpaMain {
public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

    EntityManager em = emf.createEntityManager();

    EntityTransaction tx = em.getTransaction();
    tx.begin();

    try {
        // 값 타입 저장
        Member11 member = new Member11();
        member.setUsername("member1");
        member.setHomeAddress(new Address("city1", "street", "10000"));

        member.getFavoriteFoods().add("치킨");
        member.getFavoriteFoods().add("족발");
        member.getFavoriteFoods().add("피자");

        member.getAddressHistory().add(new AddressEntity("old1", "street", "10000"));
        member.getAddressHistory().add(new AddressEntity("old2", "street", "10000"));

        em.persist(member);

        // 값 타입 조회
        // 지연 로딩
        em.flush();
        em.clear();

        System.out.println("=============== START ===============");
        Member11 findMember = em.find(Member11.class, member.getId());

        /*
        List<AddressEntity> addressHistory = findMember.getAddressHistory();
        for(AddressEntity address : addressHistory){
            System.out.println("address = " + address.getCity());
        }

        Set<String> favoriteFoods = findMember.getFavoriteFoods();
        for(String favoriteFood : favoriteFoods){
            System.out.println("favoriteFoods = " + favoriteFood);
        }
        */

        // 값 타입 수정
        // homeCity => newCity
/*
        // findMember.getHomeAddress().setCity("newCity"); // 이렇게 작성 X
        Address a = findMember.getHomeAddress();
        findMember.setHomeAddress(new Address(("newCity"), a.getStreet(),a.getZipcode()));

        // 값 타입 컬렉션 수정 ( String이기에 삭제하고 추가 )
        // 치킨 => 한식
        findMember.getFavoriteFoods().remove("치킨");
        findMember.getFavoriteFoods().add("한식");
*/
        // 주소 변경
        // Address의 equals가 제대로 정의되어 있지 않으면 큰일.
        // findMember.getAddressHistory().remove(new Address("old1", "street", "10000"));  // 기존에 있던 것과 같은 값
        // findMember.getAddressHistory().add(new Address("newCity1", "street", "10000"));

//        findMember.getAddressHistory().remove(new Address("old1", "street", "10000"));
//        findMember.getAddressHistory().add(new Address("newCity1", "street", "10000"));

        tx.commit();
    } catch (Exception e) {
        tx.rollback();
    } finally {
        em.close();
    }

    emf.close();
}
}
