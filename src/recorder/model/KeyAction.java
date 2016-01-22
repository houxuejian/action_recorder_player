package recorder.model;

public class KeyAction extends Action{

	/**
	 * 按键名称
	 */
	private String keyName;

	/**
	 * 按键代码
	 */
	private String keyRawCode;

	
	
	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public String getKeyRawCode() {
		return keyRawCode;
	}

	public void setKeyRawCode(String keyRawCode) {
		this.keyRawCode = keyRawCode;
	}

	@Override
	public String toString() {
		return "键盘动作 [ keyName=" + keyName
				+ ", keyRawCode=" + keyRawCode 
				+ super.toString() + "]";
	}
	
}
