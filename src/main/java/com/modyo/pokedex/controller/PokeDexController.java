package com.modyo.pokedex.controller;

import com.modyo.pokedex.service.PokeDexService;
import flexjson.JSONSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.modyo.pokedex.util.Constants.ERROR_MESSAGE_INFO_ALL;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@Controller
@RequestMapping(value = "/pokemon/info", produces = MediaType.APPLICATION_JSON_VALUE)
public class PokeDexController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PokeDexController.class);

    @Autowired
    PokeDexService service;

    @GetMapping()
    public ResponseEntity<?> findPokemonsInfo(
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber
    ) {
        try {
            return new ResponseEntity<>(
                    serializePokemonListInfo(service.findPokemonsInfo(pageSize, pageNumber)),
                    OK
            );
        } catch (Exception ex) {
            LOGGER.error(ERROR_MESSAGE_INFO_ALL, ex);
            return new ResponseEntity<>(
                    ERROR_MESSAGE_INFO_ALL,
                    INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findPokemonInfoById(
            @PathVariable("id") Integer id
    ) {
        try {
            return new ResponseEntity<>(
                    serializePokemonInfo(service.findPokemonInfoById(id)),
                    OK
            );
        } catch (Exception ex) {
            LOGGER.error(ERROR_MESSAGE_INFO_ALL, ex);
            return new ResponseEntity<>(
                    ERROR_MESSAGE_INFO_ALL,
                    INTERNAL_SERVER_ERROR
            );
        }
    }

    private String serializePokemonListInfo(Object pokemonListInfo) {
        return new JSONSerializer()
                .exclude("*.class")
                .include("abilities")
                .include("types")
                .exclude("evolvesFromSpecies")
                .serialize(pokemonListInfo);
    }

    private String serializePokemonInfo(Object pokemonInfo) {
        return new JSONSerializer()
                .exclude("*.class")
                .include("abilities")
                .include("types")
                .include("descriptions")
                .serialize(pokemonInfo);
    }
}
