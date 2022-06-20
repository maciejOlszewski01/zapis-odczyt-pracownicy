package model;

import java.math.BigDecimal;

public class Handlowiec extends Pracownik {
    private BigDecimal prowizja;
    private int limit;

    @Override
    public void wyswietl() {
        System.out.print("Identyfikator PESEL\t: "+Pesel+"\n");
        System.out.print("Imie\t: "+ Imie +"\n");
        System.out.print("Nazwisko\t: "+Nazwisko+"\n");
        System.out.print("Stanowisko\t: "+Stanowisko+"\n");
        System.out.print("Wynagrodzenie(zł)\t: "+Wynagrodzenie+"\n");
        System.out.print("Telefon służbowy numer\t: "+Telefon+"\n");
        System.out.print("Prowizja(%)\t: "+prowizja+"\n");
        System.out.print("Limit prowizji/miesiąc(zł)\t: "+limit+"\n");
    }

    public Handlowiec(String pesel, String imie, String nazwisko, int wynagrodzenie, int numer, BigDecimal prowizja, int limit ){
        this.Pesel = pesel;
        this.Nazwisko = nazwisko;
        this.Imie = imie;
        this.Stanowisko = "Handlowiec";
        this.Wynagrodzenie = wynagrodzenie;
        this.Telefon = numer;
        this.prowizja = prowizja;
        this.limit = limit;
    }

    public BigDecimal getProwizja() {
        return prowizja;
    }

    public void setProwizja(BigDecimal prowizja) {
        this.prowizja = prowizja;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
