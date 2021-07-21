package com.modyo.pokedex.dao;

import com.fasterxml.jackson.databind.JsonNode;

public interface PokeApiDao {
    JsonNode findPokemonInfoFromApiById(Integer id);
    JsonNode findPokemonSpecieInfoFromApiById(Integer id);
}
