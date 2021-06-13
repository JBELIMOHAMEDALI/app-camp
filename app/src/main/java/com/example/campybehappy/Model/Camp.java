package com.example.campybehappy.Model;

public class Camp {
    private String libelle;
    private String quantité;
    public Camp(String lib, String quant)
    {
        this.libelle=lib;
        this.quantité=quant;
    }

    public String getLibelle() {
        return libelle;
    }

    public String getQuantité() {
        return quantité;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setQuantité(String quantité) {
        this.quantité = quantité;
    }
}
