package com.modyo.pokedex.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.modyo.pokedex.dao.PokeApiDao;
import com.modyo.pokedex.dto.PokemonInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.modyo.pokedex.util.Constants.*;

@Service
public class PokeDexServiceImpl implements PokeDexService {

    @Autowired
    PokeApiDao pokeApiDao;

    @Override
    public List<PokemonInfoDTO> findPokemonsInfo(Integer pageSize, Integer pageNumber) {
        List<PokemonInfoDTO> pokemonsInfo = new ArrayList<>();
        int offset = (pageNumber - 1) * pageSize;
        for (int id = offset + 1; id <= pageNumber * pageSize; id++) {
            PokemonInfoDTO info = findPokemonInfoDTOById(id, false);
            pokemonsInfo.add(info);
        }
        return pokemonsInfo;
    }

    @Override
    public PokemonInfoDTO findPokemonInfoById(Integer id) {
        return findPokemonInfoDTOById(id, true);
    }

    private PokemonInfoDTO findPokemonInfoDTOById(Integer id, boolean findDetails) {
        JsonNode pokemonInfoFromApi = pokeApiDao.findPokemonInfoFromApiById(id);
        PokemonInfoDTO info = new PokemonInfoDTO();
        info.setId(pokemonInfoFromApi.get(POKEAPI_ID_KEY).asInt());
        info.setName(pokemonInfoFromApi.get(POKEAPI_NAME_KEY).asText());
        info.setPhoto(pokemonInfoFromApi.get(POKEAPI_SPRITES_KEY).get(POKEAPI_FRONT_DEFAULT_KEY).asText());
        info.setHeight(pokemonInfoFromApi.get(POKEAPI_HEIGHT_KEY).asInt());
        info.setWeight(pokemonInfoFromApi.get(POKEAPI_WEIGHT_KEY).asInt());
        for (JsonNode pokemonAbilities : pokemonInfoFromApi.get(POKEAPI_ABILITIES_KEY)) {
            info.getAbilities().add(pokemonAbilities.get(POKEAPI_ABILITY_KEY).get(POKEAPI_NAME_KEY).asText());
        }
        for (JsonNode pokemonTypes : pokemonInfoFromApi.get(POKEAPI_TYPES_KEY)) {
            info.getTypes().add(pokemonTypes.get(POKEAPI_TYPE_KEY).get(POKEAPI_NAME_KEY).asText());
        }
        if (findDetails) {
            JsonNode pokemonSpecieInfoFromApi = pokeApiDao.findPokemonSpecieInfoFromApiById(id);
            JsonNode pokemonFlaviorTextEntries = pokemonSpecieInfoFromApi.get(POKEAPI_FLAVOR_TEXT_ENTRIES_KEY);

            List<String> pokemonDescriptions =
                    StreamSupport.stream(pokemonFlaviorTextEntries.spliterator(), false)
                            .filter(x -> x.get(POKEAPI_LANGUAGE_KEY).get(POKEAPI_NAME_KEY).asText().equals(POKEAPI_LANGUAGE_EN_KEY))
                            .map(x -> {
                                String flavorText = x.get(POKEAPI_FLAVOR_TEXT_KEY).asText();
                                return flavorText.replaceAll("[\n\f]", SPACE_CHARACTER);
                            })
                            .distinct()
                            .collect(Collectors.toList());
            info.getDescriptions().addAll(pokemonDescriptions);
            JsonNode pokemonEvolvesFrom = pokemonSpecieInfoFromApi.get(POKEAPI_EVOLVES_FROM_SPECIES_KEY);
            info.setEvolvesFromSpecies(pokemonEvolvesFrom.isNull() ? null : pokemonEvolvesFrom.get(POKEAPI_NAME_KEY).asText());
        }
        return info;
    }
}
