Käyttöohje

Ohjelma toimii suorittamalla pathfinderXXXXpikseli.jar tiedosto, joka löytyy juurihakemistosta.

Kun ohjelma on avattu, käyttöliittymä aukeaa oikein jos sovelluksen pystyresoluutio on pienempi kuin sitä ajavan koneen, sillä Swing komponentit eivät skaalaudu oikein. Tätä varten sovelluksesta on 2 versiota joista toinen on 600 pikseliä korkea ja toinen 1000.

Jos sovellus on auennut oikein, näkyy kartta sekä teksti 0ms ja kasa nappuloita. Algoritmeja voi ajaa niiden nappuloilla, jotka ratkaisevat sen hetken ruudukon. + ja - nappuloilla voi suurentaa tai pienentää ruutujen kokoa 50 ruudulla. Width ja height nappuloilla voi muuttaa ruutujen määrää, mutta koska ikkunaa ei voi skaalata pystysuunnassa niin koko kentän nähdäkseen voi joutua pienentämään ruutuja. Random walls nappula asettaa kentän täyteen satunnaisesti sijoitettuja seiniä, mutta voi myös luoda ruudukon, jota ei voi ratkaista. Tällöin ratkaisun yrittäminen aiheuttaa vain erroreita. Destroy walls nappula tuhoaa seinät ja asettaa aloituspisteet vasempaan yläkulmaan ja maalin oikeaan alakulmaan.

Algoritmien toiminnallisuutta voi testata niitä ajamalla, kun algoritmi on ajettu, ruutuun ilmestyy siihen käytetty aika sekä löydetyn reitin pituus.

Ruudukkoon voi piirtää omia sokkeloita vasenta hiirenpainiketta pohjassa pitämällä. Samanlailla saa myös seinät poistettua. Maalin ja lähdön paikkaa voi myös muuttaa vetämällä sitä vasemmalla hiirenpainikkeella uuteen paikkaan.
