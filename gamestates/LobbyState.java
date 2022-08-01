package de.flori.gamestates;

import de.flori.Game;
import de.flori.events.LobbyStateListeners;
import de.flori.util.Settings;
import de.flori.util.Timer;
import de.flori.util.TimerListener;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;

public class LobbyState implements Gamestate {
    private Timer timer;
    @Override
    public void start() {
        Bukkit.getPluginManager().registerEvents(new LobbyStateListeners() , Game.getPlugin());
        startTimer();
    }

    @Override
    public void stop(){
        HandlerList.unregisterAll(new LobbyStateListeners());
    }

    public void startTimer(){
        timer = new Timer(new TimerListener() {
            @Override
            public void onTick(int time) {

                switch (time){
                    case 60:
                    case 50:
                    case 40:
                    case 30:
                    case 20:
                    case 10:
                    case 5:
                    case 4:
                    case 3:
                    case 2:
                    case 1:
                        Bukkit.broadcastMessage(Game.PREFIX + "Das Spiel startet in §a" + time + " Sekunden!");
                }

            }

            @Override
            public void onPause(int time) {

            }

            @Override
            public void onResume(int time) {

            }

            @Override
            public void onStop() {

            }
        }, 60);
    }

    public Timer getTimer() {
        return timer;
    }

    public void recalculateTime(){
        int players = Bukkit.getOnlinePlayers().size();
        boolean running = timer.isPaused();
        if(!running && players == Settings.MIN_PLAYERS) {
            timer.setTime(60);
            timer.setPaused(false);
            return;
        }

        if(running && players <= Settings.MIN_PLAYERS) {
            timer.setPaused(true);
            Bukkit.broadcastMessage(Game.PREFIX + "Der start wurde wegen spielermangel abgebrochen!");
            return;
        }
        skipTime(timer.getTime() , players);
    }

    public void skipTime(int time, int players){
        if (Settings.SKIP_TIME3 >= players && !(time < 20)){
            timer.setTime(20);
        }else if (Settings.SKIP_TIME2 >= players && !(time < 35)){
            timer.setTime(35);
        }else if (Settings.SKIP_TIME1 >= players && !(time < 50)){
            timer.setTime(50);
        }
    }
}
