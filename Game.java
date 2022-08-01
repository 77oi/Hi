package de.flori;

import de.flori.gamestates.GamestateManager;
import de.flori.gamestates.LobbyState;
import org.bukkit.plugin.java.JavaPlugin;

public final class Game extends JavaPlugin {
    public static String PREFIX = "§6[§cGame§6] §7";
    private static Game plugin;

    @Override
    public void onEnable() {
        plugin = this;
       GamestateManager gamestateManager = GamestateManager.getInstance();
       gamestateManager.setGamestate(new LobbyState());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Game getPlugin() {
        return plugin;
    }
}
