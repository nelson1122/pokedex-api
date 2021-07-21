package com.modyo.pokedex.service;

import com.modyo.pokedex.dto.PokemonInfoDTO;

import java.util.List;

public interface PokeDexService {
    List<PokemonInfoDTO> findPokemonsInfo(Integer pageSize, Integer pageNumber);
    PokemonInfoDTO findPokemonInfoById(Integer id);
}
