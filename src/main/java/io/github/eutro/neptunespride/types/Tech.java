package io.github.eutro.neptunespride.types;

import com.google.gson.annotations.SerializedName;

/**
 * The technology status of a player.
 */
public class Tech {
    /**
     * The status of a player's Banking technology.
     */
    public Technology banking;

    /**
     * The status of a player's Manufacturing technology.
     */
    public Technology manufacturing;

    /**
     * The status of a player's Hyperspace Range technology.
     */
    public Technology propulsion;

    /**
     * The status of a player's Experimentation technology.
     */
    public Technology research;

    /**
     * The status of a player's Scanning technology.
     */
    public Technology scanning;

    /**
     * The status of a player's Terraforming technology.
     */
    public Technology terraforming;

    /**
     * The status of a player's Weapons technology.
     */
    public Technology weapons;

    /**
     * The status of a single technology of a player.
     */
    public static class Technology {
        /**
         * The level of this technology.
         */
        public int level;

        /**
         * How many points it takes per level to achieve a breakthrough in this technology.
         */
        @SerializedName("brr")
        @Only.Owned
        public Integer researchCostPerLevel;

        /**
         * How many points of research the player has in this technology.
         */
        @Only.Owned
        public Integer research;

        /**
         * Significance unknown.
         *
         * value = level * bv + sv
         */
        @SerializedName("value")
        public float value;

        /**
         * Significance unknown.
         *
         * value = level * bv + sv
         */
        @SerializedName("bv")
        @Only.Owned
        public Integer bv;

        /**
         * Significance unknown.
         *
         * value = level * bv + sv
         */
        @SerializedName("sv")
        @Only.Owned
        public Integer sv;
    }
}
