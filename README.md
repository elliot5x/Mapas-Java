# Marques's Map

Um visualizador de mapas em tempo real desenvolvido em Java, utilizando a biblioteca ``JXMapViewer2`` e integrando os serviços de satélite do Bing Maps.

_O projeto foca em alta performance e manipulação direta de coordenadas através da Projeção de [Mercator](https://pt.wikipedia.org/wiki/Proje%C3%A7%C3%A3o_de_Mercator)._

---
# Detalhes Técnicos

A Lógica QuadKey
O diferencial deste projeto é a sobrescrita da classe ``TileFactoryInfo``. Em vez de usar URLs convencionais, o código implementa a lógica necessária para o sistema Bing Maps QuadKey:
- Manipulação de Bits: Utiliza operações de deslocamento (1 << (i - 1)) e máscaras lógicas (&) para identificar quadrantes geográficos. 
- Projeção de Mercator: Traduz coordenadas de latitude/longitude em uma grade plana de pixels (256x256).

---
## Outras informações:
- _Linguagem: Java_ 
- _Interface: Java Swing_ 
- _Biblioteca: JXMapViewer2 (v2.6)_
- _Build Tool: Maven_