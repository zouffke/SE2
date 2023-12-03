Feature: Ingrediënt toevoegen aan recept

    Als menuteamlid
    Wil ik een ingrediënt kunnen toevoegen aan een recept
    Zodat ik een recept kan samenstellen.

    Background:
        Given recepten
            | recept_id | recept_naam  | recept_beschrijving  |
            | 1         | Pannenkoeken | Lekkere pannenkoeken |
            | 2         | Pizza        | Lekkere pizza        |
            | 3         | Lasagne      | Lekkere lasagne      |
            | 4         | Bechamelsaus | Lekkere Bechamelsaus |

        Given subrecepten
            | recept_id | is_subrecept_van | invoegen_na_stap |
            | 4         | 3                | 1                |

        Given ingrediënten
            | ingrediënt_id | ingrediënt_naam | product_id | recept_id | hoeveelheid |
            | 1             | bloem           | 1          | 1         | 100         |
            | 3             | kaas            | 3          | 2         | 20          |
            | 4             | kaas            | 3          | 3         | 20          |
            | 5             | kaas            | 3          | 4         | 40          |
            | 6             | melk            | 2          | 4         | 100         |

        Given bereidingsstappen
            | bereidingsstap_id | bereidingsstap_naam      | bereidingsstap_beschrijving                  | recept_id | volgnummer |
            | 1                 | stap 1                   | stap 1                                       | 1         | 1          |
            | 2                 | stap 2                   | stap 2                                       | 1         | 2          |
            | 3                 | stap 1 Kaas met bechamel | Doe de kaas in de pan                        | 3         | 1          |
            | 4                 | stap 2 Lasagne           | Zet op een zacht vuurtje gedurende 5 minuten | 3         | 2          |
            | 5                 | stap 1 Bechamelsaus      | Maak de bechamelsaus, zoek online op         | 4         | 1          |

        Given distributiecentra
            | distributiecentrum_id | distributiecentrum_naam |
            | 1                     | DC1                     |
            | 2                     | DC2                     |


        Given producten
            | product_id | product_naam       |
            | 1          | bloem              |
            | 2          | melk               |
            | 3          | kaas               |
            | 4          | zelfrijzende bloem |

        Given clausules
            | clausule_id | product_id | leverancier_id | distributiecentrum_id | aankoopprijs | hoeveelheid | start_datum | eind_datum |
            | 1           | 1          | 1              | 1                     | 1.50         | 100         | 2024-01-01  | 2024-01-31 |
            | 2           | 1          | 2              | 2                     | 1.35         | 80          | 2024-01-01  | 2024-01-31 |
            | 3           | 1          | 1              | 1                     | 1.50         | 100         | 2024-01-01  | 2024-01-31 |
            | 4           | 1          | 2              | 2                     | 1.35         | 80          | 2024-01-01  | 2024-01-31 |
            | 5           | 2          | 1              | 1                     | 1.50         | 100         | 2024-01-01  | 2024-01-31 |
            | 6           | 2          | 2              | 2                     | 1.35         | 80          | 2024-01-01  | 2024-01-31 |
            | 7           | 1          | 1              | 1                     | 1.50         | 100         | 2024-03-01  | 2024-03-31 |
            | 8           | 1          | 2              | 2                     | 1.35         | 100         | 2024-03-01  | 2024-03-31 |
            | 9           | 1          | 1              | 1                     | 1.50         | 100         | 2024-03-01  | 2024-03-31 |
            | 10          | 1          | 2              | 2                     | 1.35         | 100         | 2024-03-01  | 2024-03-31 |
            | 11          | 2          | 1              | 1                     | 1.50         | 80          | 2024-03-01  | 2024-03-31 |
            | 12          | 2          | 2              | 2                     | 1.35         | 80          | 2024-03-01  | 2024-03-31 |
            | 13          | 2          | 1              | 1                     | 1.50         | 80          | 2024-03-01  | 2024-03-31 |
            | 14          | 2          | 2              | 2                     | 1.35         | 80          | 2024-03-01  | 2024-03-31 |
            | 15          | 3          | 1              | 1                     | 1.35         | 100         | 2024-03-01  | 2024-03-31 |
            | 16          | 3          | 2              | 2                     | 1.35         | 100         | 2024-03-01  | 2024-03-31 |
            | 17          | 3          | 1              | 1                     | 1.40         | 80          | 2024-03-01  | 2024-03-31 |
            | 18          | 3          | 2              | 2                     | 1.45         | 80          | 2024-03-01  | 2024-03-31 |


    Scenario: Ingrediënt toevoegen aan recept
        When ik product met id 2 toevoeg aan recept met id 1 met een hoeveelheid van 200
        Then heeft het recept 2 ingrediënten
        And één van de ingrediënten is product 2 met hoeveelheid 200