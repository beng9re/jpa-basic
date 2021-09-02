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


            Member m1 = new Member(21L,"1");
            Member m2 = new Member(22L,"2");
            em.persist(m1); // 지연 평가됨
            em.persist(m2);

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
