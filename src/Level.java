import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Level {

	private int carspeed,logspeed;
	private int loggenspeed, cargenspeed;
	private Random rand = new Random();
	private Sprite[] sprites = new Sprite[15];
	private int[] wait = new int[10];
	public int[] carCount = new int[10];
	public int[] logCount = new int[10];
	private Entity[] lilies = new Entity[5];
	private ArrayList<Entity> logs = new ArrayList<Entity>(0);
	private ArrayList<Entity> cars = new ArrayList<Entity>(0);
	private long logTime, carTime;
	private Ticker timer = new Ticker(1200, 0, 1200);
	private boolean win = false;
	private int lilycount;

	public Level() {
		this.logspeed = 0;
		this.carspeed = 0;
		this.loggenspeed = 0;
		this.cargenspeed = 0;
		this.logTime = 0;
		this.carTime = 0;
		this.lilycount = 0;
	}

	public Level(int carspeed,int logspeed, int loggenspeed, int cargenspeed) {
		this.carspeed = carspeed;
		this.logspeed = logspeed;
		this.loggenspeed = loggenspeed;
		this.cargenspeed = cargenspeed;
		this.carTime = 0;
		this.logTime = 0;
		this.lilycount = 0;

		try {
			sprites[0] = new Sprite("BullDozerRight.png");
			sprites[1] = new Sprite("BullDozerLeft.png");
			sprites[2] = new Sprite("RaceCarRight.png");
			sprites[3] = new Sprite("RaceCarLeft.png");
			sprites[4] = new Sprite("YellowCarRight.png");
			sprites[5] = new Sprite("YellowCarLeft.png");
			sprites[6] = new Sprite("PurpleCarRight.png");
			sprites[7] = new Sprite("PurpleCarLeft.png");
			sprites[8] = new Sprite("TruckRight.png");
			sprites[9] = new Sprite("TruckLeft.png");
			sprites[10] = new Sprite("SmallLog.png");
			sprites[11] = new Sprite("MediumLog.png");
			sprites[12] = new Sprite("LongLog.png");
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < 5; i++) {
			lilies[i] = new Entity();
			lilies[i].setX(800 + (i * 200));
			lilies[i].setY(200);
			lilies[i].setHeight(100);
			lilies[i].setWidth(100);

		}

	}

	public void genCar() {
		Entity car = new Entity();
		boolean passed = false;
		int row = rand.nextInt(4);
		int type = rand.nextInt(5);

		do {
			row = rand.nextInt(4);
			for (int i = 0; i < 4; i++) {
				if (row == i && carCount[i + 5] >= wait[i + 5]) {
					if (i % 2 == 0) {
						if (type == 0) {
							car.setSprite(sprites[0]);
						} else if (type == 1) {
							car.setSprite(sprites[2]);
						} else if (type == 2) {
							car.setSprite(sprites[4]);
						} else if (type == 3) {
							car.setSprite(sprites[6]);
						} else if (type == 4) {
							car.setSprite(sprites[8]);
						}
						car.setX(0 - car.getSprite().getSprite().getWidth());
						car.setSpeed(carspeed);
					} else {
						if (type == 0) {
							car.setSprite(sprites[1]);
						} else if (type == 1) {
							car.setSprite(sprites[3]);
						} else if (type == 2) {
							car.setSprite(sprites[5]);
						} else if (type == 3) {
							car.setSprite(sprites[7]);
						} else if (type == 4) {
							car.setSprite(sprites[9]);
						}
						car.setX(2400);
						car.setSpeed(carspeed * -1);
					}
					car.setHeight(car.getSprite().getSprite().getHeight());
					car.setWidth(car.getSprite().getSprite().getWidth());
					car.setY(1200 - (i * 100));
					wait[i + 5] = car.getWidth();
					carCount[i + 5] = 0;
					passed = true;
				}
			}

		} while (passed == false);

		cars.add(car);

	}

	public void genLog() {
		Entity log = new Entity();
		boolean passed = false;
		int row = rand.nextInt(5);
		int type = rand.nextInt(3);

		do {
			row = rand.nextInt(5);
			for (int i = 0; i < 5; i++) {
				if (row == i && logCount[i] >= wait[i]) {

					if (type == 0) {
						log.setSprite(sprites[10]);
					} else if (type == 1) {
						log.setSprite(sprites[11]);
					} else if (type == 2) {
						log.setSprite(sprites[12]);
					}
					if (i % 2 == 0) {
						log.setX(0 - log.getSprite().getSprite().getWidth());
						log.setSpeed(logspeed);
					} else {
						log.setX(2400);
						log.setSpeed(logspeed * -1);
					}
					log.setY(700 - (i * 100));
					log.setWidth(log.getSprite().getSprite().getWidth());
					log.setHeight(log.getSprite().getSprite().getHeight());
					wait[i] = log.getWidth();
					logCount[i] = 0;
					passed = true;
				}
			}

		} while (passed == false);

		logs.add(log);

	}

	public void checkCarCollision() {

		for (int i = 0; i < cars.size(); i++) {

			cars.get(i).moveEnemy(2);
			if (cars.get(i).getX() + cars.get(i).getWidth() <= -5 || cars.get(i).getX() >= 2405) cars.remove(i);
			if (Screen.frog.collision(cars.get(i),0)) {
				Screen.frog.setScore(Screen.frog.getScore()-200);
				Screen.frog.setLives(1);
				Screen.frog.setX(1200);
				Screen.frog.setY(1300);
			}
		}
	}

	public void checkLogCollision() {
		boolean onLog = false;

		for (int i = 0; i < logs.size(); i++) {

			logs.get(i).moveEnemy(2);
			if (logs.get(i).getX() + logs.get(i).getWidth() <= -5 || logs.get(i).getX() >= 2405) logs.remove(i);
			if (Screen.frog.collision(logs.get(i),50)) {
				Screen.frog.setY(logs.get(i).getY());
				if (logs.get(i).getSpeed() < 0) {
					Screen.frog.setOnLog(true, -1 * logspeed );
					onLog = true;

				} else {
					Screen.frog.setOnLog(true,logspeed);
					onLog = true;

				}
			}
		}

		if (Screen.frog.getY() == 200 && onLog == false) {

			for (int x = 0; x < 5; x++) {
				if (lilyCollision(Screen.frog, lilies[x])) {
					Screen.frog.setX(lilies[x].getX());
					Screen.frog.setY(lilies[x].getY());
					lilies[x].setUsed(true);
					Screen.frog.setX(1200);
					Screen.frog.setY(1300);
					Screen.frog.setOnLog(false, 1);

					break;
				}
			}

		} else if (Screen.frog.getY() >= 300 && Screen.frog.getY() < 800 && onLog == false) {
			Screen.frog.setScore(Screen.frog.getScore() -200);
			Screen.frog.setLives(1);
			Screen.frog.setX(1200);
			Screen.frog.setY(1300);
			Screen.frog.setOnLog(false, 1);

		} else if (Screen.frog.getY() >= 800 && onLog == false) {
			Screen.frog.setOnLog(false, 1);
		}
	}

	public boolean lilyCollision(Entity frog, Entity lily) {
		if ((frog.getX() + frog.getWidth()) - lily.getX() > 25 && (lily.getX() + lily.getWidth()) - frog.getX() > 25 && (frog.getY() + frog.getHeight()) - lily.getY() >=25 && (lily.getY() + lily.getHeight()) - frog.getY() >= 25) {

			if(lilycount % 2 == 0){
				loggenspeed += 5;
				logspeed += 1;
				cargenspeed -=20;
				carspeed +=1;
				lilycount++;
			}
			
	
			if(lilycount == 5){
				win = true;
			}
			return true;
		} else {
			return false;
		}
	}

	public void setCarSpeed(int speed) {
		this.carspeed = speed;
	}
	
	public void setLogSpeed(int speed){
		this.logspeed = speed;
	}

	public void setLogGenSpeed(int speed) {
		this.loggenspeed = speed;
	}

	public void setCarGenSpeed(int speed) {
		this.cargenspeed = speed;
	}

	public void setLogTime(long time) {
		this.logTime = time;
	}

	public void setCarTime(long time) {
		this.carTime = time;
	}

	public void setTimer(Ticker ticker) {
		this.timer = ticker;
	}
	public void setWin(boolean win){
		this.win = win;
	}

	public int getCarSpeed() {
		return carspeed;
	}
	public int getLogSpeed() {
		return logspeed;
	}

	public int getLogGenSpeed() {
		return loggenspeed;
	}

	public int getCarGenSpeed() {
		return cargenspeed;
	}

	public int getLogCount() {
		return logs.size();
	}

	public int getCarCount() {
		return cars.size();
	}

	public Entity getCar(int num) {
		return cars.get(num);
	}

	public Entity getLog(int num) {
		return logs.get(num);
	}

	public long getLogTime() {
		return logTime;
	}

	public long getCarTime() {
		return carTime;
	}

	public Entity getLily(int num) {
		return lilies[num];
	}

	public Ticker getTimer() {
		return timer;
	}
	
	public boolean getWin(){
		return win;
	}

}