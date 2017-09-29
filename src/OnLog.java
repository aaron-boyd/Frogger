
public class OnLog {
	private boolean onLog = false;
	private int direction = 1;

	public OnLog() {
		this.onLog = false;
		this.direction = 1;
	}

	public OnLog(boolean on, int dir) {
		this.onLog = on;
		this.direction = dir;
	}

	public void setOnLog(boolean on, int dir) {
		this.onLog = on;
		this.direction = dir;
	}

	public OnLog getOnLog() {
		return this;
	}

	public boolean getOn() {
		return onLog;
	}

	public int getDir() {
		return direction;
	}
}
