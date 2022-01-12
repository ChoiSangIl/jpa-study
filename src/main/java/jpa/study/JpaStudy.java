package jpa.study;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import jpa.study.entity.Item;
import jpa.study.entity.Member;
import jpa.study.entity.Order;
import jpa.study.entity.OrderItem;
import jpa.study.entity.OrderStatus;


public class JpaStudy {

	public static void main(String[] args) {
		//엔티티 매니저 팩토리 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
		EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성
		EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득
		
		try {
		    tx.begin(); //트랜잭션 시작
		    
		    Member member1 = new Member();
		    member1.setName("최상일");
		    member1.setCity("서울");
		    member1.setStreet("남양주시");
		    member1.setZipcode("777");
		    em.persist(member1);
		    
		    Order order = new Order();
		    order.setOrderDate(new Date());
		    order.setStatus(OrderStatus.ORDER);
		    order.setMember(member1);
		    
		    Item item1 = new Item();
		    item1.setName("미네랄 워터");
		    item1.setPrice(5000);
		    item1.setStockQuantity(100);
		    em.persist(item1);
		    
		    Item item2 = new Item();
		    item2.setName("탐사수");
		    item2.setPrice(3000);
		    item2.setStockQuantity(1000);
		    em.persist(item2);
		    
		    OrderItem orderItem1 = new OrderItem();
		    orderItem1.setOrderPrice(25000);
		    orderItem1.setCount(5);
		    orderItem1.setOrder(order);
		    orderItem1.setItem(item1);
		    em.persist(orderItem1);
		    
		    OrderItem orderItem2 = new OrderItem();
		    orderItem2.setOrderPrice(30000);
		    orderItem2.setCount(10);
		    orderItem2.setOrder(order);
		    orderItem2.setItem(item2);
		    em.persist(orderItem2);
		    
		    em.persist(order);
		    
		    tx.commit();//트랜잭션 커밋
		} catch (Exception e) {
		    e.printStackTrace();
		    tx.rollback(); //트랜잭션 롤백
		} finally {
		    em.close(); //엔티티 매니저 종료
		}
		
		emf.close(); //엔티티 매니저 팩토리 종료	
	}
	
}
