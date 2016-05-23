Tämä projekti tulee käsittelemään polunhakualgoritmejä ruutuihin pohjautuvalla kartalla. Kartta sisältää ainakin seiniä, mutta myös mahdollisesti erilaisia ruutuja, joilla saadaan painotettu verkko. Algoritmeinä projektissa tulee toimimaan A*, Dijkstran algoritmi ja leveyssuuntainen haku. Näitä varten joudun toteuttamaan ainakin jonkin priorisoivan tietorakenteen, todennäköisesti tässä tapauksessa priorisoivan jonon. Heuristiikkana A *:ssa tulen käyttämään Manhattan etäisyyttä.

Nämä algoritmit ovat valittu, koska niitä käytetään yleensä polunvalinnan ongelmia ratkottaessa. Priorisoivan keon teen, sillä algoritmit vaativat ainakin jonkinlaisen priorisoivan tietorakenteen toimiakseen kunnolla.

Ohjelma tulee todennäköisesti generoimaan satunnaisia karttoja, joihin käytetään kaikkia algoritmeja ja vertaillaan, minkälaisia reittejä ja kuinka nopeasti ne niitä löytävät.

Tavoitteena sovellukselle on algoritmien alkuperäiset tilavaatimukset O(n) ja aikavaatimuksena Dijkstran pahimman tapauksen O((E-V)log V)

Lähdemateriaalina käytetty:

http://theory.stanford.edu/~amitp/GameProgramming/AStarComparison.html

https://en.wikipedia.org/wiki/A*_search_algorithm

https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
