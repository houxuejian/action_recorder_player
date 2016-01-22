package recorder.business;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;

public abstract class AbstractEventListener implements NativeKeyListener, NativeMouseInputListener{

	@Override
	public void nativeMouseClicked(NativeMouseEvent e) {
		
	}

	@Override
	public void nativeMousePressed(NativeMouseEvent e) {
		
	}

	@Override
	public void nativeMouseReleased(NativeMouseEvent e) {
		
	}

	@Override
	public void nativeMouseMoved(NativeMouseEvent e) {
		
	}

	@Override
	public void nativeMouseDragged(NativeMouseEvent e) {
		
	}

	@Override
	public void nativeKeyPressed(NativeKeyEvent e) {
		
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent e) {
		
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent e) {
		
	}
	
}
