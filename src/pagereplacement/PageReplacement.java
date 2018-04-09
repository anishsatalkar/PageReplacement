package pagereplacement;

import java.util.ArrayList;
import java.util.List;
//@author Anish

public class PageReplacement {

    static List<Slot> slots;
    static String[] pageSequence;
    static int pageHits = 0;
    static int pageFaults = 0;

    public static void initialise() {
        slots = new ArrayList<>();
        for (int i = 0; i <= 3; i++) {
            slots.add(new Slot());
        }
        pageSequence = "c a d b e b a b c d".split(" ");
    }

    public static void LRU() {
        int i = 0;
        for (i = 0; i < pageSequence.length; i++) {
            boolean replaced = false;
            for (Slot slot : slots) {
                if (slot.page.equals("")) {
                    slot.page = pageSequence[i];
                    pageFaults++;
                    replaced = true;
                    break;
                } else if (slot.page.equals(pageSequence[i])) {
                    pageHits++;
                    replaced = true;
                    break;
                } else {
                    replaced = false;
                }
            }
            if (!replaced) {
                int max = -10;
                pageFaults++;
                Slot temporary = new Slot();
                for (Slot slot : slots) {
                    for (int j = i - 1; j >= 0; j--) {
                        if (slot.page.equals(pageSequence[j])) {
                            slot.lastUsedTimeInterval = i - j;
                            if (slot.lastUsedTimeInterval >= max) {
                                max = slot.lastUsedTimeInterval;
                                temporary = slot;
                            }
                            break;
                        }
                    }
                }
                for (Slot slot : slots) {
                    if (temporary.equals(slot)) {
                        slot.page = pageSequence[i];
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        initialise();
        LRU();
        System.out.println("Hits : " + pageHits + " Faults : " + pageFaults);
    }
}
