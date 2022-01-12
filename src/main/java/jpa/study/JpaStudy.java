package jpa.study;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import jpa.study.entity.Member;
import jpa.study.entity.Team;


public class JpaStudy {

	 public static void main(String[] args) {
	        //엔티티 매니저 팩토리 생성
	        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
	        EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성

	        EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

	        try {
	            tx.begin(); //트랜잭션 시작
	            
	            Team team1 = new Team("team1", "전자상거래");
	            em.persist(team1);
	            
	            Member member1 = new Member("member1", "최상일");
	            Member member2 = new Member("member2", "김예지");
	            member1.setTeam(team1);
	            
	            System.out.println("1");
	            em.persist(member1);
	            System.out.println("2");
	            em.persist(member2);
	            System.out.println("3");
	            em.remove(member1);
	            System.out.println("4");
	            em.remove(team1);
	            System.out.println("5");
	            
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
