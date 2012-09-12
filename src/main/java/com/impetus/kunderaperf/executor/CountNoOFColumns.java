package com.impetus.kunderaperf.executor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class CountNoOFColumns
{
    public static void main(String[] args)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("perfcassandra");
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("select u from UserDTO u");
        q.setMaxResults(100000);
        q.getResultList();
        System.out.println(q.getResultList().size());
    }
}
