package me.neovitalism.customspecs.property;

import com.cobblemon.mod.common.api.pokemon.PokemonProperties;
import com.cobblemon.mod.common.api.properties.CustomPokemonPropertyType;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.cobblemon.mod.common.pokemon.properties.StringProperty;
import kotlin.Unit;
import me.neovitalism.customspecs.manager.SpecManager;
import me.neovitalism.neoapi.helpers.RandomHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class CustomSpecProperty implements CustomPokemonPropertyType<StringProperty> {
    public static final String KEY = "custom-spec";

    @Override
    public @NotNull Iterable<String> getKeys() {
        return Set.of(CustomSpecProperty.KEY);
    }

    @Override
    public boolean getNeedsKey() {
        return true;
    }

    @Override
    public @Nullable StringProperty fromString(@Nullable String value) {
        if (value == null) return null;
        return new StringProperty(CustomSpecProperty.KEY, value, this::apply, this::matches);
    }

    public Unit apply(Pokemon pokemon, String value) {
        List<PokemonProperties> properties = SpecManager.getProperties(value);
        if (properties == null) return Unit.INSTANCE;
        PokemonProperties randomProperty = RandomHelper.getRandomValue(properties);
        randomProperty.apply(pokemon);
        return Unit.INSTANCE;
    }

    public boolean matches(Pokemon pokemon, String value) {
        List<PokemonProperties> properties = SpecManager.getProperties(value);
        for (PokemonProperties property : properties) {
            if (property.matches(pokemon)) return true;
        }
        return false;
    }

    @Override
    public @NotNull Collection<String> examples() {
        return SpecManager.getPropertyKeys();
    }
}
