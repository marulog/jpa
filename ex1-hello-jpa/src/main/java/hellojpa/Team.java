package hellojpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Team {
    // 양방향 연관관계
    // 개체특 -> 각 클래스 마다 참조 인스턴스를 보관해야됨
    // 그래서 누가 실제 FK값을 수정할지 -> 주인을 정해야됨
    // 주인 -> JoinColum으로 FK값을 수정, 관리
    // 주인 x -> 실제로 수정안됨, 읽기(조회)만 가능함
    // -> 외래 키가 있는 테이블을 주인으로 정하면됨 갸꿀띠!

    @Id
    @GeneratedValue
    @Column(name ="TEAM_ID")
    private Long id;
    private String name;

    //양방향 연관관계
    @OneToMany(mappedBy = "team") // 해당 연관관계의 주인이 아니다!
    // 즉, FK가 Team에는 없음!, FK는 ManyToOne쪽에 있음
    // 이 테이블에서는 FK로 안만들거야!
    private List<Member> members = new ArrayList<>();

    public Team() {
    }
}
