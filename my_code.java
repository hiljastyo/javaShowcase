/*

Toteuta Java-ohjelma, joka käyttää Comparator rajapintaa ja lambda -lausekkeita listan alkioiden
järjestämiseen.

Luo luokka Person, jolla on seuraavat ominaisuudet:
    String name (nimi)
    int age (ikä)
    int height (pituus senttimetreinä)

Tee luokalle yksi muodostin, jossa annetaan kaikkien attribuutien arvot
Tee toString() -metodi, jossa tulostetaan kaikkien attribuuttien arvot pilkulla erotettuina.

Tee luokka PersonUtils, jossa on seuraavat static-tyyppiset metodit

    public static List<Person> alustaHenkilot(), joka palauttaa listan, jossa viiden henkilon tiedot
    public static void lajitteleHenkilot(List<Person> people, int choice), joka lajittelee lista choice muuttajan perusteella
    - ohessa esimerkki, jolla saat lajittelun onnistumaan (nimen mukaan)
                    case 1 -> people.sort(Comparator.comparing(person -> person.name));
    public static void tulostaHenkilot(List<Person> people), tulostaa listan henkilot allekkain

Pääohjelmassa:
- Luo henkilolista käyttäen alustaHenkilot() metodia
- Kysy käyttäjältä, millä perusteella lista lajitellaan
    1: Nimen mukaan
    2: Iän mukaan
    3: Pituuden mukaan
- Lajittele lista käyttäen lajitteleHenkilot-metodia
- Tulosta henkilöt  käyttäen tulostaHenkilot-metodia

*/


import java.util.List;
import java.util.Scanner;

public class my_code {
    public int choice;
    public static void main(String[] args) {

        List<Person> henkilot = PersonUtils.alustaHenkilot();
        Scanner scandor = new Scanner(System.in);

        System.out.println("Haluatteko henkilönne paloina vai siivuina saatana?");
        System.out.println("1: Nimen mukaan");
        System.out.println("2: Iän mukaan");
        System.out.println("3: Pituuden mukaan");
        int choice = scandor.nextInt();

        PersonUtils.lajitteleHenkilot(henkilot, choice);
        PersonUtils.tulostaHenkilot(henkilot);
        }
    }
