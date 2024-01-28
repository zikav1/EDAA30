package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Holgersson {

	public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland" };

	public static void main(String[] args) throws FileNotFoundException {

		long t0 = System.nanoTime();

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

		// TextProcessor objekt
		TextProcessor p1 = new SingleWordCounter("nils");
		TextProcessor p2 = new SingleWordCounter("norge");
		TextProcessor mwc = new MultiWordCounter(REGIONS);
		TextProcessor gwc = new GeneralWordCounter(stopWords);

		// Lista för dessa TextProcessor objekt
		ArrayList<TextProcessor> words = new ArrayList<>();

		// Lägger till dessa i listan
		words.add(p1);
		words.add(p2);
		words.add(mwc);
		words.add(gwc);

		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning, används för att ta bort orelevanta tecken

		while (s.hasNext()) {
			String word = s.next().toLowerCase();

			for (TextProcessor p : words) {
				p.process(word);
			}

		}

		s.close();

		for (TextProcessor p : words) {
			p.report();
		}

		long t1 = System.nanoTime();
		System.out.println("tid: " + (t1 - t0) / 1000000.0 + " ms");
	}
}