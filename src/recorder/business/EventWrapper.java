package recorder.business;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.mouse.NativeMouseEvent;

import recorder.model.ActionList;
import recorder.model.KeyAction;
import recorder.model.MouseAction;


public final class EventWrapper{
	private static volatile long time;
	
	private EventWrapper() {
		
	}
	
	/**
	 * 将MouseAction包装为成鼠标事件对象
	 * @param e
	 * @param type
	 * @return
	 */
	public static MouseAction wrapMouseAction(NativeMouseEvent e, int type) {
		MouseAction mouseAction = new MouseAction();
		mouseAction.setKey(e.getButton());
		mouseAction.setStep(ActionList.getStep());
		mouseAction.setTime((int) (e.getWhen() - time));
		setTime(e.getWhen());
		mouseAction.setType(type);
		mouseAction.setX(e.getX());
		mouseAction.setY(e.getY());
		return mouseAction;
		
	}

	/**
	 * 将KeyEvent包装为成键盘事件对象
	 * @param e
	 * @param type
	 * @return
	 */
	public static KeyAction wrapKeyAction(NativeKeyEvent e, int type) {
		KeyAction keyAction = new KeyAction();
		keyAction.setKeyName(String.valueOf(NativeKeyEvent.getKeyText(e.getKeyCode())));
		keyAction.setKeyRawCode(String.valueOf(e.getRawCode()));
		keyAction.setStep(ActionList.getStep());
		keyAction.setTime((int) (e.getWhen() - time));
		setTime(e.getWhen());
		keyAction.setType(type);
		return keyAction;
		
	}

	public synchronized static void setTime(long time) {
		EventWrapper.time = time;
	}
	
}
