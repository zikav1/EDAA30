package textproc;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MultiWordCounter implements TextProcessor {

    private Map<String, Integer> wordCounter;

    public MultiWordCounter(String[] str) {
        wordCounter = new HashMap<>();

        for (String s : str) {
            wordCounter.put(s.toLowerCase(), 0);
        }

    }

    @Override
    public void process(String w) {
        if (wordCounter.containsKey(w)) {
            wordCounter.put(w, wordCounter.get(w) + 1);
        }
    }

    @Override
    public void report() {
        System.out.println("** MultiWordCounter **");
        for (String key : wordCounter.keySet()) {
            System.out.println(key + ": " + wordCounter.get(key));
        }
        System.out.println(" ");
    }
}
