package io.github.eutro.neptunespride.types.response;

import com.google.gson.annotations.SerializedName;

public class Player {
    public Toggle ai;
    public String alias;
    public int avatar;
    @Only.Owned
    public Integer cash;
    public Conceded conceded;
    @Only.Owned
    public Object countdownToWar;
    @SerializedName("huid")
    public int homeUid;
    public int karmaToGive;
    public int missedTurns;
    public Toggle ready;
    public int regard;
    @Only.Owned
    public String researching;
    @Only.Owned
    public String researchingNext;
    public Toggle starsAbandoned;
    public Tech tech;
    public int totalEconomy;
    public int totalFleets;
    public int totalIndustry;
    public int totalScience;
    public int totalStars;
    public int totalStrength;
    public int uid;
    @Only.Owned
    public Object war;

    public enum Conceded {
        NO, CONCEDED, INACTIVE, ANNIHILATED
    }

    public Symbol getSymbol() {
        return new Symbol(Symbol.Colour.values()[uid % 8], Symbol.Shape.values()[uid / 8]);
    }

    public static class Symbol {
        public final Colour colour;
        public final Shape shape;

        public Symbol(Colour colour, Shape shape) {
            this.colour = colour;
            this.shape = shape;
        }

        public enum Colour {
            BLUE(0x0433ff),
            CYAN(0x00a0df),
            GREEN(0x37bb00),
            YELLOW(0xffbe0e),
            ORANGE(0xe16200),
            RED(0xc11a00),
            MAGENTA(0xc12fc0),
            PURPLE(0x6127c4),
            ;

            public final int value;

            Colour(int value) {
                this.value = value;
            }
        }

        public enum Shape {
            CIRCLE,
            SQUARE,
            HEXAGON,
            TRIANGLE,
            CROSS,
            DIAMOND,
            STAR,
            OVAL,
        }
    }
}
