package jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JapMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        // JPA에서는 트랜잭션 단위로 해야함
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {

            Team team = new Team();
            team.setName("팀");
            em.persist(team);

            Member member = new Member();
            member.setName("aaa");
            member.setTeam(team);


            em.persist(member);

            em.flush(); //insert 수행
            em.clear(); //영속성켄텍스트 초기화
            System.out.println(em.find(Member.class,2L));

            tx.commit();




        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }
        emf.close();


    }
}
