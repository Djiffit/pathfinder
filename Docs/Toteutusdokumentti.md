Toteutus

Ohjelma muodostuu algoritmeista, A*, Dijkstra ja leveyssuuntainen haku. Näistä Dijkstra ja A* käyttävät hyödykseen PriorityQueueta valitakseen seuraavaksi käsiteltävän ruudun. Leveyssuuntainen haku taas puolestaan käyttää tavallista jonoa. Nämä tietorakenteet löytyvät tietorakenteiden paketista, jossa on myös solmuluokka, joita PriorityQueue käyttää. Reitinhakualgoritmit myös tarvitsevat kartan joka on omassa map- paketissaan, kartta taas koostuu monista ruuduista jotka ovat tiles paketissa. Sovelluksen käyttöliittymä on ui paketissa, jossa on käyttöliittymän avaava UserInterface luokka, hiiren toiminnallisuudesta huolta pitävä MouseFunctionality sekä MapRender, joka huolehtii siitä, että kartta piirretään käyttöliittymään niin kuin kuuluukin. 

