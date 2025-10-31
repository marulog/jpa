package hellojpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@Entity
//@SequenceGenerator(name="member_sqe_generator", sequenceName = "member_sql")
//@Table(name="DB_Member")
public class Member {

    //
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // 5가지 전략이 있음
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_sqe_generator")
    // AUTO, TABLE, UUID, SEQUENCE, IDENTITY
    // AUTO, JPA가 사용하는 DB dialec에 따라 선택 H2/MySql identity, oracle sequence 사용
    // IDENTITY -> 기본 키 생성을 DB에 위임, Mysql의 AUTO_INCREMENT
    //          ㄴ -> 즉, 실제 DB에 개체가 들어가야지만 PK값을 확인가능, 다만, JPA에서는 영속성 컨텍스트에 pk값을 가지고 개체를 보관함
    // 그래서 IDENTITY 전략이 경우 em.persist하자마자 바로 commit이 이루어짐
    // SEQUENCE -> Oracle에서 사용, db에 있는 시퀀스 객체로 값을 불러와서 적용
    //           ㄴ-> 애도 DB에서 관리하는거라서 PK값을 모르는데, call next value로 시퀀스 값을 가져와서 컨텍스트에 저장함. insert문 안씀
    //           ㄴ-> 근데 객체 생성할 때 마다 next로 가져오면 네트워크 성능이슈가 발생하니깐
    //           ㄴ->initalValue와 allocation을 통해서 2번만 가져오고 allocation수까지는 memoey에서 increase하는 전략
    // TABLE -> 별도의 키 전용 테이블을 만들어 PK값 관리
    // UUID -> 하이버네이트 6부터 추가된 전략으로, UUID(묹자열)로 키 생성

    private String id;

    @Column(name="name", nullable = false)
    private String username;

//    @Id
//    @GeneratedValue
//    private Long id;
//
//    @Column(name = "name", nullable = false) // DB에는 name으로 컬럼 저장
//    private String username;
//
//    private Integer age;
//
//    //EnumType.ORDINAL -> enum 순서를 데이터베이스에 저장 > Integer로 저장 0 ,1 ,2.. 순서로
//    @Enumerated(EnumType.STRING) // enum 이름 자체가 데이터베이스에 저장 >
//    private RoleType roleType;
//
//    @Temporal(TemporalType.TIMESTAMP) // 날짜 + 시간
//    private Date createdDate;
//
//    @Temporal(TemporalType.TIMESTAMP) // 지금은 안쓰고 바로 LocalData~ 쓰몀됨
//    private Date lastModifiedDate;
//
//    @Lob // CLOB 타입으로 긴 텍스트에 해당됨 문자면 CLOB, 나머지는 BLOB
//    private String description;
//
//    @Transient // DB에 저장하지 않음
//    private int temp;


    public Member() {
    }
}
