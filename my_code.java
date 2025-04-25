
/*

Tiedostoon kirjoittaminen ohjelmassa voi pysäyttää pääohjelman toiminnan liian pitkäksi aikaa.
Ratkaistaan ongelma  toteuttamalla mekanismi, jossa tiedostoon kirjoittaminen tapahtuu taustalla erillisessä säikeessä.
Lisäksi toteutetaan  ratkaisu, jossa lukusäie odottaa kirjoitussäikeen valmistumista ilman keinotekoista viivettä,
varmistaen ohjelman tehokkuuden ja luotettavuuden.


Tee luokka FileUtils, jossa seuraavat static -tyypiset muuttujat:
- private static final Object lock = new Object(); // muuttuja lukemisen / kirjoittamisen tahdistamiseen
- private static boolean writeCompleted = false; // lippu, jolla kerrotaan, että kirjoitus on ohi

Luokalla on myös seuraavat  static-tyyppiset metodit:

- public static void WriteToFile(String fileName, String content)`
     - Käynnistää taustasäikeen, joka kirjoittaa annetun merkkijonon "content" tiedostoon "filename".
     - Edellisen tiedoston sisältö korvataan uudella (append-toimintoa ei tarvitse toteuttaa).
     - Kirjoittamisen valmistumisen jälkeen säie ilmoittaa tästä muille säikeille synkronoinnin avulla.
     - Siis kun kirjoitus on ohi, kutsutaan seuraavaa koodia
                // Signal that writing is completed
                synchronized (lock) {
                    writeCompleted = true;
                    lock.notifyAll();
                }

- public static string ReadFromFile(String fileName)
     - Lukee annetun tiedoston sisällön ja palauttaa sen merkkijonona.
     - Tätä metodia kutsutaan erillisessä lukusäikeessä.
     - Ennen kuin lukeminen aloitetaan säikeessä, kutsutaan  metodia  WaitForWriteCompletion()

public static void WaitForWriteCompletion()
     - Synkronointimekanismi, joka odottaa, kunnes kirjoitussäie on valmis. Tätä kutsutaan lukusäikeessä ennen tiedoston lukemista.
     - Ohessa saat ko. metodin valmiina

    // Method for the reader thread to wait until write is complete
    public static void WaitForWriteCompletion() {
        synchronized (lock) {
            while (!writeCompleted) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    System.err.println("Waiting interrupted: " + e.getMessage());
                }
            }
        }
    }



Pääohjelma
   - Kysyy käyttäjältä:
     - Tiedoston nimen, johon tiedot kirjoitetaan.
     - Tekstin, joka halutaan kirjoittaa tiedostoon.
   - Käynnistää kirjoitussäikeen käyttämällä `FileUtils.WriteToFile`-metodia.
   - Käynnistää säikeen, joka:
     - Odottaa kirjoitussäikeen valmistumista (`WaitForWriteCompletion`).
     - Lukee tiedoston sisällön ja tulostaa sen konsoliin.

Ohessa ajoesimerkki:

    Enter the file name: teksti
    Enter the content to write to the file: Jotain kivaa
    Data written to file: teksti

    Content of the file:
    Jotain kivaa

Tämän harjoituksen tarkoituksena on:
- Ymmärtää tiedostokäsittelyn suorittamista taustasäikeessä.
- Käyttää synkronointimekanismeja, kuten `wait()` ja `notify()`, eri säikeiden välisen toiminnan hallintaan.
- Varmistaa, että lukuoperaatio tehdään vasta kirjoituksen valmistuttua ilman keinotekoista viivettä (esim. `Thread.sleep`).


*/

// buffered reader > Scanner jos käytetään isompia syötteitä
// selvitettävä onko näin


import java.io.BufferedReader;
import java.io.InputStreamReader;

public class my_code {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.print("Enter the file name: ");
            String fileName = reader.readLine();

            System.out.println("Write STOP to the new line to stop writing in to the file");
            System.out.println("Enter the content to write to the file: ");
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                if ("STOP".equalsIgnoreCase(line)) {
                    break;
                }
                builder.append(line).append(System.lineSeparator());
            }

            FileUtils.WriteToFile(fileName, builder.toString());

            Thread readerThread = new Thread(() -> {
                FileUtils.WaitForWriteCompletion();
                String fileContent = FileUtils.ReadFromFile(fileName);
                System.out.println("\nContent of the file:");
                System.out.println(fileContent);
            });

            readerThread.start();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}