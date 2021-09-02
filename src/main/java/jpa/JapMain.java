package jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JapMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        // JPA에서는 트랜잭션 단위로 해야함
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {

//
//            Member member = new Member();
//            member.setId(12L);
//            member.setName("비영속");

            //1차캐시에서 데이터를 가져옴
            //Member findMember = em.find(Member.class,12L);
            //System.out.println(findMember.getId());
            System.out.println("1차캐시 검증");

            //1차 케시에 의해 1번만 조회한다.
            Member findMember1 =  em.find(Member.class,10L);
            Member findMember2 = em.find(Member.class,10L);

            //영속성 컨텍스트 동일성 보장
            if(findMember1 == findMember2){
                System.out.println("참이다");
            }


            tx.commit(); // 커밋 시점에 db연결

        }catch (Exception e){
            tx.rollback();
            System.out.println(e);
        }finally {
            em.close();
        }
        emf.close();


    }
}
