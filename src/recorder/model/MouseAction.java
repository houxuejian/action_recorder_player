package recorder.model;

public class MouseAction extends Action{
	private int x;
	private int y;
	
	/**
	 * 左1右2中3
	 */
	private int key;
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	
	@Override
	public String toString() {
		return "鼠标动作 [x=" + x + ", y=" + y + ", key=" + key
					+ super.toString() + "]";
	} 
	
}
