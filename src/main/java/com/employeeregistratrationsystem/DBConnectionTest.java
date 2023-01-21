package com.employeeregistratrationsystem;

import com.employeeregistratrationsystem.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

class DBConnectionTest {

    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static Logger logger = LogManager.getLogger(DBConnectionTest.class);
    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        List<Department> departments = em.createQuery("select d from Department d", Department.class).getResultList();

        for (Department department : departments) {
            logger.info(department.getName());
        }


        em.getTransaction().commit();
        em.close();
    }
}
