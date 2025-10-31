package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class OrderItem {

    @Id @GeneratedValue // PK
    @Column(name ="ORDER_ITEM_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name="ORDER_ID") // 해당 속성을 ORDER_ID로 지정 FK 명시
    private Order order;

    @ManyToOne
    @JoinColumn(name="ITEM_ID") // FK
    private Item item;


    private int oderPrice;
    private int count;
}
