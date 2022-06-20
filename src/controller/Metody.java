package controller;
import model.*;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;
import java.util.zip.*;

import static com.company.Main.Lista;

public class Metody<J> {


    public static Handlowiec dodaj_handlowiec(){
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

    public static Dyrektor dodajDyrektor(){
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
            return dodajDyrektor();
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
    static void zapis_pojZip(String plik, Pracownik a, String b) throws IOException {
        String temp = "temp";
        temp += b;
        temp += ".ser";
        plik +=b;
        plik +=".zip";
        FileOutputStream fos = new FileOutputStream(temp);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(a);
        oos.close();
        int iByte = 0;
        FileOutputStream f = new FileOutputStream(plik);
        ZipOutputStream zos = new ZipOutputStream(f);
        BufferedOutputStream out = new BufferedOutputStream(zos);
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(temp));
        zos.putNextEntry (new ZipEntry(temp));
        while ((iByte = in.read()) != -1 ){
            out.write (iByte);
        };
        in.close();out.flush();out.close();
    }
    static void zapis_poj(String plik,Pracownik a, String b) throws IOException {

        plik +=b;
        plik +=".gz";
        FileOutputStream fos = new FileOutputStream(plik);
        GZIPOutputStream gz = new GZIPOutputStream(fos);
        ObjectOutputStream oos = new ObjectOutputStream(gz);
        oos.writeObject(a);
        oos.close();
    }
    public static void zapis() throws IOException {
        String plik;
        Scanner kbreader = new Scanner(System.in);
        System.out.print("Kompresja [G]zip/[Z]ip\t:\t");
        String roz = kbreader.next();
        System.out.print("Nazwa pliku\t:\t");
        plik = kbreader.next();
        System.out.print("-------------------------------\n");
        System.out.print("[G]-Potwierdź\n");
        System.out.print("[Q]-Powrzuć\n");
        if(roz.equals("G")) {

            if (kbreader.next().equals("G")) {
                int counter = 1;
                ExecutorService executor = Executors.newFixedThreadPool(10);
                List<CompletableFuture<Void>> completableFutureList = new ArrayList<>(Lista.size());
                for (String key: Lista.keySet()) {
                    String tem = Integer.toString(counter);

                    completableFutureList.add(CompletableFuture.runAsync(() -> {
                        try {
                            zapis_poj(plik, Lista.get(key), tem);
                        } catch (IOException e) {
                            throw new CompletionException(e);
                        }
                    } , executor));
                    counter++;
                }
                completableFutureList.forEach((a)->a.join());

            }
        } else{
            if (kbreader.next().equals("G")) {
                int counter = 1;
                ExecutorService executor = Executors.newFixedThreadPool(10);
                List<CompletableFuture<Void>> completableFutureList = new ArrayList<>(Lista.size());
                for (String key: Lista.keySet()) {
                    String tem = Integer.toString(counter);

                    completableFutureList.add(CompletableFuture.runAsync(() -> {
                        try {
                            zapis_pojZip(plik, Lista.get(key), tem);
                        } catch (IOException e) {
                            throw new CompletionException(e);
                        }
                    } , executor));
                    counter++;
                }
                completableFutureList.forEach((a)->a.join());
            }
    }}

     static int counter_g(String a) throws FileNotFoundException {


        int b = 0;
        int c = 1;
        String temp ="";
        try{
        while(true) {
            temp = a;
            temp += Integer.toString(c);
            temp +=".gz";
            FileInputStream fi = new FileInputStream(temp);
            b++;
            c++;
        }}catch(FileNotFoundException g){
            return b;
        }

     }

    static int counter_z(String a) throws FileNotFoundException {


        int b = 0;
        int c = 1;
        String temp;
        try{
            while(true) {
                temp = a;
                temp += Integer.toString(c);
                temp +=".zip";
                FileInputStream fi = new FileInputStream(temp);
                b++;
                c++;
            }}catch(FileNotFoundException g){
            return b;
        }

    }

    public static Pracownik odczyt_p(String plik, String a) throws IOException, ClassNotFoundException {
        plik += a;
        plik += ".gz";
        FileInputStream fi = new FileInputStream(plik);
        GZIPInputStream in = new GZIPInputStream(fi);
        ObjectInputStream ois = new ObjectInputStream(in);
        Pracownik temp = (Pracownik) ois.readObject();
        ois.close();
        return temp;
    }

    public static Pracownik odczyt_pz(String plik,String a) throws IOException, ClassNotFoundException {
        plik += a;
        plik += ".zip";
        FileInputStream fi = new FileInputStream(plik);
        int iByte = 0;
        FileOutputStream o2 = new FileOutputStream("temp2.ser");
        ZipInputStream in2 = new ZipInputStream(fi);
        BufferedInputStream bis = new BufferedInputStream(in2);
        while(in2.getNextEntry()!=null){
            while((iByte = bis.read()) != -1){
                o2.write(iByte);
            }
        }
        o2.close();
        FileInputStream tr = new FileInputStream("temp2.ser");
        ObjectInputStream trn = new ObjectInputStream(tr);
        Pracownik temp = (Pracownik) trn.readObject();
        trn.close();
        return temp;
    }

    public static Pracownik odczyt() throws IOException, ClassNotFoundException {
        String plik;
        Scanner kbreader = new Scanner(System.in);
        System.out.print("Nazwa pliku\t:\t");
        plik = kbreader.next();
        System.out.print("-------------------------------\n");
        System.out.print("[G]-Potwierdź\n");
        System.out.print("[Q]-Powrzuć\n");
        if(kbreader.next().equals("G")){
            if(plik.charAt(plik.length()-2) == 'g') {
                System.out.print(counter_g(plik));
                ExecutorService executor = Executors.newFixedThreadPool(10);
                List<CompletableFuture<Pracownik>> completableFutureList = new ArrayList<>(Lista.size());

                    for (int i = 0; i < counter_g(plik); i++) {
                        int temp2 = i + 1;
                        String temp3 = Integer.toString(temp2);
                        completableFutureList.add(CompletableFuture.supplyAsync(() -> {
                            try {
                                Pracownik temp = odczyt_p(plik, temp3);
                                Lista.put(temp.getPesel(), temp);
                                return temp;
                            } catch (IOException | ClassNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                        }, executor));

                    }
                try {
                    completableFutureList.forEach(CompletableFuture::join);
                }catch(Exception e){
                    System.out.print("\nERROR\n");
                }
            }else {

                for(int i = 0; i < counter_z(plik);i++){
                    int temp2 = i+1;

                    String temp3 = Integer.toString(temp2);

                    ExecutorService executor = Executors.newFixedThreadPool(10);
                    List<CompletableFuture<Pracownik>> completableFutureList = new ArrayList<>(Lista.size());
                    completableFutureList.add(CompletableFuture.supplyAsync(() -> {
                        Pracownik temp = null;
                        try {
                            temp = odczyt_pz(plik, temp3);
                            Lista.put(temp.getPesel(), temp);
                            return temp;
                        } catch (IOException | ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    },executor));
                    completableFutureList.forEach((a)->a.join());
                }

            }
        }
        String p = "01251006218";
        return Lista.get(p);
    }
}
