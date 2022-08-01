package de.flori.gamestates;

public class GamestateManager {
    private static GamestateManager instance;
    private Gamestate gamestate;

    public static GamestateManager getInstance() {
        if(instance == null){
            instance = new GamestateManager();
        }
        return instance;
    }

    public Gamestate getGamestate() {
        return gamestate;
    }

    public void setGamestate(Gamestate gamestate) {
        if (this.gamestate != null){
            this.gamestate.stop();
        }
        this.gamestate = gamestate;
        this.gamestate.start();
    }
}
