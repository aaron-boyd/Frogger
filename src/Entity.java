import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entity {
	protected int myx, myy;
	protected int mywidth, myheight;
	protected Sprite mysprite;
	protected int myspeed;
	protected OnLog onLog;
	protected boolean bool;

	public Entity[] obs = new Entity[100];

	public Entity(int x, int y, int speed, String path) {
		this.myx = x;
		this.myy = y;
		this.myspeed = speed;
		try {
			this.mysprite = new Sprite(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.mywidth = this.mysprite.getSprite().getWidth();
		this.myheight = this.mysprite.getSprite().getHeight();
		this.onLog = new OnLog(false, 1);
		this.bool =false;
	}

	public Entity() {
		this.myx = 0;
		this.myy = 0;
		this.mywidth = 0;
		this.myheight = 0;
		this.myspeed = 0;
		this.onLog = new OnLog(false, 1);
		this.bool = false;

	}

	public Entity getOb(int num) {
		return obs[num];
	}

	public void moveFrog(int xAmount, int yAmount) {
		this.myx += xAmount * this.myspeed;
		this.myy += yAmount * this.myspeed;
	}

	public void moveEnemy(int amount) {
		this.myx += amount * this.myspeed;
	}

	public void setX(int x) {
		this.myx = x;
	}

	public void setY(int y) {
		this.myy = y;
	}

	public void setSpeed(int speed) {
		this.myspeed = speed;
	}

	public void setWidth(int w) {
		this.mywidth = w;
	}

	public void setHeight(int h) {
		this.myheight = h;
	}

	public void setSprite(Sprite sprite) {
		this.mysprite = sprite;
	}

	public void setOnLog(boolean on, int dir) {
		this.onLog.setOnLog(on, dir);

	}
	
	public void setUsed(boolean used){
		this.bool = used;
	}

	public int getX() {
		return this.myx;
	}

	public int getY() {
		return this.myy;
	}

	public int getWidth() {
		return this.mywidth;
	}

	public int getHeight() {
		return this.myheight;
	}

	public int getSpeed() {
		return this.myspeed;
	}

	public Sprite getSprite() {
		return mysprite;
	}

	public OnLog getOnLog() {
		return onLog;
	}
	
	public boolean getUsed(){
		return bool;
	}

	public String toString() {
		return (myx + " " + myy + " " + mywidth + " " + myheight + " " + myspeed);
	}

	public boolean collision(Entity enemy, int buffer) {
		boolean collide = false;
		if ((myx + mywidth) - enemy.getX() > buffer && (enemy.getX() + enemy.getWidth()) - myx > buffer && (myy + myheight) - enemy.getY() >buffer && (enemy.getY() + enemy.getHeight()) - myy > buffer) {
			// System.out.println("Collide!");
			collide = true;
		}
		return collide;
	}

}
