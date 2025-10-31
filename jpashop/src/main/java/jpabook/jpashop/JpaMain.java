package jpabook.jpashop;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
//            Order order = em.find(Order.class, 1L);
//            Long memberId = order.getMemberId();
//
//            Member member = em.find(Member.class, memberId);

            // 이게 객체지향다움
//            Order order = em.find(Order.class, 1L);
//            Member findMember = order.getMember();

            // 양방향 연관관계일 때 편의 메소드 작성
            Order order = new Order();
            order.addOrderItem(new OrderItem());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
          em.close();
        }
        emf.close();
    }
}
