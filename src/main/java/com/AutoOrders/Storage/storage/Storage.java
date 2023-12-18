package com.AutoOrders.Storage.storage;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String kod;
    private String nazwa;
    private double iloscMagazyn;
    private double cenaZakupu;
    private double cenaSprzedazy;
    private double marza;
    private String dataWaznosci;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public double getIloscMagazyn() {
        return iloscMagazyn;
    }

    public void setIloscMagazyn(double iloscMagazyn) {
        this.iloscMagazyn = iloscMagazyn;
    }

    public double getCenaZakupu() {
        return cenaZakupu;
    }

    public void setCenaZakupu(double cenaZakupu) {
        this.cenaZakupu = cenaZakupu;
    }

    public double getCenaSprzedazy() {
        return cenaSprzedazy;
    }

    public void setCenaSprzedazy(double cenaSprzedazy) {
        this.cenaSprzedazy = cenaSprzedazy;
    }

    public double getMarza() {
        return marza;
    }

    public void setMarza(double marza) {
        this.marza = marza;
    }

    public String getDataWaznosci() {
        return dataWaznosci;
    }

    public void setDataWaznosci(String dataWaznosci) {
        this.dataWaznosci = dataWaznosci;
    }

    @Override

    public String toString() {
        return "Storage{" +
                "id=" + id +
                ", kod=" + kod +
                ", nazwa='" + nazwa + '\'' +
                ", iloscMagazyn=" + iloscMagazyn +
                ", cenaZakupu=" + cenaZakupu +
                ", cenaSprzedazy=" + cenaSprzedazy +
                ", marza=" + marza +
                ", dataWaznosci='" + dataWaznosci + '\'' +
                '}';
    }
}
