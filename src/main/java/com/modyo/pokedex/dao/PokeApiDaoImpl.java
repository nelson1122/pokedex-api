package com.modyo.pokedex.dao;

import com.fasterxml.jackson.databind.JsonNode;
import com.modyo.pokedex.util.RestTemplateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PokeApiDaoImpl implements PokeApiDao {

    @Value("${com.modyo.pokeapi.url}")
    private String pokeApiUrl;

    @Autowired
    RestTemplateHelper restTemplateHelper;

    @Override
    public JsonNode findPokemonInfoFromApiById(Integer id) {
        String url = pokeApiUrl + "pokemon/" + id;
        return restTemplateHelper.getForEntity(url, JsonNode.class);
    }

    public JsonNode findPokemonSpecieInfoFromApiById(Integer id) {
        String url = pokeApiUrl + "pokemon-species/" + id;
        return restTemplateHelper.getForEntity(url, JsonNode.class);
    }
}
