package com.bmstechpro;
/* library-app
 * @created 10/07/2022
 * @author Konstantin Staykov
 */

import com.bmstechpro.model.Book;
import com.bmstechpro.model.Card;
import com.bmstechpro.repository.BookDao;

import java.util.List;
import java.util.Random;

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
        Random random = new Random();
        int minValue = Integer.MAX_VALUE;
        int maxValue = Integer.MIN_VALUE;
int count = 0;
boolean aceIsOne = true;
        for (int i = 0; i < 3; i++) {
            Card card = Card.getCard();
            System.out.println("You've got: "+ card.name());
            switch (card) {
                case TWO -> count += 2;
                case THREE -> count += 3;
                case FOUR -> count += 4;
                case FIVE -> count += 5;
                case SIX -> count +=6;
                case SEVEN -> count+=7;
                case EIGHT -> count+=8;
                case NINE -> count+=9;
                case TEN, JACK, QUEEN, KING -> count +=10;
                case ACE -> {
                    if(aceIsOne){
                        count+=1;
                    }else {
                        count +=11;
                    }
                }
                default -> throw new UnsupportedOperationException("unknown card");
            }
        }
        System.out.println("count: "+ count);


    }
}
