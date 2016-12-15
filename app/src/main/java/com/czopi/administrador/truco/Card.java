package com.czopi.administrador.truco;

import java.util.Comparator;

public class Card {
    public String name;
    public char palo;
    public int value;
    public int envidoValue;

    public Card(String name, char palo, int value, int envidoValue) {
        this.name = name;
        this.palo = palo;
        this.value = value;
        this.envidoValue = envidoValue;
    }

    public String name() {
        return name;
    }

    public char palo() {
        return palo;
    }

    public int value() {
        return value;
    }

    public int envidoValue() {
        return envidoValue;

    }

    public static class CUSTOM_COMPARATOR implements Comparator<Card> {
        @Override
        public int compare(Card one, Card other) {
            return one.value - other.value;
        }
    }

}
