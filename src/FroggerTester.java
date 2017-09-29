import java.awt.Dimension;

import javax.swing.JFrame;

public class FroggerTester {
	public static JFrame frame = new JFrame("Frogger");
	public static Screen screen = new Screen();
	public static SplashScreen splash = new SplashScreen();
	public static TopTenScreen topten = new TopTenScreen();

	public static void main(String[] args) {
		frame.setPreferredSize(new Dimension(2400, 1463));
		frame.setResizable(false);
		frame.add(splash);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.addMouseListener(splash);
	}

}
