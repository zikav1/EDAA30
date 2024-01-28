package textproc;

import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class GeneralWordCounter implements TextProcessor {

    private Map<String, Integer> wordCounter;
    private Set<String> stopWords;

    public GeneralWordCounter(Set<String> set) {
        stopWords = new HashSet<>(set);
        wordCounter = new HashMap<String, Integer>();
    }

    @Override
    public void process(String w) {
        if (!stopWords.contains(w)) {
            if (wordCounter.containsKey(w)) {
                wordCounter.put(w, wordCounter.get(w) + 1);
            } else
                wordCounter.put(w, 1);
        }
    }

    @Override
    public void report() {
        Set<Map.Entry<String, Integer>> wordSet = wordCounter.entrySet();
        List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordSet);

        // Sorterar listan på antal förekomster i andrahand i bokstavsordning
        wordList.sort((w1, w2) -> {
            int result = w2.getValue().compareTo(w1.getValue());
            if (result == 0) {
                return w2.getKey().compareTo(w1.getKey());
            }
            return result;
        });

        // Printar ut alla förekomster i GeneralWordCounter
        System.out.println("** GeneralWordCounter **");
        for (int i = 0; i < 20; i++) {
            System.out.println(wordList.get(i));
        }

    }

    public List<Map.Entry<String, Integer>> getWordList() {
        Set<Map.Entry<String, Integer>> wordSet = wordCounter.entrySet();
        List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordSet);
        return wordList;
    }

}
