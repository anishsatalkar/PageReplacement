package pagereplacement;
//@author Anish

public class Slot {

    String page;
    int lastUsedTimeInterval;
    boolean isLeastRecentlyUsed;

    Slot() {
        this.page = "";
        this.lastUsedTimeInterval = 0;
        this.isLeastRecentlyUsed = false;
    }
}
