package com.AutoOrders.Storage.FV;

import com.AutoOrders.Storage.FvDetails.FvDetails;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class FV {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String dataDostawy;
    private String numerFV;
    private String kontrahent;
    private String kwota;
    private String termin;

    @ManyToMany
    @JoinTable(
            name = "fv_fvdetails",
            joinColumns = @JoinColumn(name = "fv_id"),
            inverseJoinColumns = @JoinColumn(name = "fvdetails_id")
    )
    private Set<FvDetails> details = new HashSet<>();


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDataDostawy() {
        return dataDostawy;
    }

    public void setDataDostawy(String dataDostawy) {
        this.dataDostawy = dataDostawy;
    }

    public String getKontrahent() {
        return kontrahent;
    }

    public void setKontrahent(String kontrahent) {
        this.kontrahent = kontrahent;
    }

    public String getKwota() {
        return kwota;
    }

    public void setKwota(String kwota) {
        this.kwota = kwota;
    }

    public String getTermin() {
        return termin;
    }

    public void setTermin(String termin) {
        this.termin = termin;
    }

    public String getNumerFV() {
        return numerFV;
    }

    public void setNumerFV(String numerFV) {
        this.numerFV = numerFV;
    }

    public Set<FvDetails> getDetails() {
        return details;
    }

    public void setDetails(Set<FvDetails> details) {
        this.details = details;
    }
    public void addDetails(FvDetails fvDetails){
        this.details.add(fvDetails);
    }

    public void removeFvDetails(FvDetails fvDetails){
        this.details.remove(fvDetails);
    }

    @Override
    public String toString() {
        return "FV{" +
                "id=" + id +
                ", dataDostawy='" + dataDostawy + '\'' +
                ", numerFV" + numerFV +
                ", kontrahent" + kontrahent +
                ", kwota" + kwota +
                ", termin" + termin +
                '}';
}
}


