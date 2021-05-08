package io.github.eutro.neptunespride.types.response;

import java.util.Map;

public final class ScanningData {
    public Map<Integer, Fleet> fleets;
    public float fleetSpeed;
    public boolean paused;
    public int productions;
    public float tickFragment;
    public int tickRate;
    public long now;
    public int productionRate;
    public Map<Integer, Star> stars;
    public int starsForVictory;
    public Toggle gameOver;
    public boolean started;
    public long startTime;
    public int totalStars;
    public int productionCounter;
    public int tradeScanned;
    public int tick;
    public int tradeCost;
    public String name;
    public int playerUid;
    public int admin;
    public int war;
    public Map<Integer, Player> players;
    public int turnBasedTimeOut;
}
