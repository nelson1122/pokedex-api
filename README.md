# Pokedex REST Api -  Documentation

![PokeApi](src/main/resources/pokeapi.png?raw=true "PokeApi")


Challenge developed by **Nelson Quemá, Eng.** for the Modyo Company

Framework: Java SpringBoot 2.5.2

Available REST services

* **1 - List pokemon info by page size and page number (GET Request)**
  
  Request example:
  ~~~
  https://nelson1122-pokedex-api.herokuapp.com/pokemon/info?pageSize=2&pageNumber=1
  ~~~
  Response example:
    ```
  [
        {
            "abilities": [
                "overgrow",
                "chlorophyll"
            ],
            "height": 7,
            "id": 1,
            "name": "bulbasaur",
            "photo": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
            "types": [
                "grass",
                "poison"
            ],
            "weight": 69
        },
        {
            "abilities": [
                "overgrow",
                "chlorophyll"
            ],
            "height": 10,
            "id": 2,
            "name": "ivysaur",
            "photo": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/2.png",
            "types": [
                "grass",
                "poison"
            ],
            "weight": 130
        }
  ]
    ```

* **2 - Find pokemon info by Id (GET Request)**    
  
  Request Example:
  ~~~
  https://nelson1122-pokedex-api.herokuapp.com/pokemon/info/3
  ~~~

  Response Example:

    ```
    {
        "abilities": [
            "immunity",
            "thick-fat",
            "gluttony"
        ],
        "descriptions": [
            "Very lazy. Just eats and sleeps. As its rotund bulk builds, it becomes steadily more slothful.",
            "Will eat anything, even if the food happens to be a little moldy. It never gets an upset stomach.",
            "What sounds like its cry may ac­ tually be its snores or the rumblings of its hungry belly.",
            "Its stomach's di­ gestive juices can dissolve any kind of poison. It can even eat things off the ground.",
            "This POKéMON's stomach is so strong, even eating moldy or rotten food will not affect it.",
            "SNORLAX’s typical day consists of nothing more than eating and sleeping. It is such a docile POKéMON that there are children who use its expansive belly as a place to play.",
            "SNORLAX’s typical day consists of nothing more than eating and sleeping. It is such a docile POKéMON that there are children who use its big belly as a place to play.",
            "It is not satisfied unless it eats over 880 pounds of food every day. When it is done eating, it goes promptly to sleep.",
            "Its stomach can digest any kind of food, even if it happens to be moldy or rotten.",
            "It stops eating only to sleep. It doesn’t feel full unless it eats nearly 900 pounds a day.",
            "When its belly is full, it becomes too lethargic to even lift a finger, so it is safe to bounce on its belly.",
            "What sounds like its cry may actually be its snores or the rumblings of its hungry belly.",
            "Its stomach’s digestive juices can dissolve any kind of poison. It can even eat things off the ground.",
            "Snorlax’s typical day consists of nothing more than eating and sleeping. It is such a docile Pokémon that there are children who use its expansive belly as a place to play.",
            "Its stomach is said to be incomparably strong. Even Muk’s poison is nothing more than a hint of spice on Snorlax’s tongue.",
            "It eats nearly 900 pounds of food every day. It starts nodding off while eating—and continues to eat even while it’s asleep.",
            "It doesn’t do anything other than eat and sleep. When prompted to make a serious effort, though, it apparently displays awesome power.",
            "It has no interest in anything other than eating. Even if you climb up on its stomach while it’s napping, it doesn’t seem to mind at all!",
            "Gigantamax energy has affected stray seeds and even pebbles that got stuck to Snorlax, making them grow to a huge size.",
            "Terrifyingly strong, this Pokémon is the size of a mountain—and moves about as much as one as well.",
            "This Pokémon’s stomach is so strong, even eating moldy or rotten food will not affect it."
        ],
        "evolvesFromSpecies": "munchlax",
        "height": 21,
        "id": 143,
        "name": "snorlax",
        "photo": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/143.png",
        "types": [
            "normal"
        ],
        "weight": 4600
    }
    ```