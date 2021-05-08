package io.github.eutro.neptunespride.types;

import com.google.gson.annotations.SerializedName;

/**
 * The data of a fleet.
 */
public class Fleet {
    /**
     * Unknown
     */
    @SerializedName("l")
    public int l;

    /**
     * The X position of this fleet last tick.
     *
     * @see io.github.eutro.neptunespride.types
     */
    @SerializedName("lx")
    public float lastX;

    /**
     * The Y position of this fleet last tick.
     *
     * @see io.github.eutro.neptunespride.types
     */
    @SerializedName("ly")
    public float lastY;

    /**
     * The name of this fleet.
     */
    @SerializedName("n")
    public String name;

    /**
     * The known orders of this fleet.
     */
    @SerializedName("o")
    public Order[] orders;

    /**
     * The UID of the star this fleet is at, if it is at a star.
     */
    @SerializedName("ouid")
    public Integer starUid;

    /**
     * The UID of the player that owns this fleet.
     */
    @SerializedName("puid")
    public int ownerUid;

    /**
     * The number of ships in this fleet.
     */
    @SerializedName("st")
    public int shipCount;

    /**
     * The UID of this fleet.
     */
    public int uid;

    /**
     * Whether this fleet is warping.
     */
    @SerializedName("w")
    public Toggle warping;

    /**
     * The X position of this fleet.
     *
     * @see io.github.eutro.neptunespride.types
     */
    @SerializedName("x")
    public float curX;

    /**
     * The Y position of this fleet.
     *
     * @see io.github.eutro.neptunespride.types
     */
    @SerializedName("y")
    public float curY;

    /**
     * An order a fleet may have.
     */
    public static class Order {
        /**
         * The delay on this order, in ticks.
         */
        public int delay;

        /**
         * The UID of the target star.
         */
        public int star;

        /**
         * The type of the order.
         */
        public Type type;

        /**
         * The value the order type takes.
         */
        public int count;

        /**
         * An enum for possible order types.
         */
        public enum Type {
            /**
             * The fleet should do nothing at the star.
             */
            DO_NOTHING,

            /**
             * The fleet should collect all ships at the star.
             */
            COLLECT_ALL,

            /**
             * The fleet should drop all ships at the star.
             */
            DROP_ALL,

            /**
             * The fleet should collect the given number of ships at the star.
             */
            COLLECT,

            /**
             * The fleet should drop the given number of ships at the star.
             */
            DROP,

            /**
             * The fleet should drop all but the given number of ships at the star.
             */
            DROP_ALL_BUT,

            /**
             * The fleet should garrison a number of ships at the star.
             */
            GARRISON_STAR,
        }
    }
}
