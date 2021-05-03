package io.github.eutro.neptunespride.types.response;

import com.google.gson.annotations.SerializedName;

public class Fleet {
    @SerializedName("l") public int unknownL;
    @SerializedName("lx") public float lastX;
    @SerializedName("ly") public float lastY;
    @SerializedName("n") public String name;
    @SerializedName("o") public Order[] orders;
    @SerializedName("ouid") @Only.AtStar public Integer starUid;
    @SerializedName("puid") public int ownerUid;
    @SerializedName("st") public int shipCount;
    public int uid;
    @SerializedName("w") public Toggle warping;
    @SerializedName("x") public float curX;
    @SerializedName("y") public float curY;

    public static class Order {
        public int delay;
        public int star;
        public Type type;
        public int count;

        public enum Type {
            DO_NOTHING,
            COLLECT_ALL,
            DROP_ALL,
            COLLECT,
            DROP,
            DROP_ALL_BUT,
            GARRISON_STAR,
        }
    }
}
