package recorder.model;

public abstract class Action {
	
	/**
	 * 0按下1释放
	 */
	private int type;
	
	/**
	 * 步骤
	 */	
	private int step;
	
	/**
	 * 间隔时间
	 * 毫秒
	 */
	private int time;

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "  Action [type=" + type + ", step=" + step + ", time=" + time
				+ "]";
	}
	
}
