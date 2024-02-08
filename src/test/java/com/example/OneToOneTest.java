package com.example;

import com.example.model.Address;
import com.example.model.Author;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class OneToOneTest {

    @Test
    void OneToOne() {
        insertData();

        Session session = HibernateUtil.getSessionFactory().openSession();

        Author author1 = session.find(Author.class, 1L);
        Author author2 = session.find(Author.class, 2L);
        System.out.println(author1);
        System.out.println(author2);

        session.close();
    }

    void insertData(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        /////////
        Author author1 = new Author("Pepe", "pepe@gmail.com", LocalDate.of(1964, 1, 1));
        Author author2 = new Author("Perico", "perico@gmail.com", LocalDate.of(1967, 2, 2));

        Address address1 = new Address("Calle1", "Ciudad1",  "España");
        Address address2 = new Address("Calle2", "Ciudad3",  "España");

        author1.setAddress(address1);
        author2.setAddress(address2);

        session.persist(address1);
        session.persist(address2);

        session.persist(author1);
        session.persist(author2);
        /////////

        session.getTransaction().commit();
        session.close();
    }
}
