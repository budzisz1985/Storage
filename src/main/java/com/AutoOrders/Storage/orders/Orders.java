package com.AutoOrders.Storage.orders;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String data;
    private String dostawca;
    private String nazwa;

    private String ilosc;

    public String getIlosc() {
        return ilosc;
    }

    public void setIlosc(String ilosc) {
        this.ilosc = ilosc;
    }




    public String getNazwa() {return nazwa;}

    public void setNazwa(String nazwa) {this.nazwa = nazwa;}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDostawca() {return dostawca;}

    public void setDostawca(String dostawca) {
        this.dostawca = dostawca;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", data='" + data + '\'' +
                ", nazwa" + nazwa +
                ", dostawca" + dostawca +
                '}';
    }

}
