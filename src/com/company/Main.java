package com.company;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;
import java.util.zip.*;

import model.*;
import controller.Metody;

import static controller.Metody.odczyt;
import static controller.Metody.zapis;

public class Main {
    public static HashMap<String, Pracownik> Lista = new HashMap<String, Pracownik>();

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner kbreader = new Scanner(System.in);

        while(true){
            System.out.print("\nMenu\n" +
                    "1.Lista pracowników\n" +
                    "2.Dodaj pracownika\n" +
                    "3.Usun pracownika\n" +
                    "4.Kopia zapasowa\n");
            int a = kbreader.nextInt();
            switch(a){
                case 1:
                    int i = 1;
                    for (String key: Lista.keySet()) {
                        Lista.get(key).wyswietl();
                        System.out.print("\t\t\t[Pozycja:"+i+"/"+Lista.size()+"]\n");
                        i++;
                        System.out.print("[G]-Następny\n");
                        System.out.print("[Q]-Powrót\n");
                        if(kbreader.next().equals("G")){
                            continue;
                        }else{
                            break;
                        }
                    }

                    break;
                case 2:
                    try{
                    Pracownik b = Metody.dodaj_pracownik();
                    Lista.put(b.getPesel(),b);
                    break;
                    }catch(NullPointerException e){
                        System.out.print("Pracownik nie został dodany\n");
                    }
                    break;
                case 3:
                    try {
                        Metody.usun(Lista);
                        break;
                    }catch(NullPointerException e){
                        System.out.print("Pracownik o podanym Peselu nie został odnaleziony\n");
                        break;
                    }
                case 4:
                    System.out.print("[Z]achowaj/[O]dtworz\t:\t");
                    String c = kbreader.next();
                    String plik;
                    String roz;
                    System.out.print("-------------------------------\n");
                    if(c.equals("Z")){


                    zapis();
                    break;

                    } else if(c.equals("O")){
                        odczyt();
                    }
                    break;
                default:
                    System.out.print("\nWybrałeś nieistniejącą opcje\n");


            }
        }
    }
}
