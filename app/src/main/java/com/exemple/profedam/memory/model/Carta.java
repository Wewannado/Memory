package com.exemple.profedam.memory.model;

import com.exemple.profedam.memory.R;

/**
 * Created by ALUMNEDAM on 29/01/2016.
 */
public class Carta {

    public enum Estat {BACK, FRONT, FIXED}

    private final int backImage = R.drawable.back;
    private int frontImage;
    private Estat estat;

    public Carta(int frontImage) {
        this.frontImage = frontImage;
        this.estat = Estat.BACK;
    }

    /**
     * Returns the frontImage of a card
     *
     * @return the frontImage Resource id
     */
    public int getFrontImage() {
        return frontImage;
    }

    /**
     * Returns the status from one card.
     *
     * @return 0 for a faced down card (BACK),1 for a faced up card (FRONT), or 2 for a paired card (FIXED)
     */
    public Estat getEstat() {
        return estat;
    }

    /**
     * Switch one card between the FRONT and BACK status.
     */
    public void girar() {
        //GIRA la carta
        switch (estat) {
            case BACK:
                estat = Estat.FRONT;
                break;

            case FRONT:
                estat = Estat.BACK;
                break;

        }


    }

    /**
     * Sets the STATUS of one card to FIXED, aka the card has been paired.
     */
    public void bloqueja() {
        //FIXA la carta
        estat = Estat.FIXED;
    }

    /**
     * Returns the current active image of the card. It could be the Face value of the card, or the common reverse card.
     * Note that a FIXED card return the front image although the image is not shown anymore.
     * @return The current active image of the card.
     */
    public int getActive() {
        int imageActive = 0;
        switch (estat) {
            case BACK:
                imageActive = this.backImage;
                break;

            case FRONT:
                FIXED:
                imageActive = this.frontImage;
                break;
        }
        return imageActive;
    }
}
