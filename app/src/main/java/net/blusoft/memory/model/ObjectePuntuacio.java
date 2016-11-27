package net.blusoft.memory.model;


class ObjectePuntuacio {

    private String nom;
    private long puntuacio;
    private int posicio;


    ObjectePuntuacio(String nom, long puntuacio, int posicio) {
        this.nom = nom;
        this.puntuacio = puntuacio;
        this.posicio = posicio;
    }


    public void setPosicio(int posicio) {
        this.posicio = posicio;
    }

    String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    long getPuntuacio() {
        return puntuacio;
    }

    public void setPuntuacio(long puntuacio) {
        this.puntuacio = puntuacio;
    }

    int getPosicio() {
        return posicio;
    }
}
