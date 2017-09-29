import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SplashScreen extends JPanel implements MouseListener {
	
	private static BufferedImage image;
	private static Button start = new Button(1950,950,400,100,new Color(0,218,49));
	private static Button topten = new Button(1950,1075,400,100, new Color(255,255,0));
	private static Button exit = new Button(1950,1200,400,100,new Color(237, 100, 100));

	public SplashScreen(){
		try {
			System.out.println("Splash");
			image = ImageIO.read(new File("Splash.png"));
		} catch (IOException e) {

			e.printStackTrace();
		}
		repaint();
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g.drawImage(image,0,0,null);
		start.drawButton(g2);
		topten.drawButton(g2);
		exit.drawButton(g2);
		g2.setFont(new Font("Calibri", Font.PLAIN, 70));
		g2.setColor(Color.BLACK);
		g2.drawString("Start", start.getX() + 130, start.getY() + 70);
		g2.drawString("Top Ten", topten.getX() + 82, topten.getY() + 70);
		g2.drawString("Exit", exit.getX() + 150, exit.getY() + 70);
	}
	
	
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		//System.out.println(e.getX() + " " + e.getY() );
		//System.out.println(start.toString());
		if(start.checkClicked(e)){
			FroggerTester.screen = new Screen();
			FroggerTester.screen.frog = new Frog(1200,1300,"frog1.png");
			FroggerTester.frame.addKeyListener(FroggerTester.screen);
			FroggerTester.frame.addMouseListener(FroggerTester.screen);
			FroggerTester.frame.getContentPane().remove(FroggerTester.splash);
			FroggerTester.frame.getContentPane().add(FroggerTester.screen);
			FroggerTester.frame.revalidate();
			FroggerTester.screen.frog.setInitials();
			FroggerTester.screen.starttime = System.currentTimeMillis();
			FroggerTester.screen.timer.start();
			FroggerTester.topten.topten.tt[10] = FroggerTester.screen.frog;
			FroggerTester.topten.topten.tt[10].setMe(true);
			FroggerTester.screen.gamestarted =1;
			
		}else if(exit.checkClicked(e)){
			System.exit(0);
		}else if(topten.checkClicked(e)){
			FroggerTester.frame.getContentPane().remove(FroggerTester.splash);
			FroggerTester.frame.getContentPane().add(FroggerTester.topten);
			FroggerTester.frame.addMouseListener(FroggerTester.topten);
			FroggerTester.frame.setSize(new Dimension(1200,1000));
			FroggerTester.frame.setLocationRelativeTo(null);
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

