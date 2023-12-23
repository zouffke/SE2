Feature: Suggesties krijgen voor ingredienten

  Als menuteamlid
  Wil ik suggesties krijgen voor ingredienten
  Zodat ik snel een geschikt ingredient kan toevoegen aan een recept.

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
      | 11          | 3           | 1.50         | 100         | liter    | 2024-03-01  | 2024-03-31 |
      | 12          | 4           | 1.35         | 80          | liter    | 2024-03-01  | 2024-03-31 |
      | 13          | 3           | 1.50         | 100         | liter    | 2024-03-01  | 2024-03-31 |
      | 14          | 4           | 1.35         | 80          | liter    | 2024-03-01  | 2024-03-31 |
      | 15          | 5           | 1.35         | 100         | kilogram | 2024-03-01  | 2024-03-31 |
      | 16          | 6           | 1.35         | 100         | kilogram | 2024-03-01  | 2024-03-31 |
      | 17          | 5           | 1.40         | 80          | kilogram | 2024-03-01  | 2024-03-31 |
      | 18          | 6           | 1.45         | 80          | kilogram | 2024-03-01  | 2024-03-31 |

  Scenario: Lijst gesorteerd op procentuele verschil tussen de huidige weekgemiddelde aankoopprijs
    Given het is vandaag "2024"-"03"-"01"
    When ik de suggesties raadpleeg
    Then krijg ik een lijst van 3 producten, gesorteerd op wekelijkse gemiddelde aankoopprijs
    And product 1 in de lijst is 1
    And product 2 in de lijst is 2
    And product 3 in de lijst is 3