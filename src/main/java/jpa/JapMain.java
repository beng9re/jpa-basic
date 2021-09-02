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

            //변경 감지
            Member findMember = em.find(Member.class,21L);
            findMember.setName("3");

            System.out.println("=======END===");

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
