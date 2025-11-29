package ui;

import java.util.Set;

public interface InputProvider {
    int promptPlayer(String key);
    Set<Integer> promptExpansionPackNumbers();
}
