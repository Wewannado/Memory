package com.exemple.profedam.memory.model;

import java.io.Serializable;

/**
 * Created by IES on 20/11/2016.
 */

public class ObjecteOpcions implements Serializable {
    private int numCartes;
    private long dificultat;


    public ObjecteOpcions(int numCartes, long dificultat) {
        super();
        this.dificultat = dificultat;
        this.numCartes = numCartes;
    }

    public ObjecteOpcions() {

    }

    public int getNumCartes() {
        return numCartes;
    }

    public long getDificultat() {
        return dificultat;
    }
}

