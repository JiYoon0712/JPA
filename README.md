# JPA ( Java Persistence API )
: 자바 진영의 ORM 기술 표준

## JPA를 사용해야 하는 이유
1. SQL 중심적인 개발에서 객체 중심으로 개발
2. 생산성 
3. 유지보수 : 기존에는 필드 변경시 모든 SQL을 수정해야 하는데 JPA는 필드만 추가하면 된다. SQL은 JPA가 처리한다.
4. 패러다임의 불일치 해결
5. 성능
6. 데이터 접근 추상화와 벤더 독립성
7. 표준

## 데이터베이스 방언 : SQL 표준을 지키지 않는 특정 데이터베이스만의 고유한 기능
- 가변문자
  MySQL : VARCHAR
  Oracle : VARCHAR2
- 문자열을 자르는 함수
  SQL 표준 : SUBSTRING()
  Oracle : SUBSTR()
- 페이징
  MySQL : LIMIT
  Oracle : ROWNUM

=> 💎JPA는 특정 데이터베이스에 종속되지 않는다.


## 주의사항
- EntityManagerFactory : 하나만 생성해서 애플리케이션 전체에서 공유한다.
- EntityManager : 쓰레드간에 공유해서는 안된다.
- JPA의 모든 데이터 변경은 트랜잭션 안에서 실행한다.


## JPQL
: 테이블이 아닌 객체를 대상으로 검색하는 객체 지향 쿼리
- SQL을 추상화하여 특정 데이터베이스 SQL에 의존적이지 않다.
- SQL과 문법이 유사 > SELECT, FROM, WHERE, GROUP BY, HAVING, JOIN 지원

<hr>
## 영속성 컨텍스트
: 엔티티를 영구 저장하는 환경

