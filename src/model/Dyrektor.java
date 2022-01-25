package model;

import java.math.BigDecimal;

public class Dyrektor extends Pracownik {
    private String karta;
    private BigDecimal dodatek;
    private int limit;


    public Dyrektor(String pesel, String imie, String nazwisko,int wynagrodzenie,int numer,BigDecimal dodatek, String karta, int limit ){
        this.Pesel = pesel;
        this.Nazwisko = nazwisko;
        this.Imie = imie;
        this.Stanowisko = "Dyrektor";
        this.Wynagrodzenie = wynagrodzenie;
        this.Telefon = numer;
        this.dodatek = dodatek;
        this.karta = karta;
        this.limit = limit;
    }

    public BigDecimal getDodatek() {
        return dodatek;
    }

    public void setDodatek(BigDecimal dodatek) {
        this.dodatek = dodatek;
    }

    public String getKarta() {
        return karta;
    }

    public void setKarta(String karta) {
        this.karta = karta;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public void wyswietl() {
        System.out.print("Identyfikator PESEL\t: "+Pesel+"\n");
        System.out.print("Imie\t: "+ Imie +"\n");
        System.out.print("Nazwisko\t: "+Nazwisko+"\n");
        System.out.print("Stanowisko\t: "+Stanowisko+"\n");
        System.out.print("Wynagrodzenie(zł)\t: "+Wynagrodzenie+"\n");
        System.out.print("Telefon służbowy numer\t: "+Telefon+"\n");
        System.out.print("Dodatek służbowy(zł)\t: "+dodatek+"\n");
        System.out.print("Karta służbowa numer\t: "+karta+"\n");
        System.out.print("Limit kosztów/miesiąc(zł)\t: "+limit+"\n");
    }
}
