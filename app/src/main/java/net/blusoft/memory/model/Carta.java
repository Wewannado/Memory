package net.blusoft.memory.model;

import net.blusoft.memory.R;

public class Carta {

    enum Estat {BACK, FRONT, FIXED}

    private final int backImage = R.drawable.back;
    private int frontImage;
    private Estat estat;

    Carta(int frontImage) {
        this.frontImage = frontImage;
        this.estat = Estat.BACK;
    }

    /**
     * Returns the frontImage of a card
     *
     * @return the frontImage Resource id
     */
    int getFrontImage() {
        return frontImage;
    }

    /**
     * Returns the status from one card.
     *
     * @return 0 for a faced down card (BACK),1 for a faced up card (FRONT), or 2 for a paired card (FIXED)
     */
    Estat getEstat() {
        return estat;
    }

    /**
     * Switch one card between the FRONT and BACK status.
     */
    void girar() {
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
    void bloqueja() {
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
