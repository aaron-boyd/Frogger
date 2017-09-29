import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JPanel;

public class TopTenScreen extends JPanel implements MouseListener {

	public static topten topten = new topten();
	private static Button back = new Button(25, 25, 150, 75, new Color(103, 219, 106));

	public TopTenScreen() {
		try {
			topten.openFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, getWidth(), getHeight());
		g2.setColor(new Color(103, 219, 106));
		g2.setFont(new Font("Consolas", Font.PLAIN, 150));
		g2.drawString("Top Ten", 300, 200);
		back.drawButton(g2);
		topten.sortTop();
		for (int i = 0; i < 10; i++) {
			g2.setColor(new Color(103, 219, 106));
			if (i < 5) {
				g2.setColor(new Color(255, 255, 0));
				g2.fillRect(getWidth() / 2 - 423, (i * 100) + 303, 403, 78);
				g2.setColor(new Color(103, 219, 106));
				g2.fillRect((getWidth() / 2 - 425), (i * 100) + 300, 400, 75);
				g2.setColor(Color.BLACK);
				g2.setFont(new Font("Consolas", Font.PLAIN, 48));
				g2.drawString(topten.toString(i), getWidth() / 2 - 400, (i * 100) + 350);
			} else {
				g2.setColor(new Color(255, 255, 0));
				g2.fillRect((getWidth() / 2 + 28), ((i - 5) * 100) + 303, 403, 78);
				g2.setColor(new Color(103, 219, 106));
				g2.fillRect((getWidth() / 2 + 25), ((i - 5) * 100) + 300, 400, 75);
				g2.setColor(Color.BLACK);
				g2.setFont(new Font("Consolas", Font.PLAIN, 48));
				g2.drawString(topten.toString(i), getWidth() / 2 + 50, ((i - 5) * 100) + 350);
			}
		}
		g.setFont(new Font("Consolas", Font.PLAIN, 48));
		g.setColor(Color.BLACK);
		g.drawString("Back", back.getX() + 20, back.getY() + 50);
	}

	public void mouseClicked(MouseEvent e) {
		if (back.checkClicked(e)) {
			FroggerTester.frame.setSize(new Dimension(2400, 1463));
			FroggerTester.frame.setLocationRelativeTo(null);
			FroggerTester.frame.getContentPane().remove(FroggerTester.topten);

			if (FroggerTester.screen.gamestarted !=0) {
				FroggerTester.frame.getContentPane().add(FroggerTester.screen);
			} else {
				FroggerTester.frame.getContentPane().add(FroggerTester.splash);
			}
			FroggerTester.frame.revalidate();
		}

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
