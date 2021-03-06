package com.mercadopago.android.px.testcheckout.input;

import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class FakeCard extends Card {

    public FakeCard(final CardState cardState, @Nullable final String cardNumber) {
        super("123", cardState.toString(), cardNumber, "11234567", "1121");
    }

    public FakeCard(final String escNumber, final CardState cardState, final String cardNumber) {
        super(escNumber, cardState.toString(), cardNumber, "11234567", "1121");
    }

    public enum CardState {
        APRO,
        CONT,
        CALL,
        FUND,
        SECU,
        EXPI,
        FORM,
        OTHE;

        public static List<CardState> all() {
            List<CardState> states = new ArrayList<>();
            states.add(APRO);
            states.add(CONT);
            states.add(CALL);
            states.add(FUND);
            states.add(SECU);
            states.add(EXPI);
            states.add(FORM);
            states.add(OTHE);
            return states;
        }
    }
}
