package com.bmstechpro.model;
/* library-app
 * @created 10/08/2022
 * @author Konstantin Staykov
 */

import java.util.Random;

public enum Card {

    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    TEN,
    JACK,
    KING,
    QUEEN,
    ACE;

    public static Card getCard(){
        Random random = new Random();
        int i = random.nextInt(13) ;
        Card[] values = Card.values();
        return values[i];

    }
}
