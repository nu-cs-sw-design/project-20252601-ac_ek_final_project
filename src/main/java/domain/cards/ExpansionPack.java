package domain.cards;

import domain.game.GameContext;
import domain.game.GameEngine;

public enum ExpansionPack {
    PARTY_PACK("Party Pack"),
    STREAKING_KITTENS("Streaking Kittens"),
    IMPLODING_KITTENS("Imploding Kittens");

    private final String displayName;

    ExpansionPack(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}