package recorder.business;

import javax.swing.JTextArea;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.mouse.NativeMouseEvent;

import recorder.model.ActionList;
import recorder.model.KeyAction;
import recorder.model.MouseAction;

/**
 * 全局鼠标键盘事件监听的实现类
 * @author hou
 *
 */
public final class EventListenerImpl extends AbstractEventListener{
	
	private static EventListenerImpl eventListenerImpl = new EventListenerImpl();
	
	private EventListenerImpl() {
		
	}
	
	public static EventListenerImpl getInstance() {
		return eventListenerImpl;
	}
	
	private void dealMouseAction(MouseAction action) {
		JTextArea area = MainWindow.jTextArea;
		area.append("步骤" +" "+ ActionList.getStep() + ": ");
		area.append(action.toString() + "\n\r");
		area.setCaretPosition(area.getText().length());
		ActionList.getList().add(action);
		ActionList.setStep(ActionList.getStep() + 1);
	}
	
	private void dealKeyAction(KeyAction action) {
		JTextArea area = MainWindow.jTextArea;
		area.append("步骤" +" "+ ActionList.getStep() + ": ");
		area.append(action.toString() + "\n\r");
		area.setCaretPosition(area.getText().length());
		ActionList.getList().add(action);
		ActionList.setStep(ActionList.getStep() + 1);
	}
	
	/**
	 * 鼠标按下
	 */
	@Override
	public void nativeMousePressed(NativeMouseEvent e) {
		MouseAction action = EventWrapper.wrapMouseAction(e, 0);
		dealMouseAction(action);
	}
	
	/**
	 * 鼠标抬起
	 */
	@Override
	public void nativeMouseReleased(NativeMouseEvent e) {
		MouseAction action = EventWrapper.wrapMouseAction(e, 1);
		dealMouseAction(action);
	}
	
	
	/**
	 * 键盘按下
	 */
	@Override
	public void nativeKeyPressed(NativeKeyEvent e) {
		KeyAction action = EventWrapper.wrapKeyAction(e, 0);
		dealKeyAction(action);
		
	}
	
	/**
	 * 键盘抬起
	 */
	@Override
	public void nativeKeyReleased(NativeKeyEvent e) {
		KeyAction action = EventWrapper.wrapKeyAction(e, 1);
		dealKeyAction(action);
	}

}
