Säännöllisten lauseiden tulkki
=====
Käyttöohje

Ohjelma ei toimi itsenäisesti, vaan sitä tulee käyttää kirjastona osana projektia. Kirjaston löytää .jar-tiedostona releasesta.

Tulkki kertoo vastaako merkkijono annettua säännöllistä lauseketta. Jos säännöllistä lauseketta käytetään vain kerran, voidaan kutsua Regex.vastaakoSyote(String syote, String regex), jolloin metodi rakentaa samalla automaatin. Siinä tapauksessa, että lauseketta tarvitaan useammin, kannattaa luoda ensiksi säännöllistä lauseketta vastaava automaatti, ja sitten kutsua Regex.vastaakoSyote(String syote, Automaatti automaatti). Näin automaattia ei tarvitse rakentaa joka kerta uudelleen.

Automaatin rakennus toimii kutsumalla Automaatti-luokan metodia Automaatti.luoAutomaattiRegexista(String regex),joka palauttaa regexiä vastaavan automaatin.

Säännölliset lausekkeet toimivat samoin kuin Javassa, mutta koko Javan toiminnallisuutta ei ole toteutettu.
Ohjelma tukee tällä hetkellä seuraavia säännöllisten lauseiden operaattoreita:

X*      X, 0..* kertaa
X+      X, 1..* kertaa
X?      X, 0..1 kertaa
XY      X, jonka jälkeen seuraa Y
X|Y     Joko X tai Y

HUOM! Tulkki osaa käsitellä ainoastaan syötteitä, joissa on vain kirjaimia!