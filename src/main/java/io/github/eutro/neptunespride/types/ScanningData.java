package io.github.eutro.neptunespride.types;

import java.util.Map;

/**
 * The full scanning data returned by the API.
 */
public final class ScanningData {
    /**
     * A map of fleet UIDs to the fleets they name.
     */
    public Map<Integer, Fleet> fleets;

    /**
     * A map of star UIDs to the stars they name.
     */
    public Map<Integer, Star> stars;

    /**
     * A map of player UIDs to the players they name.
     */
    public Map<Integer, Player> players;

    /**
     * Unknown.
     */
    public float fleetSpeed;

    /**
     * Whether the game is paused.
     */
    public boolean paused;

    /**
     * How many productions, or cycles, have passed.
     */
    public int productions;

    /**
     * The fraction of the current tick that has been completed.
     * <p>
     * This may be above 1.
     */
    public float tickFragment;

    /**
     * The duration of a tick, in minutes.
     */
    public int tickRate;

    /**
     * The timestamp at which the request was made, in milliseconds.
     */
    public long now;

    /**
     * How often production occurs, in ticks.
     */
    public int productionRate;

    /**
     * How many stars a player needs to get to win.
     */
    public int starsForVictory;

    /**
     * Whether the game is over.
     */
    public Toggle gameOver;

    /**
     * Whether the game has started.
     */
    public boolean started;

    /**
     * The timestamp at which the game was started, in milliseconds.
     */
    public long startTime;

    /**
     * The number of stars in the galaxy.
     */
    public int totalStars;

    /**
     * How many ticks have occured since last production.
     */
    public int productionCounter;

    /**
     * Whether trade requires being in scanning range.
     */
    public Toggle tradeScanned;

    /**
     * The total number of ticks that have occured.
     */
    public int tick;

    /**
     * How much it costs to trade one level of technology.
     */
    public int tradeCost;

    /**
     * The name of the game.
     */
    public String name;

    /**
     * The UID of the player that owns the API key.
     */
    public int playerUid;

    /**
     * 1 if the owner of the API key is admin, 0 if they are not, and -1 if the game is public.
     */
    public int admin;

    /**
     * Whether formal alliances are enabled.
     */
    public Toggle war;

    /**
     * Unknown.
     */
    public int turnBasedTimeOut;
}
