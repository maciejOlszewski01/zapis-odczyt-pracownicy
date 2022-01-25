package com.company;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;
import java.util.zip.*;

import model.*;
import controller.Metody;
public class Main {


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner kbreader = new Scanner(System.in);
        HashMap<String, Pracownik> Lista = new HashMap<String, Pracownik>();
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
                    }
                case 4:
                    System.out.print("[Z]achowaj/[O]dtworz\t:\t");
                    String c = kbreader.next();
                    String plik;
                    String roz;
                    System.out.print("-------------------------------\n");
                    if(c.equals("Z")){
                        System.out.print("Kompresja [G]zip/[Z]ip\t:\t");
                        roz = kbreader.next();
                        System.out.print("Nazwa pliku\t:\t");
                        plik = kbreader.next();
                        System.out.print("-------------------------------\n");
                        System.out.print("[G]-Potwierdź\n");
                        System.out.print("[Q]-Powrzuć\n");
                        if(roz.equals("G")) {
                            if (kbreader.next().equals("G")) {
                                FileOutputStream fos = new FileOutputStream(plik);
                                GZIPOutputStream gz = new GZIPOutputStream(fos);
                                ObjectOutputStream oos = new ObjectOutputStream(gz);
                                oos.writeObject(Lista);
                                oos.close();
                            } else {
                                break;
                            }
                        } else{
                            if (kbreader.next().equals("G")) {
                                FileOutputStream fos = new FileOutputStream("temp.ser");
                                ObjectOutputStream oos = new ObjectOutputStream(fos);
                                oos.writeObject(Lista);
                                oos.close();
                                int iByte = 0;
                                FileOutputStream f = new FileOutputStream(plik);
                                ZipOutputStream zos = new ZipOutputStream(f);
                                BufferedOutputStream out = new BufferedOutputStream(zos);
                                BufferedInputStream in = new BufferedInputStream(new FileInputStream("temp.ser"));
                                zos.putNextEntry (new ZipEntry ("temp.ser"));
                                while ((iByte = in.read()) != -1 ){
                                    out.write (iByte);
                                };
                                in.close();out.flush();out.close();
                            } else {
                                break;
                            }
                        }
                    } else if(c.equals("O")){
                        System.out.print("Nazwa pliku\t:\t");
                        plik = kbreader.next();
                        System.out.print("-------------------------------\n");
                        System.out.print("[G]-Potwierdź\n");
                        System.out.print("[Q]-Powrzuć\n");
                        if(kbreader.next().equals("G")){
                            if(plik.charAt(plik.length()-2) == 'g') {
                                FileInputStream fi = new FileInputStream(plik);
                                GZIPInputStream in = new GZIPInputStream(fi);
                                ObjectInputStream ois = new ObjectInputStream(in);
                                Lista = (HashMap<String, Pracownik>) ois.readObject();
                                ois.close();

                                continue;
                            }else{
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
                                Lista = (HashMap<String, Pracownik>) trn.readObject();
                                trn.close();
                            }
                        }else{
                            break;
                        }

                    }
                    break;
                default:
                    System.out.print("\nWybrałeś nieistniejącą opcje\n");


            }
        }
    }
}
