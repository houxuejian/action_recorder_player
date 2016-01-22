package player.model;

public class KeyAction extends Action{

	/**
	 * 按键名称
	 */
	private String keyName;

	/**
	 * 按键代码
	 */
	private int keyRawCode;

	
	
	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public int getKeyRawCode() {
		return keyRawCode;
	}

	public void setKeyRawCode(int keyRawCode) {
		this.keyRawCode = keyRawCode;
	}

	@Override
	public String toString() {
		return "键盘动作 [ keyName=" + keyName
				+ ", keyRawCode=" + keyRawCode 
				+ super.toString() + "]";
	}
	
}
