package player.business;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.mouse.NativeMouseWheelEvent;
import org.jnativehook.mouse.NativeMouseWheelListener;

import player.model.Action;
import player.model.ActionList;
import player.model.KeyAction;
import player.model.MouseAction;
import player.utils.KeyCodeUtils;
import player.utils.XMLReadUtils;
import utils.JOptionPaneUtils;

public class ActionPlayer {
	/**
	 * 最小延时毫秒
	 */
	public static int minDelay = 1;
	private static Robot robot = null;
	
	/**
	 * 创建robot
	 */
	public static void createRobot() {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			JOptionPaneUtils.alert(e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * 开始执行所有动作
	 */
	public static void playerStart() {
		for (Action action : ActionList.getList()) {
			if (action instanceof MouseAction) {
				doMouseAction((MouseAction) action);
			} else if (action instanceof KeyAction) {
				doKeyAction((KeyAction) action);
			}
		}
	}
	
	 /**
	  * 执行鼠标动作
	  * @param mouseAction
	  */
	private static void doMouseAction(MouseAction mouseAction) {
		//延时
		robot.delay(mouseAction.getTime());
		
		robot.mouseMove(mouseAction.getX(), mouseAction.getY());
		if (mouseAction.getType() == 0) {
			robot.mousePress(mouseAction.getKey());
		}else if (mouseAction.getType() == 1) {
			robot.mouseRelease(mouseAction.getKey());
		}
	}
	
	/**
	 * 执行键盘动作
	 * @param keyAction
	 */
	private static void doKeyAction(KeyAction keyAction) {
		//延时
		robot.delay(keyAction.getTime());
		
		int keyCode = KeyCodeUtils.convertKeyCode(keyAction);
		try {
			if (keyAction.getType() == 0) {
				robot.keyPress(keyCode);
			} else if (keyAction.getType() == 1) {
				robot.keyRelease(keyCode);
			}
		} catch (IllegalArgumentException e) {
			JOptionPaneUtils.alert(keyAction.getKeyName() + "    \t找不到对应的键位");
			JOptionPaneUtils.alert(e.getMessage());
			System.exit(0);
		}
		
	}
	
	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Usage: filename MIN_DELAY");
		}
		try {
			XMLReadUtils.readXMLDocument(args[0]);
		} catch (ArrayIndexOutOfBoundsException e) {
			JOptionPaneUtils.alert("不要直接运行，请加上正确的文件名");
			System.exit(0);
		}
		//第二参数为最小延时
		try {
			minDelay = Integer.parseInt(args[1]);
			if(minDelay > 600000 || minDelay < 2){
				minDelay = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPaneUtils.alert(e.getMessage());
		}
		XMLReadUtils.validateDocument();
		XMLReadUtils.stepsConvertToActions();
		createRobot();
		//停止的热键
		new hotKey();
		playerStart();
		System.exit(0);
	}
	
	/**
	 * 热键，滚动鼠标滚轮可以终止程序运行
	 * @author hou
	 *
	 */
	private static class hotKey implements NativeMouseWheelListener{
		private final Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
		public hotKey() {
			logger.setUseParentHandlers(false);
			logger.setLevel(Level.ALL);
			try {
				GlobalScreen.registerNativeHook();
				GlobalScreen.addNativeMouseWheelListener(this);
			} catch (NativeHookException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void nativeMouseWheelMoved(NativeMouseWheelEvent e) {
			System.exit(0);
		}
	}
}
