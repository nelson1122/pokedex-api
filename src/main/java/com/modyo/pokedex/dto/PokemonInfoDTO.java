package com.modyo.pokedex.dto;

import java.util.ArrayList;
import java.util.List;

public class PokemonInfoDTO {
    private Integer id;
    private String name;
    private String photo;
    private Integer height;
    private Integer weight;
    private List<String> abilities;
    private List<String> types;
    private List<String> descriptions;
    private String evolvesFromSpecies;

    public PokemonInfoDTO() {
        this.types = new ArrayList<>();
        this.abilities = new ArrayList<>();
        this.descriptions = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public List<String> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<String> abilities) {
        this.abilities = abilities;
    }

    public List<String> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<String> descriptions) {
        this.descriptions = descriptions;
    }

    public String getEvolvesFromSpecies() {
        return evolvesFromSpecies;
    }

    public void setEvolvesFromSpecies(String evolvesFromSpecies) {
        this.evolvesFromSpecies = evolvesFromSpecies;
    }
}
