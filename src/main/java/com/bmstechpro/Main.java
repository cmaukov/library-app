package com.bmstechpro;
/* library-app
 * @created 10/07/2022
 * @author Konstantin Staykov
 */

import com.bmstechpro.model.Book;
import com.bmstechpro.model.Card;
import com.bmstechpro.model.CardEnum;
import com.bmstechpro.model.Suit;
import com.bmstechpro.repository.BookDao;

import java.util.*;

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


//        int count = 0;
//        boolean aceIsOne = true;
//        for (int i = 0; i < 3; i++) {
//            CardEnum card = CardEnum.getCard();
//            System.out.println("You've got: " + card.name());
//            switch (card) {
//                case TWO -> count += 2;
//                case THREE -> count += 3;
//                case FOUR -> count += 4;
//                case FIVE -> count += 5;
//                case SIX -> count += 6;
//                case SEVEN -> count += 7;
//                case EIGHT -> count += 8;
//                case NINE -> count += 9;
//                case TEN, JACK, QUEEN, KING -> count += 10;
//                case ACE -> {
//                    if (aceIsOne) {
//                        count += 1;
//                    } else {
//                        count += 11;
//                    }
//                }
//                default -> throw new UnsupportedOperationException("unknown card");
//            }
//        }
//        System.out.println("count: " + count);



        // Creating a deck of cards
        int cardDeckIndex = 0;
        Card[] deckOfCards = new Card[52];

        for (Suit suit : Suit.values()) {
            for (CardEnum cardEnum : CardEnum.values()) {
                deckOfCards[cardDeckIndex++] = new Card(cardEnum, suit);
            }
        }

        // Shuffling the deck
        Deque<Card> cardDeque = shuffleCards(deckOfCards);
        // Drawing cards from the deck

        for (int i = 0; i < 52; i++) {
            Card card = cardDeque.poll();
            System.out.println(card);
            System.out.println("cards left: "+ cardDeque.size());
        }


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
