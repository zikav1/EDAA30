package fractal;

import koch.Koch;
import mountain.Mountain;
import mountain.Point;

public class FractalApplication {
	public static void main(String[] args) {
		Fractal[] fractals = new Fractal[2];
		fractals[1] = new Koch(300);

		Point a = new Point(70, 450);
		Point b = new Point(260, 90);
		Point c = new Point(550, 500);

		double devValue = 5.5;

		fractals[0] = new Mountain(a, b, c, devValue);

		new FractalView(fractals, "Fraktaler", 600, 600);
	}

}
