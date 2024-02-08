package com.example;

import com.example.model.Address;
import com.example.model.Author;
import com.example.model.Book;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class OneToManytest {

    @Test
    void oneToMany() {
        insertData();

        Session session = HibernateUtil.getSessionFactory().openSession();

        Author author1 = session.find(Author.class, 1L);

        System.out.println(author1.getBooks());

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

        Book book1 = new Book ("Teo va al parque", 5.99, 20, true);
        Book book2 = new Book ("Teo va al zoo", 8.99, 23, true);
        Book book3 = new Book ("Teo va a la piscina", 7.99, 24, true);
        Book book4 = new Book ("Teo va Tunivers", 6.99, 50, false);

        book1.setAuthor(author1);
        book2.setAuthor(author1);
        book3.setAuthor(author2);
        book4.setAuthor(author2);

        session.persist(book1);
        session.persist(book2);
        session.persist(book3);
        session.persist(book4);

        /////////

        session.getTransaction().commit();
        session.close();
    }
}
