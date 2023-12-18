package com.AutoOrders.Storage.FvDetails;


import javax.persistence.*;
import java.util.Objects;

@Entity
public class FvDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String zaplata;

    public FvDetails() {
    }

    public FvDetails(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getZaplata() {
        return zaplata;
    }

    public void setZaplata(String zaplata) {
        this.zaplata = zaplata;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FvDetails fvDetails = (FvDetails) o;
        return Objects.equals(id, fvDetails.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return this.zaplata;
    }
}
