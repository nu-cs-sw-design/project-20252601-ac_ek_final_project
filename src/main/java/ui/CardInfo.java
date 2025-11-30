package ui;

public final class CardInfo {
    private final int index;
    private final String name;
    private final boolean isPlayable;
    
    public CardInfo(int index, String name, boolean isPlayable) {
        this.index = index;
        this.name = name;
        this.isPlayable = isPlayable;
    }
    
    public int index() {
        return index;
    }
    
    public String name() {
        return name;
    }
    
    public boolean isPlayable() {
        return isPlayable;
    }
}