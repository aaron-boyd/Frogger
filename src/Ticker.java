import java.awt.Color;
import java.awt.Graphics;

public class Ticker {
	private int timeLeft;
	private int timeUsed;
	private int timeTotal;
	
	public Ticker(){
		this.timeLeft = 0;
		this.timeUsed = 0;
		this.timeTotal = 0;
	}
	
	public Ticker(int total, int used, int left){
		this.timeLeft = left;
		this.timeUsed = used;
		this.timeTotal = total;
	}
	
	public void setTimeTotal(int total){
		this.timeTotal = total;
	}
	
	public void setTimeUsed(int used){
		this.timeUsed = used;
	}
	
	public void setTimeLeft(int left){
		this.timeLeft = left;
	}
	
	public int getTimeTotal(){
		return timeTotal;
	}
	
	public int getTimeUsed(){
		return timeUsed;
	}
	
	public int getTimeLeft(){
		return timeLeft;
	}
	
	public void drawTimer(Graphics g){
		g.setColor(new Color(103, 219, 106));
		g.fillRect(10, 10, timeLeft, 20);
		g.setColor(new Color(237,100,100));
		g.fillRect(timeTotal - timeUsed, 10, timeUsed, 20);
		
	}
	
	public void tick(){
		timeUsed += 10;
		timeLeft -= 10;
	}
	
	
}
