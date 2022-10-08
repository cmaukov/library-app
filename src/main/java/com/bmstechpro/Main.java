package com.bmstechpro;
/* library-app
 * @created 10/07/2022
 * @author Konstantin Staykov
 */

import com.bmstechpro.model.Book;
import com.bmstechpro.repository.BookDao;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Getting all books from the database
        List<Book> all = new BookDao().findAll();
        all.forEach(System.out::println);

        // Getting a book by id
        new BookDao().findById(1).ifPresent(System.out::println);

        // Inserting a Book into the database
//        Book book = new Book();
//        book.setTitle("OCP Java Study Guide");
//        // Here we are getting the updated book (book id is updated by the method call to the database
//         book = new BookDao().create(book);
//        System.out.println(book);


    }
}
