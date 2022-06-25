package com.mycompany.ut8_pd2;

public class TArista implements IArista {

    protected Comparable etiquetaOrigen;
    protected Comparable etiquetaDestino;
    protected double costo;

    public TArista(Comparable etiquetaOrigen, Comparable etiquetaDestino, double costo) {
        this.etiquetaOrigen = etiquetaOrigen;
        this.etiquetaDestino = etiquetaDestino;
        this.costo = costo;
    }

    @Override
    public Comparable getEtiquetaOrigen() {
        return etiquetaOrigen;
    }

    @Override
    public void setEtiquetaOrigen(Comparable etiquetaOrigen) {
        this.etiquetaOrigen = etiquetaOrigen;
    }

    @Override
    public Comparable getEtiquetaDestino() {
        return etiquetaDestino;
    }

    @Override
    public void setEtiquetaDestino(Comparable etiquetaDestino) {
        this.etiquetaDestino = etiquetaDestino;
    }

    @Override
    public double getCosto() {
        return costo;
    }

    @Override
    public void setCosto(double costo) {
        this.costo = costo;
    }

    public TArista aristaInversa() {
        return new TArista(this.getEtiquetaDestino(), this.getEtiquetaOrigen(), this.getCosto());
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        
        builder.append("(");
        builder.append(this.etiquetaOrigen);
        builder.append(", ");
        builder.append(this.etiquetaDestino);
        builder.append("): ");
        builder.append(this.costo);
        
        return builder.toString();
    }

    @Override
    public int compareTo(IArista o) {
        if(this.getCosto() > o.getCosto()) {
            return 1;
        } else if (this.getCosto() == o.getCosto()) {
            return 0;
        } else {
            return -1;
        }
    }
}
