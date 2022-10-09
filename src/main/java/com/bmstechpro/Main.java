package com.bmstechpro;
/* library-app
 * @created 10/07/2022
 * @author Konstantin Staykov
 */

import com.bmstechpro.model.Book;
import com.bmstechpro.model.Card;
import com.bmstechpro.repository.BookDao;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class Main {
    public static void main(String[] args) {

        BookDao bookDao = new BookDao();
        // Getting all books from the database
        List<Book> all = bookDao.findAll();
        all.forEach(System.out::println);

        // Getting a book by id
        Book book = bookDao.findById(1).orElseThrow();

//        book.setTitle("Effective Java - Second Edition");
//
//        // Updating book in our database
//        bookDao.update(book);

        System.out.println(book);

        // Updating rating

        all = bookDao.findAll();
        all.forEach(b->b.setRating(5));
        bookDao.update(all);


//
//        // Inserting a Book into the database
//         book = new Book();
//        book.setTitle("More Java");
//        // Here we are getting the updated book (book id is updated by the method call to the database
//         book = bookDao.create(book);
//        System.out.println(book);






//
//        // Creating a deck of cards

//        int cardDeckIndex = 0;
//        Card[] deckOfCards = new Card[52];
//
//        for (Suit suit : Suit.values()) {
//            for (CardEnum cardEnum : CardEnum.values()) {
//                deckOfCards[cardDeckIndex++] = new Card(cardEnum, suit);
//            }
//        }
//
//        // Shuffling the deck
//        Deque<Card> cardDeque = shuffleCards(deckOfCards);
//        // Drawing cards from the deck
//
//        for (int i = 0; i < 52; i++) {
//            Card card = cardDeque.poll();
//            System.out.println(card);
//            System.out.println("cards left: "+ cardDeque.size());
//        }


    }

    public static Deque<Card> shuffleCards(Card[] deck) {
        Random random = new Random();
        for (int i = 0; i <10000 ; i++) {
            int index1 = random.nextInt(52);
            int index2 = random.nextInt(52);
            Card card1 = deck[index1];
            Card card2 = deck[index2];
            deck[index1] = card2;
            deck[index2] = card1;
        }

        return new ArrayDeque<>(Arrays.asList(deck));
    }
}
