Testaus

Testaus on suurimmaksi osaksi hoidettu manuaalisesti käyttämällä käyttöliittymää, jonka avulla itse algoritmien toiminnan seuraaminen on helppoa. Itse algoritmeilla on automaattisia testejä, jotka vain tarkistavat että ne osaavat löytää oikeanmittaisen polun ruudukosta, mutta muuten niiden testaaminen on suoritettu manuaalisesti.

Muilla ohjelman osilla paitsi käyttöliittymällä on automaattisia junit testejä, jotka varmistavat että osat toimivat oikein. Käyttöliittymän testaaminen on jäänyt myöskin täysin manuaaliseksi tehtäväksi.

Itse suorituskykytestaus suoritettiin luomalla erikokoisia neliönmuotoisia karttoja ja ajamalla algoritmejä niissä useaan otteeseen ja lopulta ottamalla näiden ajoaikojen keskiarvo. Oheisessa kuvaajassa näkyy Map Size akselilla kartan sivun koko, eli karttojen koko oli sivu * sivu ruutua. Kaikille algoritmeille ajettiin kulmasta kulmaan reitinhaku sekä kulmasta kulmaan reitinhaku kun on satunnaiset 17% seiniä asetettu kartalle.

Testit voi toistaa avaamalla sovellus ja ajamalla algoritmejä erikokoisissa kartoissa, on kuitenkin huomattavaa että kannattaa tehdä itse piirretystä kartasta niin pieni että sitä ei näy, jotta tulokset eivät katoa ruudusta kun kartan koko kasvaa merkittävän suureksi.

![Kuvaaja tuloksista](https://github.com/Djiffit/pathfinder/blob/master/Docs/kuvaaja.png)

Kaikki testit suoritettiin Intel Xeon E3-1230 v3 suorittimella.
