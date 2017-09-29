import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class Button {
	private int myx, myy, mywidth, myheight;
	private Color myColor;

	public Button() {
		myx = 0;
		myy = 0;
		mywidth = 0;
		myheight = 0;
		myColor = new Color(0, 0, 0);
	}

	public Button(int x, int y, int width, int height, Color color) {
		myx = x;
		myy = y;
		mywidth = width;
		myheight = height;
		myColor = color;
	}

	public void setX(int x) {
		myx = x;
	}

	public void setY(int y) {
		myy = y;
	}

	public void setWidth(int w) {
		mywidth = w;
	}

	public void setHeight(int h) {
		myheight = h;
	}

	public void setColor(Color color) {
		myColor = color;
	}

	public int getX() {
		return myx;
	}

	public int getY() {
		return myy;
	}

	public int getWidth() {
		return mywidth;
	}

	public int getHeight() {
		return myheight;
	}
	
	public Color getColor(){
		return myColor;
	}
	
	public void drawButton(Graphics2D g2){
		g2.setComposite(AlphaComposite.SrcOver.derive(0.95f));
		g2.setColor(myColor);
		g2.fillRect(myx,myy,mywidth,myheight);
	}	
	
	public boolean checkClicked(MouseEvent e){
		if(e.getX() > myx && e.getX() < (myx + mywidth + 50) && e.getY() > myy && e.getY() < (myy + myheight + 50)){
			return true;
		}else{
			return false;
		}
	}
	public String toString(){
		return(myx + " " + myy + " " + mywidth + " " + myheight);
	}
}
