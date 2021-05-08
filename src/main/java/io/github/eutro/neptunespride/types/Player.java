package io.github.eutro.neptunespride.types;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * The data of a player.
 */
public class Player {
    /**
     * Whether this player is under AI administration.
     */
    public Toggle ai;

    /**
     * The alias of the player.
     */
    public String alias;

    /**
     * The index of the avatar of this player.
     */
    public int avatar;

    /**
     * How much cash the player has.
     */
    @Only.Owned
    public Integer cash;

    /**
     * The state of this players concession.
     */
    public Conceded conceded;

    /**
     * In a game with formal alliances, a map of player UIDs to the number of ticks until war.
     */
    @Only.Owned
    public Map<Integer, Integer> countdownToWar;

    /**
     * The UID of the home star of the player.
     */
    @SerializedName("huid")
    public int homeUid;

    /**
     * How much karma, or renown, this player has left to give.
     */
    public int karmaToGive;

    /**
     * In a turn-based game, how many turns this player has missed.
     */
    public int missedTurns;

    /**
     * In a turn-based game, whether this player's turn is ready.
     */
    public Toggle ready;

    /**
     * The regard this player has towards the owner of the API key.
     * <p>
     * This becomes relevant if the player falls under AI administration.
     */
    public int regard;

    /**
     * The technology currently being researched.
     */
    @Only.Owned
    public String researching;

    /**
     * The technology scheduled to be researched next.
     */
    @Only.Owned
    public String researchingNext;

    /**
     * Unknown.
     */
    public Toggle starsAbandoned;

    /**
     * The technology progress of this player.
     */
    public Tech tech;

    /**
     * The total economy this player has.
     */
    public int totalEconomy;

    /**
     * The total industry this player has.
     */
    public int totalIndustry;

    /**
     * The total science this player has.
     */
    public int totalScience;

    /**
     * The total number of carriers this player has.
     */
    public int totalFleets;

    /**
     * The total stars this player has.
     */
    public int totalStars;

    /**
     * The total ships this player has.
     */
    public int totalStrength;

    /**
     * The UID of this player.
     */
    public int uid;

    /**
     * A map of player UIDs to the war status the API key owner has with that player.
     * <p>
     * Values unknown, but always 3 in games with no formal alliances.
     */
    @Only.Owned
    public Map<Integer, Integer> war;

    /**
     * An enum of possible values for a player's concession status.
     *
     * @see #conceded
     */
    public enum Conceded {
        /**
         * Not conceded.
         */
        NO,

        /**
         * Conceded.
         */
        CONCEDED,

        /**
         * Kicked for inactivity.
         */
        INACTIVE,

        /**
         * Knocked out.
         */
        KO,
    }

    /**
     * Get the symbol that represents this player.
     *
     * @return The symbol that represents this player.
     */
    public Symbol getSymbol() {
        return Symbol.fromUid(uid);
    }

    /**
     * A class that represents a symbol that represents a player.
     */
    public static class Symbol {
        /**
         * The colour of the symbol.
         */
        public final Colour colour;
        /**
         * The shape of the symbol.
         */
        public final Shape shape;

        /**
         * Construct a symbol with the given colour and shape.
         *
         * @param colour The colour of the symbol.
         * @param shape  The shape of the symbol.
         */
        public Symbol(Colour colour, Shape shape) {
            this.colour = colour;
            this.shape = shape;
        }

        /**
         * Get the symbol a given player has.
         *
         * @param uid The UID of the player.
         * @return The symbol they have.
         */
        public static Symbol fromUid(int uid) {
            return new Symbol(Symbol.Colour.values()[uid % 8], Symbol.Shape.values()[uid / 8]);
        }

        /**
         * Get the UID that a player with this symbol would have.
         *
         * @return The UID of the player.
         */
        public int toUid() {
            return shape.ordinal() * 8 + colour.ordinal();
        }

        /**
         * An enum of possible colour values a symbol may have.
         */
        public enum Colour {
            /**
             * Blue
             */
            BLUE(0x0433ff),

            /**
             * Cyan
             */
            CYAN(0x00a0df),

            /**
             * Green
             */
            GREEN(0x37bb00),

            /**
             * Yellow
             */
            YELLOW(0xffbe0e),

            /**
             * Orange
             */
            ORANGE(0xe16200),

            /**
             * Red
             */
            RED(0xc11a00),

            /**
             * Magenta
             */
            MAGENTA(0xc12fc0),

            /**
             * Purple
             */
            PURPLE(0x6127c4),
            ;

            /**
             * The RGB value of the colour.
             */
            public final int value;

            Colour(int value) {
                this.value = value;
            }
        }

        /**
         * An enum of possible colour shapes a symbol may have.
         */
        public enum Shape {
            /**
             * Circle
             */
            CIRCLE,

            /**
             * Square
             */
            SQUARE,

            /**
             * Hexagon
             */
            HEXAGON,

            /**
             * Triangle
             */
            TRIANGLE,

            /**
             * Cross
             */
            CROSS,

            /**
             * Diamond
             */
            DIAMOND,

            /**
             * Star
             */
            STAR,

            /**
             * Oval
             */
            OVAL,
        }
    }
}
