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


//            Member member = new Member();
//            member.setId(4L);
//            member.setName("aaa");
            //업데이트 쿼리 발생
//            Member updateTagetMember =  em.find(Member.class,1L);
//            updateTagetMember.setName("뱅스");
//            em.remove(member);
            // 다중 쿼리
            List<Member> members = em.createQuery("select m from Member as m",Member.class)
                    .setFirstResult(2)
                    .setMaxResults(3)
                    .getResultList();
            //JPQL을 사용하더라도 영속성을 유지한다.
            members.get(0).setName("키키");


            //영속성 컨텍스트
            Member member = new Member();
            member.setId(12L);
            member.setName("비영속");

            //영속 -- 디비에 저장 되지않음
            Member findMember = em.find(Member.class,12L);


            //비영속성 -- 디테치 이후 반영되지 않음 분리상태이기 때문
            em.detach(member);
            member.setName("비영속성2");
            System.out.println("=========== 이후");


            //저장

            tx.commit(); // 커밋 시점에 db연결

        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();


    }
}
