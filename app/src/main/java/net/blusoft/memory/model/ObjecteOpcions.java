package net.blusoft.memory.model;

import java.io.Serializable;


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

    int getNumCartes() {
        return numCartes;
    }

    long getDificultat() {
        return dificultat;
    }
}

