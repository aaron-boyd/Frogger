import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics2D;
import java.io.IOException;

import javax.swing.JOptionPane;

public class Frog extends Entity {

	private int score;
	private String initials;
	private int lives;
	private Sprite heart;
	private boolean me;
	
	public Frog(){
		score = 0;
		initials = "";
		lives = 0;
		me = false;
	}

	public Frog(int x, int y, String path) {
		super(x, y, 0, path);
		this.score = 0;
		this.lives = 3;
		this.initials = "";
		try {
			this.heart = new Sprite("heart.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Frog(String initials, int score){
		this.initials = initials;
		this.score = score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public void setMe(boolean me){
		this.me = me;
	}
	
	public void setInitials(){
		do{
			initials = JOptionPane.showInputDialog("Please enter 3 initials.").toUpperCase();
		}while(initials.length() != 3);
		
	}

	public void setLives(int lives) {
		this.lives -= lives;
	}

	public int getScore() {
		return score;
	}

	public int getLives() {
		return lives;
	}

	public Sprite getHeart() {
		return heart;
	}
	
	public String getInitials(){
		return initials;
	}
	
	public boolean getMe(){
		return me;
	}

	public void drawScoreAndLives(Graphics2D g2) {

		g2.setColor(Color.WHITE);
		g2.setFont(new Font("Calibri", Font.PLAIN, 70));
		if(score <= -1000){
			g2.drawString(Integer.toString(score), 2230, 55);
		}else if(score < 0 && score > -1000){
			g2.drawString(Integer.toString(score), 2260, 55);
		}else if (score >= 0 && score < 100) {
			g2.drawString(Integer.toString(score), 2355, 55);
		} else if (score >= 100 && score < 1000) {
			g2.drawString(Integer.toString(score), 2275, 55);
		} else if (score >= 1000) {
			g2.drawString(Integer.toString(score), 2250, 55);
		}

		for (int i = 0; i < lives; i++) {
			g2.drawImage(heart.getSprite(), 2340 - (i * 55), 60, null);
		}
	}

}