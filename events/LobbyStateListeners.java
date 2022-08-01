package de.flori.events;

import de.flori.Game;
import de.flori.gamestates.GamestateManager;
import de.flori.gamestates.LobbyState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class LobbyStateListeners implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        LobbyState lobbyState = (LobbyState) GamestateManager.getInstance().getGamestate();
        lobbyState.recalculateTime();

    }

    @EventHandler
    public void onLogin(PlayerLoginEvent e){

    }

    @EventHandler
    public void onLeft(PlayerQuitEvent e){
        new BukkitRunnable() {
            @Override
            public void run() {
                LobbyState lobbyState = (LobbyState) GamestateManager.getInstance().getGamestate();
                lobbyState.recalculateTime();
            }
        }.runTaskLater(Game.getPlugin(), 1);

    }


}
