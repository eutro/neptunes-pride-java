package io.github.eutro.neptunespride.types;

import com.google.gson.annotations.SerializedName;

/**
 * A star in the galaxy.
 */
public class Star {
    /**
     * How many ships have been partially built on this star.
     */
    @SerializedName("c")
    @Only.Owned
    public Float partialShips;

    /**
     * The economy at this star.
     */
    @SerializedName("e")
    @Only.Visible
    public Integer economy;

    /**
     * The industry at this star.
     */
    @SerializedName("i")
    @Only.Visible
    public Integer industry;

    /**
     * The science at this star.
     */
    @SerializedName("s")
    @Only.Visible
    public Integer science;

    /**
     * Whether a warp gate exists at this star.
     */
    @SerializedName("ga")
    @Only.Visible
    public Toggle warpGate;

    /**
     * The name of this star.
     */
    @SerializedName("n")
    public String name;

    /**
     * The UID of the owner of this star, or null if it is unowned.
     */
    @SerializedName("puid")
    public Integer ownerUid;

    /**
     * The natural resources at this star.
     */
    @SerializedName("nr")
    @Only.Visible
    public Integer naturalResources;

    /**
     * The resources at this star, after terraforming.
     */
    @SerializedName("r")
    @Only.Visible
    public Integer terraformedResources;

    /**
     * The number of ships at this star.
     */
    @SerializedName("st")
    @Only.Visible
    public Integer shipCount;

    /**
     * The UID of this star.
     */
    public int uid;

    /**
     * Whether the star is visible.
     */
    @SerializedName("v")
    public Toggle visible;

    /**
     * The X position of this star.
     *
     * @see io.github.eutro.neptunespride.types
     */
    public float x;

    /**
     * The X position of this star.
     *
     * @see io.github.eutro.neptunespride.types
     */
    public float y;
}
