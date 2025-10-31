package hellojpa;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        // 설정정보를 조회 -> persistence.xml
        // persistentce unit이름으로 -> 앤티티매니저팩토리 생성
        // 해당 앤티티매니저에서 다수의 앤티티 매니저 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            //등록
//            Member member = new Member();
//            member.setId(1L);
//            member.setName("HelloA");
//            em.persist(member);
            //삭제
//            Member findMember = em.find(Member.class, 1L);
//            em.remove(findMember);
            //수정
//            Member findMember = em.find(Member.class, 1L);
//            findMember.setName("modifiedA");
            //비영속
//            Member member = new Member();
//            member.setId(101L);
//            member.setName("HelloJPA");
//
//            //영속
//            System.out.println("=== BEFORE ===");
//            em.persist(member);
//            System.out.println("=== AFTER ===");
//
//            Member findMember = em.find(Member.class, 101L);
//            System.out.println("findMember.id = " + findMember.getId());
//            System.out.println("findMember.name = " + findMember.getName());
//            // 실제로 DB에서 조회하지 않고 1차 캐시에서 조회함!
//
//            Member member1 = em.find(Member.class, 100L);
//            Member member2 = em.find(Member.class, 100L);
//            System.out.println("a == b -> " + (member1 == member2));

            // 쓰기지연
//            Member member1 = new Member(150L, "A");
//            Member member2 = new Member(160L, "B");
//
//            em.persist(member1);
//            em.persist(member2);
//            System.out.println("================");
//            Member member = new Member(1L, "memberA");
//            em.persist(member);

            // 자동 변경 감지 id가 1인 레코드가 존재함!
//            Member findMember = em.find(Member.class, 1L);
////            findMember.setId(2L);
//            findMember.setName("modified_A");
            // 따로 em.persist(findMember)할 필요가 없음
            // 1차 캐시에서 변경점을 발견하고 commit할때 자동 반영함
            // 정확히는 초기 1차 캐시에 넣을때 만든 스냅샷과 현재 개체를 비교해서
            // 변경된 개체들에 한해서 sql 저장소에 update sql를 작성함!

            // 플러시 -> 영속성 컨텍스트의 변경내용을 데이터베이스에 반영함
            // 플러시 발생! -> 변경점을 확인해서 sql를 작성 -> 실제 commit할 때 db에 전송
            // em.flush(), 트랜잭션 커밋, JPQL 쿼리 실행 시 호출됨
//            Member member = new Member(200L, "memberFlush");
//            em.persist(member);
//            em.flush();
//            System.out.println("===========");
//            Member member = new Member();
//            member.setUsername("Test1");
//            member.setRoleType(RoleType.ADMIN);
//            em.persist(member);

            //  기본키
//            Member member = new Member();
//            member.setId("ID_A");
//            member.setUsername("A");
//            em.persist(member);

            // 단방향 연관관계
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team); // INSERT문 바로 나감!

            Member member = new Member();
            member.setUserName("member1");
            member.changeTeam(team); // 1차 캐시에서 가져옴 Member -> Team 관계 설정
            em.persist(member); // INSERT문 바로 나감

//            team.getMembers().add(member); // Team -> Member 관계 설정
            // 즉 2번 참조 설정을 만들어아됨 -> 연관관계 편의 메소드를 생성하자

//            em.flush();
//            em.clear();// sql 임시 저장소 초기화

            // ManyToOne -> 즉시 로딩임, team객체가 존재하거나, 1차 캐시에 있으면 바로 반환
            Member findMember = em.find(Member.class, member.getId()); //1차 캐시
            // OneToMany -> 지연 로딩임, 이 리스트가 필요할 때 까지 DB 조회 안함
            // 현재 객체 자체는 존재하지만 비어있음 -> 조회할 필요가 없음 -> select 쿼리 안날림
            List<Member> members = findMember.getTeam().getMembers();


            System.out.println("======");
            for (Member m : members) {
                System.out.println("m.getUserName() = " + m.getUserName());
            }
            System.out.println("======");
            

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
