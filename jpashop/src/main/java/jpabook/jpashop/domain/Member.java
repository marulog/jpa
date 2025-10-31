package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MEMBER_ID")
    private Long id;

    @OneToMany(mappedBy = "member") // ManyToOne에서 쓰인 인스턴스이름
    private List<Order> orders = new ArrayList<>();

    private String name;
    private String city;
    private String street;
    private String zipCode;
}
