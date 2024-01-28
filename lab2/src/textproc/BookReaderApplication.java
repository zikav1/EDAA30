package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BookReaderApplication {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File(
                "C:\\Users\\hugo_\\OneDrive\\Skrivbord\\Inför omtentamen i Java fortsättning\\edaa30ht22-workspace\\lab2\\nilsholg.txt"),
                "UTF8");

        Scanner scan = new Scanner(new File(
                "C:\\Users\\hugo_\\OneDrive\\Skrivbord\\Inför omtentamen i Java fortsättning\\edaa30ht22-workspace\\lab2\\undantagsord.txt"),
                "UTF-8");

        Set<String> stopWords = new HashSet<>();

        while (scan.hasNext()) {
            String notValidWords = scan.next().toLowerCase();
            stopWords.add(notValidWords);
        }

        // GeneralWordCounter objekt
        GeneralWordCounter gwc = new GeneralWordCounter(stopWords);

        s.findWithinHorizon("\uFEFF", 1);
        s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning, används för att ta bort orelevanta tecken

        while (s.hasNext()) {
            String word = s.next().toLowerCase();
            gwc.process(word);
        }

        BookReaderController brc = new BookReaderController(gwc);
        s.close();
    }

}
