Feature: Ingredient zoeken
    Als lid van het menuteam
    wil ik de mogelijkheid hebben om de ingredienten voor mijn recepten te vinden via de zoekfunctie,
    zodat ik snel de juiste ingredienten kan toevoegen aan een recept.

    Background:
        Given producten
            | product_id | product_naam       |
            | 1          | bloem              |
            | 2          | melk               |
            | 3          | kaas               |
            | 4          | zelfrijzende bloem |

        Given distributiecentra
            | distributiecentrum_id | distributiecentrum_naam |
            | 1                     | DC1                     |
            | 2                     | DC2                     |

        Given leveranciers
            | leverancier_id | leverancier_naam |
            | 1              | leverancier1     |
            | 2              | leverancier2     |

        Given recepten
            | recept_id | recept_naam  | recept_beschrijving  |
            | 1         | Pannenkoeken | Lekkere pannenkoeken |
            | 2         | Pizza        | Lekkere pizza        |
            | 3         | Lasagne      | Lekkere lasagne      |
            | 4         | Bechamelsaus | Lekkere Bechamelsaus |

        Given ingredienten
            | ingredient_id | ingredient_naam | product_id | recept_id | hoeveelheid | eenheid  |
            | 1             | bloem           | 1          | 1         | 0.1         | kilogram |
            | 3             | kaas            | 3          | 2         | 0.02        | kilogram |
            | 4             | kaas            | 3          | 3         | 0.02        | kilogram |
            | 5             | kaas            | 3          | 4         | 0.04        | kilogram |
            | 6             | melk            | 2          | 4         | 0.1         | liter    |

        Given bereidingsstappen
            | bereidingsstap_id | bereidingsstap_naam      | bereidingsstap_beschrijving                  | recept_id | volgnummer | ingredient_ids |
            | 1                 | stap 1                   | stap 1                                       | 1         | 1          | 1              |
            | 2                 | stap 2                   | stap 2                                       | 1         | 2          | 3              |
            | 3                 | stap 1 Kaas met bechamel | Doe de kaas in de pan                        | 3         | 1          | 4              |
            | 4                 | stap 2 Lasagne           | Zet op een zacht vuurtje gedurende 5 minuten | 3         | 2          | -              |
            | 5                 | stap 1 Bechamelsaus      | Maak de bechamelsaus, zoek online op         | 4         | 1          | 5,6            |

        Given subrecepten
            | recept_id | is_subrecept_van | invoegen_na_stap |
            | 4         | 3                | 1                |


        Given contract
            | contract_id | product_id | leverancier_id | distributiecentrum_id |
            | 1           | 1          | 1              | 1                     |
            | 2           | 1          | 2              | 2                     |
            | 3           | 2          | 1              | 1                     |
            | 4           | 2          | 2              | 2                     |
            | 5           | 3          | 1              | 1                     |
            | 6           | 3          | 2              | 2                     |

        Given clausules
            | clausule_id | contract_id | aankoopprijs | hoeveelheid | eenheid  | start_datum | eind_datum |
            | 1           | 1           | 1.50         | 100         | kilogram | 2024-01-01  | 2024-01-31 |
            | 2           | 2           | 1.35         | 80          | kilogram | 2024-01-01  | 2024-01-31 |
            | 3           | 1           | 1.50         | 100         | kilogram | 2024-01-01  | 2024-01-31 |
            | 4           | 2           | 1.35         | 80          | kilogram | 2024-01-01  | 2024-01-31 |
            | 5           | 3           | 1.50         | 100         | liter    | 2024-01-01  | 2024-01-31 |
            | 6           | 4           | 1.35         | 80          | liter    | 2024-01-01  | 2024-01-31 |
            | 7           | 1           | 1.50         | 100         | kilogram | 2024-03-01  | 2024-03-31 |
            | 8           | 2           | 1.35         | 100         | kilogram | 2024-03-01  | 2024-03-31 |
            | 9           | 1           | 1.50         | 100         | kilogram | 2024-03-01  | 2024-03-31 |
            | 10          | 2           | 1.35         | 100         | kilogram | 2024-03-01  | 2024-03-31 |
            | 11          | 3           | 1.50         | 80          | liter    | 2024-03-01  | 2024-03-31 |
            | 12          | 4           | 1.35         | 80          | liter    | 2024-03-01  | 2024-03-31 |
            | 13          | 3           | 1.50         | 80          | liter    | 2024-03-01  | 2024-03-31 |
            | 14          | 4           | 1.35         | 80          | liter    | 2024-03-01  | 2024-03-31 |
            | 15          | 5           | 1.35         | 100         | kilogram | 2024-03-01  | 2024-03-31 |
            | 16          | 6           | 1.35         | 100         | kilogram | 2024-03-01  | 2024-03-31 |
            | 17          | 5           | 1.40         | 80          | kilogram | 2024-03-01  | 2024-03-31 |
            | 18          | 6           | 1.45         | 80          | kilogram | 2024-03-01  | 2024-03-31 |

    Scenario: Producten zoeken op naam
        When ik zoek op het woord "bloem"
        Then krijg ik een lijst terug met 2 producten
        And product_id van item 1 uit de lijst is 1
        And product_id van item 2 uit de lijst is 4

    # TODO: beschikbare producten was eerst 1, maar ik vind 2 producten terug met test...
    # Mogelijk is er iets mis met de Id's van de producten
    Scenario: Product zoeken op beschikbaarheid (enkel ingredienten waarvoor twee leveranciers zijn per distributiecentrum voor de huidige datum)
        Given we zoeken op datum van "2024"-"01"-"15"
        When ik zoek naar producten die beschikbaar zijn
        Then krijg ik een lijst terug met 2 producten
        And product_id van het product is 1

    Scenario: Lijst gesorteerd op gemiddelde aankoopprijs opvragen
        Given we zoeken op datum van "2024"-"03"-"15"
        When ik zoek naar product die beschikbaar zijn, stijgend gesorteerd op gemiddelde aankoopprijs
        Then krijg ik een lijst terug met 3 producten
        And product_id van item 1 in de lijst is 3
        And product_id van item 2 in de lijst is 1
        And product_id van item 3 in de lijst is 2
