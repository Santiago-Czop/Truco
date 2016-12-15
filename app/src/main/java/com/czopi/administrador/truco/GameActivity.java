package com.czopi.administrador.truco;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;

public class GameActivity extends AppCompatActivity {

    ImageButton card1, card2, card3;
    ImageView  AIcard1, AIcard2, AIcard3, cardPlayed1, cardPlayed2, cardPlayed3, cardPlayed4, cardPlayed5, cardPlayed6;
    Button envido, realenvido, faltaenvido, quiero, noquiero, truco, retruco, vale4, almazo;
    ArrayList<Card> cards, userHand, AIHand;
    Hashtable<String, Integer> AICardsValues;
    int envidoUser, envidoAI, turn, round, AIScore, UserScore;
    String cardPlayedByUser;
    Boolean toGo1, toGo2, trick, reTrick, worth4, mano, envidoDouble, envidoReal, envidoFalta;
    Card bestCard, middleCard, worstCard;

    int cardsPlayed = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        card1 = (ImageButton)findViewById(R.id.card1);
        card2 = (ImageButton)findViewById(R.id.card2);
        card3 = (ImageButton)findViewById(R.id.card3);
        envido = (Button)findViewById(R.id.envido);
        realenvido = (Button)findViewById(R.id.realenvido);
        faltaenvido = (Button)findViewById(R.id.faltaenvido);
        quiero = (Button)findViewById(R.id.quiero);
        noquiero = (Button)findViewById(R.id.noquiero);
        truco = (Button)findViewById(R.id.truco);
        retruco = (Button)findViewById(R.id.retruco);
        vale4 = (Button)findViewById(R.id.vale4);
        almazo = (Button)findViewById(R.id.almazo);
        AIcard1 = (ImageView)findViewById(R.id.AIcard1);
        AIcard2 = (ImageView)findViewById(R.id.AIcard2);
        AIcard3 = (ImageView)findViewById(R.id.AIcard3);
        cardPlayed1 = (ImageView)findViewById(R.id.cardPlayed1);
        cardPlayed2 = (ImageView)findViewById(R.id.cardPlayed2);
        cardPlayed3 = (ImageView)findViewById(R.id.cardPlayed3);
        cardPlayed4 = (ImageView)findViewById(R.id.cardPlayed4);
        cardPlayed5 = (ImageView)findViewById(R.id.cardPlayed5);
        cardPlayed6 = (ImageView)findViewById(R.id.cardPlayed6);


        cards = new ArrayList<>();
        cards.add(new Card("e1", 'e', 0, 1));
        cards.add(new Card("b1", 'b', 1, 1));
        cards.add(new Card("e7", 'e', 2, 7));
        cards.add(new Card("o7", 'o', 3, 7));
        cards.add(new Card("e3", 'e', 4, 3));
        cards.add(new Card("o3", 'o', 4, 3));
        cards.add(new Card("c3", 'c', 4, 3));
        cards.add(new Card("b3", 'b', 4, 3));
        cards.add(new Card("e2", 'e', 5, 2));
        cards.add(new Card("c2", 'c', 5, 2));
        cards.add(new Card("b2", 'b', 5, 2));
        cards.add(new Card("o2", 'o', 5, 2));
        cards.add(new Card("c1", 'c', 6, 1));
        cards.add(new Card("o1", 'o', 6, 1));
        cards.add(new Card("e12", 'e', 7, 0));
        cards.add(new Card("o12", 'o', 7, 0));
        cards.add(new Card("c12", 'c', 7, 0));
        cards.add(new Card("b12", 'b', 7, 0));
        cards.add(new Card("b11", 'b', 8, 0));
        cards.add(new Card("e11", 'e', 8, 0));
        cards.add(new Card("c11", 'c', 8, 0));
        cards.add(new Card("o11", 'o', 8, 0));
        cards.add(new Card("e10", 'e', 9, 0));
        cards.add(new Card("o10", 'o', 9, 0));
        cards.add(new Card("c10", 'c', 9, 0));
        cards.add(new Card("b10", 'b', 9, 0));
        cards.add(new Card("b7", 'b', 10, 7));
        cards.add(new Card("c7", 'c', 10, 7));
        cards.add(new Card("o6", 'o', 11, 6));
        cards.add(new Card("e6", 'e', 11, 6));
        cards.add(new Card("c6", 'c', 11, 6));
        cards.add(new Card("b6", 'b', 11, 6));
        cards.add(new Card("e5", 'e', 12, 5));
        cards.add(new Card("c5", 'c', 12, 5));
        cards.add(new Card("b5", 'b', 12, 5));
        cards.add(new Card("o5", 'o', 12, 5));
        cards.add(new Card("e4", 'e', 13, 4));
        cards.add(new Card("c4", 'c', 13, 4));
        cards.add(new Card("o4", 'o', 13, 4));
        cards.add(new Card("b4", 'b', 13, 4));

        cardDealer();
        toGo1 = false;
        toGo2 = false;
        turn = 1;
        round = 1;
        UserScore = 0;
        AIScore = 0;
        mano = false; //True for User being Mano and False for AI being Mano

    }

    public void cardDealer () {
        Collections.shuffle(cards);
        userHand = new ArrayList<>();
        int userCards = 0;
        AIHand = new ArrayList<>();
        int AICards = 0;
        int randomCardIndex;
        Card randomCard;
        for (int i = 0; i < 6; i++) {
            while (true) {
                randomCardIndex = (int) (Math.random() * 40);
                randomCard = cards.get(randomCardIndex);
                if (!(userHand.contains(randomCard) || AIHand.contains(randomCard))){
                    break;
                }
            }
            int resID = getResources().getIdentifier(randomCard.name(), "drawable", getPackageName());
            if (i % 2 == 0) {
                userHand.add(randomCard);
                userCards++;
                if (userCards == 0) {
                    card1.setBackground(getResources().getDrawable(resID));
                    card1.setTag(randomCard.name());
                } else if (userCards == 1) {
                    card2.setBackground(getResources().getDrawable(resID));
                    card2.setTag(randomCard.name());
                } else {
                    card3.setBackground(getResources().getDrawable(resID));
                    card3.setTag(randomCard.name());
                }
            } else {
                AIHand.add(randomCard);
                AICards++;
                if (AICards == 0) {
                    AIcard1.setTag(randomCard.name());
                } else if (AICards == 1) {
                    AIcard2.setTag(randomCard.name());
                } else {
                    AIcard3.setTag(randomCard.name());
                }
            }



        }
        Collections.sort(AIHand, new Card.CUSTOM_COMPARATOR());
        bestCard = AIHand.get(0);
        middleCard = AIHand.get(1);
        worstCard = AIHand.get(2);
        envidoUser = tantos(userHand);
        envidoAI = tantos(AIHand);
    }

    public int tantos (ArrayList<Card> array) {
        int tantos;
        int e = 0;
        int espadasCounter = 0;
        int o = 0;
        int orosCounter = 0;
        int c = 0;
        int copasCounter = 0;
        int b = 0;
        int bastosCounter = 0;
        for (Card card : array) {
            char palo = card.palo();
            int envidoValue = card.envidoValue();
            switch (palo) {
                case 'e':
                    e+= envidoValue;
                    espadasCounter++;
                    break;
                case 'o':
                    o+= envidoValue;
                    orosCounter++;
                    break;
                case 'c':
                    c+= envidoValue;
                    copasCounter++;
                    break;
                case 'b':
                    b+= envidoValue;
                    bastosCounter++;
                    break;
                default:
                    break;
            }
        }
        if (espadasCounter == 3 || orosCounter == 3 || copasCounter == 3 || bastosCounter == 3) {
            char palo = array.get(0).palo();
            ArrayList<Integer> temp = new ArrayList<>();
            for (Card card : array) {
                temp.add(card.envidoValue());
            }
            int toDiminish = Collections.min(temp);
            switch (palo) {
                case 'e':
                    e-= toDiminish;
                    espadasCounter--;
                    break;
                case 'o':
                    o-= toDiminish;
                    orosCounter--;
                    break;
                case 'c':
                    c-= toDiminish;
                    copasCounter--;
                    break;
                case 'b':
                    b-= toDiminish;
                    bastosCounter--;
                    break;
                default:
                    break;
            }
        }
        if (espadasCounter == 2) {
            e+= 20;
        } else if (orosCounter == 2) {
            o+= 20;
        } else if (copasCounter == 2) {
            c+= 20;
        } else if (bastosCounter == 2) {
            b+= 20;
        }
        ArrayList<Integer> tantosPorPalo = new ArrayList<>();
        tantosPorPalo.add(e);
        tantosPorPalo.add(o);
        tantosPorPalo.add(c);
        tantosPorPalo.add(b);
        tantos = Collections.max(tantosPorPalo);
        return tantos;
    }

    public void cardClicked (View view) {
        cardPlayedByUser = view.getTag().toString();
        for (Iterator<Card> iterator = userHand.iterator(); iterator.hasNext();) {
            Card card = iterator.next();
            if (card.name().equals(cardPlayedByUser)) {
                iterator.remove();
            }
        }
        Drawable card;
        switch (view.getId()) {
            case R.id.card1:
                card = card1.getBackground();
                card1.setBackground(null);
                card1.setClickable(false);
                break;
            case R.id.card2:
                card = card2.getBackground();
                card2.setBackground(null);
                card2.setClickable(false);
                break;
            case R.id.card3:
                card = card3.getBackground();
                card3.setBackground(null);
                card3.setClickable(false);
                break;
            default:
                card = getResources().getDrawable(R.drawable.black);
                break;
        }
        switch (cardsPlayed) {
            case 0:
                cardPlayed1.setImageDrawable(card);
                break;
            case 1:
                cardPlayed2.setImageDrawable(card);
                break;
            case 2:
                cardPlayed3.setImageDrawable(card);
                break;
            case 3:
                cardPlayed4.setImageDrawable(card);
                break;
            case 4:
                cardPlayed5.setImageDrawable(card);
                break;
            case 5:
                cardPlayed6.setImageDrawable(card);
                break;
            default:
                break;
        }
        toGo1 = true;
        if (toGo2) {
            turnWinner();
        } else{
            AITurn();
        }

    }

    public void AITurn() {
        if (AIEnvidoDesires()) {
            envido();
        }
        toGo2 = true;
        if (toGo1) {
            turnWinner();
        }
    }

    public boolean AIEnvidoDesires () {
        boolean wants = false;
        if (envidoAI > 27) {
            wants = true;
        } else if (envidoAI == 27) {
            int x = (int) (Math.random()*2);
            if (x == 0) {
                wants = true;
            }
        } else if (envidoAI == 26) {
            int x = (int) (Math.random()*3);
            if (x == 0) {
                wants = true;
            }
        } else if (envidoAI == 25) {
            int x = (int) (Math.random()*4);
            if (x == 0) {
                wants = true;
            }
        } else if (envidoAI == 24) {
            int x = (int) (Math.random()*5);
            if (x == 0) {
                wants = true;
            }
        } else if (envidoAI == 23) {
            int x = (int) (Math.random()*7);
            if (x == 0) {
                wants = true;
            }
        } else if (envidoAI == 22) {
            int x = (int) (Math.random()*8);
            if (x == 0) {
                wants = true;
            }
        } else if (envidoAI == 21) {
            int x = (int) (Math.random()*9);
            if (x == 0) {
                wants = true;
            }
        } else if (envidoAI == 20) {
            int x = (int) (Math.random()*10);
            if (x == 0) {
                wants = true;
            }
        } else if (envidoAI > 0) {
            int x = (int) (Math.random() * 20);
            if (x == 0) {
                wants = true;
            }
        }
        if (wants) {
            if (AIEnvidoDoubleOrEnvidoRealDesires()) {
                envidoDouble(); //TODO EnvidoReal();
            }
        }
        return wants;
    }

    public boolean AIEnvidoDoubleOrEnvidoRealDesires() {
        boolean wants = false;
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        if ("Button".equals(stackTraceElements[1].getMethodName())) {
            if (envidoAI > 31) {
                envidoDouble = true;
                wants = true;
            } else if (envidoAI > 26) {
                int x = (int) (Math.random() * 3);
                if (x == 0) {
                    envidoDouble = true;
                    wants = true;
                }
            } else {
                int x = (int) (Math.random() * 10);
                if (x == 0) {
                    envidoDouble = true;
                    wants = true;
                }
            }
        }
        return wants;
    }

    public void turnWinner() {
        toGo1 = false;
        toGo2 = false;
        turn++;
        if (turn == 4) { //Si llega a turn 4, ya es el 1 del siguiente round
            turn = 1;
            round++;
            if (round % 2 == 0) {
                AITurn();
                mano = true;
            } else {
                mano = false;
            }
        }
    }

    public void checkForWinner () {
        String winner;
        if (UserScore >= 30) {
            winner = "User";
        } else if (AIScore >= 30) {
            winner = "AI";
        }

    }

    public void checkForWinnerOfEnvido () {
        int x = 0;
        if (envidoFalta) {
            //TODO Set the value of X according to Falta Envido's rules.
        } else {
            if (envidoDouble) {
                x+=2;
            }
            if (envidoReal) {
                x+=3;
            }
            if (envidoAI > envidoUser) {
                AIScore += x;
            } else if (envidoAI < envidoUser) {
                UserScore += x;
            } else if (envidoAI == envidoUser) {
                if (mano) {
                    UserScore += x;
                } else {
                    AIScore += 2;
                }
            }
        }
        envidoFalta = false;
        envidoReal = false;

    }

    public void Button (View view) {
        switch (view.getId()) {
            case R.id.envido:
                if (AIEnvidoDesires()) {
                    checkForWinnerOfEnvido();
                } else {
                    UserScore+=1;
                }
                break;


        }
    }

    public void envidoDouble () {
        //TODO Cuando el AI responde Envido a Envido.
        //TODO CheckfForEnvidoAIDoubleDesires
        /*
        if (quiero) {
            if(CheckForEnvidoAiDoubleDesires()) {

            }
        }

         */
    }

    public void envido () {
        //TODO Cuando el AI empieza un Envido.
    }

}


