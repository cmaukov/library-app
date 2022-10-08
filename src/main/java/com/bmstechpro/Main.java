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
        List<Book> all = new BookDao().findAll();
        all.forEach(System.out::println);

        new BookDao().findById(1).ifPresent(System.out::println);
    }
}
