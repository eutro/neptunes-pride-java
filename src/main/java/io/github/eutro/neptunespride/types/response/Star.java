package io.github.eutro.neptunespride.types.response;

import com.google.gson.annotations.SerializedName;

public class Star {
    @SerializedName("c") @Only.Owned public Float partialShips;
    @SerializedName("e") @Only.Visible public Integer economy;
    @SerializedName("ga") @Only.Visible public Toggle warpGate;
    @SerializedName("i") @Only.Visible public Integer industry;
    @SerializedName("n") public String name;
    @SerializedName("nr") @Only.Visible public Integer naturalResources;
    @SerializedName("puid") public int ownerUid;
    @SerializedName("r") @Only.Visible public Integer terraformedResources;
    @SerializedName("s") @Only.Visible public Integer science;
    @SerializedName("st") @Only.Visible public Integer shipCount;
    @SerializedName("uid") public int uid;
    @SerializedName("v") public Toggle visible;
    public float x;
    public float y;
}
