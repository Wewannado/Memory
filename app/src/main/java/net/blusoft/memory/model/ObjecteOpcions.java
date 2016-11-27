package net.blusoft.memory.model;

import java.io.Serializable;


public class ObjecteOpcions implements Serializable {
    private int numCartes;
    private long dificultat;
    public final static int EASY = 1;
    public final static int MEDIUM = 2;
    public final static int HARD = 3;


    public ObjecteOpcions(int numCartes, long dificultat) {
        super();
        this.dificultat = dificultat;
        this.numCartes = numCartes;
    }

    public ObjecteOpcions() {

    }

    int getNumCartes() {
        return numCartes;
    }

    long getDificultat() {
        return dificultat;
    }
}

