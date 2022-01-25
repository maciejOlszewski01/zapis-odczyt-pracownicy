package controller;
import model.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Scanner;

public class Metody<J> {


    static Handlowiec dodaj_handlowiec(){
        String pesel, imie, nazwisko;
        int wynagrodzenie, numer;
        BigDecimal prowizja;
        int limit;
        Scanner kb = new Scanner(System.in);
        System.out.print("Identyfikator PESEL\t: ");

        pesel = kb.next();
        while(!Sprawdz_Pesel(pesel)){
            pesel = kb.next();
        }
        System.out.print("Imie\t: ");
        imie = kb.next();
        System.out.print("Nazwisko\t: ");
        nazwisko = kb.next();
        System.out.print("Wynagrodzenie(zł)\t: ");
        wynagrodzenie = kb.nextInt();
        System.out.print("Telefon służbowy numer\t: ");
        numer = kb.nextInt();
        System.out.print("Prowizja(%)\t: ");
        prowizja = kb.nextBigDecimal();
        System.out.print("Limit prowizji/miesiąc(zł)\t: ");
        limit = kb.nextInt();
        System.out.print("------------------------------------------------------\n");
        System.out.print("[G]-Zapisz\n");
        System.out.print("[Q]-Powrót\n");
        if(kb.next().equals("G")) {
            return new Handlowiec(pesel, imie, nazwisko, wynagrodzenie, numer, prowizja, limit);
        } else {
            return null;
        }
    }

    static Dyrektor dodaj_Dyrektor(){
        String pesel, imie, nazwisko;
        int wynagrodzenie, numer;
        BigDecimal dodatek;
        String Karta;
        int limit;
        Scanner kb = new Scanner(System.in);
        System.out.print("Identyfikator PESEL\t: ");

        pesel = kb.next();
        while(!Sprawdz_Pesel(pesel)){
            pesel = kb.next();
        }
        System.out.print("Imie\t: ");
        imie = kb.next();
        System.out.print("Nazwisko\t: ");
        nazwisko = kb.next();
        System.out.print("Wynagrodzenie(zł)\t: ");
        wynagrodzenie = kb.nextInt();
        System.out.print("Telefon służbowy numer\t: ");
        numer = kb.nextInt();
        System.out.print("Dodatek służbowy\t: ");
        dodatek = kb.nextBigDecimal();
        System.out.print("Karta służbowa numer\t: ");
        Karta = kb.next();
        System.out.print("Limit kosztów/miesiąc(zł)\t: ");
        limit = kb.nextInt();
        System.out.print("------------------------------------------------------\n");
        System.out.print("[G]-Zapisz\n");
        System.out.print("[Q]-Powrót\n");
        if(kb.next().equals("G")) {
            return new Dyrektor(pesel, imie, nazwisko, wynagrodzenie, numer, dodatek, Karta, limit);
        } else {
            return null;
        }
    }

    public static Pracownik dodaj_pracownik(){
        System.out.print("[D]yrektor/[H]andlowiec:\t");
        String choice;
        Scanner kb = new Scanner(System.in);
        choice = kb.next();
        System.out.print("------------------------------------------------------\n");
        if(choice.equals("H")){
            return dodaj_handlowiec();
        }else if(choice.equals("D")){
            return dodaj_Dyrektor();
        } else{
            return null;
        }
    }
    public static void usun(HashMap<String,Pracownik> a){
        Scanner kb = new Scanner(System.in);
        String p;
        System.out.print("Podaj identyfikator Pesel\t:\t");
        p = kb.nextLine();
        a.get(p).wyswietl();

        System.out.print("[G]-Potwierdz\n");
        System.out.print("[Q]-Powrót\n");
        if(kb.next().equals("G")){
           a.remove(p);
        }
    }

    public static boolean Sprawdz_Pesel(String Pesel) {
        try {
            if (((Pesel.charAt(0) - '0') + 3 * (Pesel.charAt(1) - '0') + 7 * (Pesel.charAt(2) - '0') + 9 * (Pesel.charAt(3) - '0') + (Pesel.charAt(4) - '0') + 3 * (Pesel.charAt(5) - '0') + 7 * (Pesel.charAt(6) - '0') + 9 * (Pesel.charAt(7) - '0') + (Pesel.charAt(8) - '0') + 3 * (Pesel.charAt(9) - '0') + (Pesel.charAt(10) - '0')) %
                    10 == 0) {


                return true;
            } else {
                System.out.print("Suma kontrolna się nie zgadza\nPopraw dane\n");
                return false;
            }
        }catch(StringIndexOutOfBoundsException e){
            System.out.print("Zła długość peselu\nPopraw dane\n");
            return false;
        }
    }
}
