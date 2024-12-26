package me.neovitalism.customspecs;

import com.cobblemon.mod.common.api.properties.CustomPokemonProperty;
import me.neovitalism.customspecs.manager.SpecManager;
import me.neovitalism.customspecs.property.CustomSpecProperty;
import me.neovitalism.neoapi.config.Configuration;
import me.neovitalism.neoapi.modloading.NeoMod;
import me.neovitalism.neoapi.modloading.command.CommandRegistryInfo;
import me.neovitalism.neoapi.modloading.command.ReloadCommand;

public class CustomSpecs extends NeoMod {
    @Override
    public String getModID() {
        return "CustomSpecs";
    }

    @Override
    public String getModPrefix() {
        return "&#696969[&#7E50C7C&#8250C3u&#8651C0s&#8A51BCt&#8E51B8o&#9352B5m&#9752B1S&#9B52ADp&#9F52A9e&#A353A6c&#A753A2s&#696969]&f ";
    }

    @Override
    public void onInitialize() {
        super.onInitialize();
        CustomPokemonProperty.Companion.register(new CustomSpecProperty());
        this.getLogger().info("Loaded!");
    }

    @Override
    public void configManager() {
        Configuration config = this.getConfig("custom-specs.yml", true);
        SpecManager.reload(config);
    }

    @Override
    public void registerCommands(CommandRegistryInfo info) {
        new ReloadCommand(this, info.getDispatcher(), "customspecs");
    }
}
