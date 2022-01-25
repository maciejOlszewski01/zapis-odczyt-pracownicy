package model;

import java.io.Serializable;

public abstract class Pracownik implements Serializable {
    protected String Pesel;
    protected String Imie;
    protected String Nazwisko;
    protected String Stanowisko;
    protected int Wynagrodzenie;
    protected int Telefon;




    Pracownik(){

    }
    public abstract void wyswietl();

    public String getStanowisko() {
        return Stanowisko;
    }

    public void setStanowisko(String stanowisko) {
        Stanowisko = stanowisko;
    }

    public String getNazwisko() {
        return Nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        Nazwisko = nazwisko;
    }

    public int getWynagrodzenie() {
        return Wynagrodzenie;
    }

    public void setWynagrodzenie(int wynagrodzenie) {
        Wynagrodzenie = wynagrodzenie;
    }

    public int getTelefon() {
        return Telefon;
    }

    public void setTelefon(int telefon) {
        Telefon = telefon;
    }

    public String getImie() {
        return Imie;
    }

    public void setImie(String imie) {
        Imie = imie;
    }

    public String getPesel() {
        return Pesel;
    }

    public void setPesel(String pesel) {
        Pesel = pesel;
    }
}
