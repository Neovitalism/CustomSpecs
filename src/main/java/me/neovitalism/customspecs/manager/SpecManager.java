package me.neovitalism.customspecs.manager;

import com.cobblemon.mod.common.api.pokemon.PokemonProperties;
import me.neovitalism.neoapi.config.Configuration;

import java.util.*;

public class SpecManager {
    private static final Map<String, List<PokemonProperties>> CUSTOM_SPECS = new HashMap<>();

    public static void reload(Configuration config) {
        SpecManager.CUSTOM_SPECS.clear();
        for (String key : config.getKeys()) {
            List<String> specStrings = config.getStringList(key);
            List<PokemonProperties> specs = new ArrayList<>();
            specStrings.forEach(s -> specs.add(PokemonProperties.Companion.parse(s, " ", "=")));
            SpecManager.CUSTOM_SPECS.put(key, specs);
        }
    }

    public static Set<String> getPropertyKeys() {
        return SpecManager.CUSTOM_SPECS.keySet();
    }

    public static List<PokemonProperties> getProperties(String key) {
        return SpecManager.CUSTOM_SPECS.get(key);
    }
}
