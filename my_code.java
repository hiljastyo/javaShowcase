
/*
Kännyköitä valmistavassa yrityksessä on päätetty erottaa ohjelmiston toiminnallisuudet laitteistosta rajapintojen avulla.
Sinun tehtäväsi on suunnitella ja toteuttaa kaiutinta sekä mikrofonia ohjaavat rajapinnat ja toteutukset,
jotka mahdollistavat erilaisten laitteistojen käytön ilman suuria muutoksia ohjelmistoon. Tämä myös helpottaa testausta.

Luo seuraavat rajapinnat:

ITelephoneMic: Mikrofonia ohjaava rajapinta, jolla seuraavat metodit
- boolean micOn(): Palauttaa true, jos mikrofonin avaaminen onnistuu.
- boolean micOff(): Palauttaa true, jos mikrofonin sulkeminen onnistuu.
- void setVolume(int volume): Asettaa äänenvoimakkuuden arvon.
- int getVolume(): Palauttaa äänenvoimakkuuden arvon.

ITelephoneSpeaker: Kaiutinta ohjaava rajapinta, jolla seuraavat metodit
- boolean mute(): Palauttaa true, jos kaiuttimen vaimentaminen onnistuu.
- boolean unMute(): Palauttaa true, jos kaiuttimen avaaminen onnistuu.
- void setVolume(int volume): Asettaa äänenvoimakkuuden arvon.
- int getVolume(): Palauttaa äänenvoimakkuuden arvon.

Toteuta abstrakti luokka BaseMic, joka toteuttaa ITelephoneMic-rajapinnan.
Sillä on seuraavat ominaisuudet:
- protected int volume, äänenvoimakkuuden tallentamiseen.
- protected boolean isOn, seuraamaan mikrofonin tilaa.
Älä toteuta micOn(), koska se
    toteutetaan laitevalmistajien luokissa.
Toteuta seuraavat rajapinnan vaatimat metodit:
- micOff(): asettaa isOn-muuttujan arvoksi false ja palauttaa aina true.
- setVolume(), asettaa volume-muuttujan arvon
- getVolume() , palauttaa volume-muuttujan arvon
Aliluokat voivat siis ylikirjoittaa yläluokan rajapinnan kautta määritellyt metodit

Toteuta abstrakti luokka BaseSpeaker, joka toteuttaa ITelephoneSpeaker-rajapinnan.
Lisää seuraavat ominaisuudet:
- protected int volume, äänenvoimakkuuden tallentamiseen.
- protected boolean isMuted, seuraamaan kaiuttimen tilaa.
Toteuta seuraavat rajapinnan vaatimat metodit:
- setVolume(), asettaa volume-muuttujan arvon
- getVolume() , palauttaa volume-muuttujan arvon
- mute(): asettaa isMuted-muuttujan arvoksi true ja palauttaa aina true.
Aliluokat voivat siis ylikirjoittaa yläluokan rajapinnan kautta määritellyt metodit
Älä toteuta unMute(), koska se
    toteutetaan laitevalmistajien luokissa.

Toteuta seuraavat luokat eri laitteistokonfiguraatioille:

BroadcomMic, joka periytyy BaseMic luokasta:
Toteuta seuraavat rajapinnan vaatimat metodit:
- micOn(), Jos äänenvoimakkuus on yli 100,  asetetaan isOn falseksi ja metodi palauttaa false, muutoin true.
- Muodostimia ja set/get -pareja ei tarvitse tehdä

SamsungMic, joka periytyy BaseMic luokasta:
Toteuta seuraavat rajapinnan vaatimat metodit:
- micOn(), Jos äänenvoimakkuus on yli 60,  asetetaan isOn falseksi ja metodi palauttaa false, muutoin true.
- Muodostimia ja set/get -pareja ei tarvitse tehdä

BroadcomSpeaker, joka periytyy BaseMSpeaker luokasta:
Toteuta seuraavat rajapinnan vaatimat metodit:
- unMute(), Jos äänenvoimakkuus on yli 100,  asetetaan isMuted falseksi ja metodi palauttaa false, muutoin true.
- Muodostimia ja set/get -pareja ei tarvitse tehdä

QualcommSpeaker, joka periytyy BaseSpeaker luokasta:
Toteuta seuraavat rajapinnan vaatimat metodit:
- unMute(), Jos äänenvoimakkuus on yli 80,  asetetaan isMuted trueksi ja metodi palauttaa false, muutoin true.
- Muodostimia ja set/get -pareja ei tarvitse tehdä

Tee luokka CellPhone, jolla on seuraavat ominaisuudet:
- ITelephoneMic mic
- iTelephone speaker
Tee luokalle seuraavat muodostimet:
- oletusmuodostin
- muodostin, jossa annetaan molemmat attribuutit
Tee luokalle getterit ja setterit

Ohesssa valmis pääohjelma. Testaa sen avulla onko määrittelyt tehty oikein

Esimerkkituloste:

Jos kaiuttimen äänenvoimakkuus on 10, niin ekan puhelimen kaiutin voitiin laittaa päälle
Jos kaiuttimen äänenvoimakkuus on 200, niin ekan puhelimen kaiutinta ei voida laittaa päälle
Jos mikin äänenvoimakkuus on 45, niin ekan puhelimen mikki voitiin laittaa päälle
Jos mikin äänenvoimakkuus on 70, niin ekan puhelimen mikkiä ei voida laittaa päälle

Jos kaiuttimen äänenvoimakkuus on 88, niin tokan puhelimen kaiutin voitiin laittaa päälle
Jos kaiuttimen äänenvoimakkuus on 102, niin tokan puhelimen kaiutinta ei voida laittaa päälle
Jos mikin äänenvoimakkuus on 99, niin tokan puhelimen mikki voitiin laittaa päälle
Jos mikin äänenvoimakkuus on 101, niin tokan puhelimen mikkiä ei voida laittaa päälle

*/


import java.time.LocalDate;
import java.util.Scanner;

public class my_code {

    public static void main(String[] args) {
        // Luodaan eka känny
        CellPhone cp1 = new CellPhone();
        SamsungMic ssm1 = new SamsungMic();
        QualcommSpeaker qcs1 = new QualcommSpeaker();

        cp1.setMic (ssm1);
        cp1.setSpeaker(qcs1);

        // Luodaan toka känny
        CellPhone cp2 = new CellPhone(new BroadcomMic(), new BroadcomSpeaker());

        // Lähdetään testailemaan, toimiiko laitevalmistajakohtaiset ohjelmistorajoitteet

        cp1.getSpeaker().setVolume(10);
        if (cp1.getSpeaker().unMute()) {
            System.out.println("Jos kaiuttimen äänenvoimakkuus on 10, niin ekan puhelimen kaiutin voitiin laittaa päälle");
        }
        cp1.getSpeaker().setVolume(200);
        if (!cp1.getSpeaker().unMute()) {
            System.out.println("Jos kaiuttimen äänenvoimakkuus on 200, niin ekan puhelimen kaiutinta ei voida laittaa päälle");
        }

        cp1.getMic().setVolume(45);
        if (cp1.getMic().micOn()) {
            System.out.println("Jos mikin äänenvoimakkuus on 45, niin ekan puhelimen mikki voitiin laittaa päälle");
        }
        cp1.getMic().setVolume(70);
        if (!cp1.getMic().micOn()) {
            System.out.println("Jos mikin äänenvoimakkuus on 70, niin ekan puhelimen mikkiä ei voida laittaa päälle");
        }

        System.out.println();

        cp2.getSpeaker().setVolume(88);
        if (cp2.getSpeaker().unMute()) {
            System.out.println("Jos kaiuttimen äänenvoimakkuus on 88, niin tokan puhelimen kaiutin voitiin laittaa päälle");
        }
        cp2.getSpeaker().setVolume(102);
        if (!cp2.getSpeaker().unMute()) {
            System.out.println("Jos kaiuttimen äänenvoimakkuus on 102, niin tokan puhelimen kaiutinta ei voida laittaa päälle");
        }

        cp2.getMic().setVolume(99);
        if (cp2.getMic().micOn()) {
            System.out.println("Jos mikin äänenvoimakkuus on 99, niin tokan puhelimen mikki voitiin laittaa päälle");
        }
        cp2.getMic().setVolume(101);
        if (!cp2.getMic().micOn()) {
            System.out.println("Jos mikin äänenvoimakkuus on 101, niin tokan puhelimen mikkiä ei voida laittaa päälle");
        }
    }

}

