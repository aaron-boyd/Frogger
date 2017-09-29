import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Screen extends JPanel implements KeyListener, MouseListener {

	public static boolean running = false;
	public static boolean pause = false;
	public static Frog frog;
	public static Level level1;
	public static BufferedImage image;
	public Keyboard key = new Keyboard();
	public javax.swing.Timer timer;
	public long now;
	public long starttime;
	public long lasttime;
	private Button resume = new Button(1000, 550, 400, 100, new Color(103, 219, 106));
	private Button topten = new Button(1000, 700, 400, 100, new Color(255, 226, 81));
	private Button exit = new Button(1000, 850, 400, 100, new Color(237, 100, 100));
	private Button topten2 = new Button(800, 800, 400, 100, new Color(255, 226, 81));
	private Button quit = new Button(1250, 800, 400, 100, new Color(237, 100, 100));
	public int gamestarted = 0;

	public Screen() {
		try {
			image = ImageIO.read(new File("Level1Map.png"));
		} catch (IOException e) {

			e.printStackTrace();
		}
		now = 0;
		starttime = 0;
		lasttime = 0;
		timer = new javax.swing.Timer(6, new MoveListener());
		starttime = System.currentTimeMillis();
		frog = new Frog(1200,1300,"frog1.png");
		level1 = new Level(4,3, 400,350);
		running = false;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g.drawImage(image, 0, 0, null);

		for (int i = 0; i < level1.getCarCount(); i++) {
			g.drawImage(level1.getCar(i).getSprite().getSprite(), level1.getCar(i).getX(), level1.getCar(i).getY(), null);
		}
		for (int i = 0; i < level1.getLogCount(); i++) {
			g.drawImage(level1.getLog(i).getSprite().getSprite(), level1.getLog(i).getX(), level1.getLog(i).getY(), null);
		}
		g.drawImage(frog.getSprite().getSprite(), frog.getX(), frog.getY(), null);

		for (int i = 0; i < 5; i++) {
			if (level1.getLily(i).getUsed()) g.drawImage(frog.getSprite().getSprite(), level1.getLily(i).getX(), level1.getLily(i).getY(), null);
		}

		level1.getTimer().drawTimer(g);
		frog.drawScoreAndLives(g2);

		drawPauseMenu(g2);

		if (level1.getTimer().getTimeLeft() < 100) {
			if (now - lasttime >= 500) {
				g2.setColor(new Color(237, 100, 100, 75));
				g2.fillRect(0, 0, getWidth(), getHeight());
			}
		}

		g2.setColor(new Color(255, 226, 81));
		g2.setFont(new Font("Consolas", Font.PLAIN, 400));
		if (now - starttime <= 2000 && now - starttime >= 1000) {
			g2.drawString("3", 1080, 800);
		} else if (now - starttime > 2000 && now - starttime <= 3000) {
			g2.drawString("2", 1080, 800);
		} else if (now - starttime > 3000 && now - starttime <= 4000) {
			g2.drawString("1", 1080, 800);
		} else if (now - starttime > 4000 && now - starttime < 5000) {
			g2.drawString("GO!", 900, 800);
			running = true;
		}

		if (frog.getLives() == 0 || level1.getTimer().getTimeLeft() < 0) {
			g2.setFont(new Font("Consolas", Font.PLAIN, 200));
			g2.setColor(new Color(255, 100, 100, 75));
			g2.fillRect(0, 0, getWidth(), getHeight());
			g2.setColor(Color.RED);
			g2.drawString("GAME OVER!", getWidth() / 2 - 500, getHeight() / 2 + 50);
			topten2.drawButton(g2);
			quit.drawButton(g2);
			g2.setFont(new Font("Consolas", Font.PLAIN, 70));
			g2.setColor(Color.BLACK);
			g2.drawString("Top Ten", topten2.getX() + 30, topten2.getY() + 75);
			g2.drawString("Exit", quit.getX() + 115, quit.getY() + 75);
			timer.stop();
			running = false;
		}
		
		if(level1.getWin()){
			g2.setFont(new Font("Consolas", Font.PLAIN, 200));
			g2.setColor(new Color(103, 219, 106,75));
			g2.fillRect(0, 0, getWidth(), getHeight());
			g2.setColor(Color.GREEN);
			g2.drawString("GAME OVER! YOU WIN!", getWidth() / 2 - 1000, getHeight() / 2 + 50);
			topten2.drawButton(g2);
			quit.drawButton(g2);
			g2.setFont(new Font("Consolas", Font.PLAIN, 70));
			g2.setColor(Color.BLACK);
			g2.drawString("Top Ten", topten2.getX() + 30, topten2.getY() + 75);
			g2.drawString("Exit", quit.getX() + 115, quit.getY() + 75);
			timer.stop();
			running = false;
		}

	}

	private class MoveListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			now = System.currentTimeMillis();

			if (frog.getOnLog().getOn()) {
				frog.setX(frog.getX() + (2 * frog.getOnLog().getDir()));
			}

			for (int i = 0; i < 9; i++) {
				level1.carCount[i] += 2 * level1.getCarSpeed();
				level1.logCount[i] += 2 * level1.getLogSpeed();
			}

			level1.checkCarCollision();
			level1.checkLogCollision();
			checkTime();

			repaint();

		}
	}

	public void checkTime() {
		if ((now - level1.getCarTime()) >= level1.getCarGenSpeed()) {
			level1.genCar();
			level1.setCarTime(System.currentTimeMillis());
		}
		if ((now - level1.getLogTime()) >= level1.getLogGenSpeed()) {
			level1.genLog();
			level1.setLogTime(System.currentTimeMillis());
		}
		if (now - starttime > 4000) {
			if (now - lasttime >= 1000) {
				level1.getTimer().tick();
				lasttime = now;
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (running == true) {
			if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
				if (frog.getY() - 100 >= 0) {
					frog.setY(frog.getY() - 100);
					frog.setScore(frog.getScore() + 100);
				}
			} else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
				if (frog.getY() + 100 <= 1300) {
					frog.setY(frog.getY() + 100);
					frog.setScore(frog.getScore() + 100);
				}
			} else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
				if (frog.getX() - 100 >= 0) frog.setX(frog.getX() - 100);
			} else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
				if (frog.getX() + 100 <= 2300) frog.setX(frog.getX() + 100);
			}

			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				timer.stop();
				running = false;
				pause = true;
				repaint();
			}
		}

	}

	public void drawPauseMenu(Graphics2D g2) {
		if (!timer.isRunning() && pause == true) {
			g2.setColor(new Color(128, 128, 128, 75));
			g2.fillRect(0, 0, getWidth(), getHeight());
			resume.drawButton(g2);
			topten.drawButton(g2);
			exit.drawButton(g2);
			g2.setFont(new Font("Calibri", Font.PLAIN, 70));
			g2.setColor(Color.BLACK);
			g2.drawString("Resume", resume.getX() + 80, resume.getY() + 70);
			g2.drawString("Top Ten", topten.getX() + 78, topten.getY() + 70);
			g2.drawString("Exit", exit.getX() + 150, exit.getY() + 70);
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (!running && frog.getLives() > 0) {
			if (resume.checkClicked(e)) {
				running = true;
				pause = false;
				timer.start();
				
			} else if (exit.checkClicked(e)) {
				try {
					FroggerTester.topten.topten.saveFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.exit(0);
			} else if (topten.checkClicked(e)) {
				FroggerTester.frame.getContentPane().remove(FroggerTester.screen);
				FroggerTester.frame.getContentPane().add(FroggerTester.topten);
				FroggerTester.frame.addMouseListener(FroggerTester.topten);
				FroggerTester.frame.setSize(new Dimension(1200, 1000));
				FroggerTester.frame.setLocationRelativeTo(null);
				FroggerTester.frame.revalidate();
			}
		} else if (!running && frog.getLives() == 0) {
		if (quit.checkClicked(e)) {
			try {
				FroggerTester.topten.topten.saveFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				System.exit(0);
				System.out.println("Quit");
			}else if(topten2.checkClicked(e)){
				FroggerTester.frame.getContentPane().remove(FroggerTester.screen);
				FroggerTester.frame.getContentPane().add(FroggerTester.topten);
				FroggerTester.frame.addMouseListener(FroggerTester.topten);
				FroggerTester.frame.setSize(new Dimension(1200, 1000));
				FroggerTester.frame.setLocationRelativeTo(null);
				FroggerTester.frame.revalidate();
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
