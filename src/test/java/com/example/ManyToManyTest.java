package com.example;

import com.example.model.Address;
import com.example.model.Author;
import com.example.model.Book;
import com.example.model.Category;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class ManyToManyTest {

    @Test
    void manyToMany() {
        insertData();

        Session session = HibernateUtil.getSessionFactory().openSession();

        Book book1 = session.find(Book.class, 1L);
        System.out.println(book1.getCategories());


        Category category1 = session.find(Category.class, 1L);
        //  System.out.println(category1.getBooks());

        Set<Book> books = category1.getBooks();
        for(Book book: books){
            System.out.println(book);
        }

        session.close();
        
    }

    void insertData(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        /////////
        /*
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
        */

        /////////

        Book book1 = new Book ("Teo va al parque", 5.99, 20, true);
        Book book2 = new Book ("Teo va al zoo", 8.99, 23, true);
        Book book3 = new Book ("Teo va a la piscina", 7.99, 24, true);
        Book book4 = new Book ("Teo va Tunivers", 6.99, 50, false);

        /*
        book1.setAuthor(author1);
        book2.setAuthor(author1);
        book3.setAuthor(author2);
        book4.setAuthor(author2);
        */

        /////////
        //Categorías

        Category cat1 = new Category("cat1", 18);
        Category cat2 = new Category("cat2", 18);
        Category cat3 = new Category("cat3", 18);

        book1.getCategories().add(cat1);
        book1.getCategories().add(cat2);

        book2.getCategories().add(cat1);
        book2.getCategories().add(cat3);

        book3.getCategories().add(cat1);

        session.persist(cat1);
        session.persist(cat2);
        session.persist(cat3);


        session.persist(book1);
        session.persist(book2);
        session.persist(book3);
        session.persist(book4);
        /////////

        session.getTransaction().commit();
        session.close();
    }
}
