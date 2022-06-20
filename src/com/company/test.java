package com.company;
import controller.Metody;
import model.Dyrektor;
import model.Handlowiec;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;

import static controller.Metody.*;
import static org.junit.jupiter.api.Assertions.*;
import static com.company.Main.Lista;
public class test {


        @ParameterizedTest
        @ValueSource(strings = {"48090582163", "04262255934", "04221767245", "01242899786", "84122381351", "47080235537", "91020287227", "54041973737", "71121428617", "80112394648"})
        void Sprawdz_Pesel(String pesel) {

            boolean output = Metody.Sprawdz_Pesel(pesel);
            assertAll(
                    () -> assertTrue(output)
            );
        }

    @ParameterizedTest
    @ValueSource(strings = {"4809058216", "0426255934", "0421767245", "0124289976", "8412381351", "7080235537", "9100287227", "4041973737", "7121428617", "8112394648"})
    void Sprawdz_PeselZ(String pesel) {

        boolean output = Metody.Sprawdz_Pesel(pesel);
        assertAll(
                () -> assertFalse(output)
        );
    }
    @Test
    void pracownikHP() {
        Lista.clear();
        String input = "48090582163\n5\n5\n5\n5\n5\n5\nG";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertDoesNotThrow(() -> {
            Lista.put("48090582163", dodaj_handlowiec());
        });
    }

    @Test
    void pracownikDP() {
        Lista.clear();
        String input = "04262255934\n6\n6\n6\n6\n6\n6\n7\nG";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertDoesNotThrow(() -> {
            Lista.put("0426255934", dodajDyrektor());
        });
    }

    @Test
    void pracownikHZ() {
        Lista.clear();
        Dyrektor d = new Dyrektor("84122381351","8","8",8,8, BigDecimal.TEN,"8",8);
        Lista.put("84122381351",d);
        String input = "48090582163\n5\n5\n5\n5\n5\n5\nG";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertDoesNotThrow(() -> {
            Lista.put("48090582163", dodaj_handlowiec());
        });
    }
    @Test
    void pracownikDZ() {
        Lista.clear();
        Dyrektor d = new Dyrektor("84122381351","8","8",8,8, BigDecimal.TEN,"8",8);
        Lista.put("84122381351",d);
        String input = "04262255934\n6\n6\n6\n6\n6\n6\n7\nG";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertDoesNotThrow(() -> {
            Lista.put("0426255934", dodajDyrektor());
        });
    }

    @Test
    void HandlowiecU() {
        Lista.clear();
        Dyrektor d = new Dyrektor("84122381351","8","8",8,8, BigDecimal.TEN,"8",8);
        Lista.put("84122381351",d);
        Handlowiec k = new Handlowiec("91020287227","5","5",5,5,BigDecimal.TEN,5);
        Lista.put("91020287227",k);
        String input = "91020287227\nG";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertDoesNotThrow(() -> {
            usun(Lista);
        });
    }

    @Test
    void dyrektorU() {
        Lista.clear();
        Dyrektor d = new Dyrektor("84122381351","8","8",8,8, BigDecimal.TEN,"8",8);
        Lista.put("84122381351",d);
        Handlowiec k = new Handlowiec("91020287227","5","5",5,5,BigDecimal.TEN,5);
        Lista.put("91020287227",k);
        String input = "84122381351\nG";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertDoesNotThrow(() -> {
            usun(Lista);
        });
    }

    @Test
    void ZapisZ() {
        Lista.clear();
        Dyrektor d = new Dyrektor("84122381351","8","8",8,8, BigDecimal.TEN,"8",8);
        Lista.put("84122381351",d);
        Handlowiec k = new Handlowiec("91020287227","5","5",5,5,BigDecimal.TEN,5);
        Lista.put("91020287227",k);
        String input = "Z\ntpa\nG";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertDoesNotThrow(() -> {
            zapis();
        });
    }

    @Test
    void OdczytZ() {
        Lista.clear();
        String input = "tpa\nG";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertDoesNotThrow(() -> {
            odczyt();
        });
    }

    @Test
    void odczytZB() {
        Lista.clear();
        String input = "wywala\nG";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertThrows(RuntimeException.class, () -> {
            odczyt();
        });
    }
}



