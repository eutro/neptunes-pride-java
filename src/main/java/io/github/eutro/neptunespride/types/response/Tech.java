package io.github.eutro.neptunespride.types.response;

import com.google.gson.annotations.SerializedName;

public class Tech {
    public Technology banking;
    public Technology manufacturing;
    public Technology propulsion;
    public Technology research;
    public Technology scanning;
    public Technology terraforming;
    public Technology weapons;

    public static class Technology {
        @SerializedName("brr") @Only.Owned public Integer researchCostPerLevel;
        @SerializedName("bv") @Only.Owned public Integer unknownBv;
        public int level;
        @Only.Owned public Integer research;
        @SerializedName("sv") @Only.Owned public Integer unknownSv;
        @SerializedName("value") public float unknownValue;
    }
}
