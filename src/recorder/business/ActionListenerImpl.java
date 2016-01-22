package recorder.business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import recorder.model.Action;
import recorder.model.ActionList;
import recorder.model.KeyAction;
import recorder.model.MouseAction;
import recorder.utils.XMLWriteUtils;
import utils.JOptionPaneUtils;
import utils.ThreadUtils;

/**
 * 按钮时间监听实现
 * @author hou
 *
 */
public final class ActionListenerImpl implements ActionListener{
	private static ActionListenerImpl actionListenerImpl = new ActionListenerImpl();
	
	private ActionListenerImpl() {

	}
	public static ActionListenerImpl getInstance() {
		return actionListenerImpl;
	}
	
	/**
	 * 点击按钮，启动或停止录制
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "start":
			if (GlobalScreen.isNativeHookRegistered()) {
				break;
			} else {
				synchronized (actionListenerImpl) {
					ActionList.getList().clear();
					ActionList.setStep(1);
					startRecord();
					break;
				}
			}
		case "stop":
			if (GlobalScreen.isNativeHookRegistered()) {
				stopRecord();
				break;
			} else {
				break;
			}

		default:
			JOptionPaneUtils.alert("按钮异常");
			break;
		}
	}
	
	/**
	 * 开始录制动作
	 */
	private void startRecord() {
		try {
			//开始计时
			MainWindow.jTextArea.setText("开始录制\n");
			EventWrapper.setTime(System.currentTimeMillis());
			GlobalScreen.registerNativeHook();
			GlobalScreen.addNativeMouseListener(EventListenerImpl.getInstance());
			GlobalScreen.addNativeKeyListener(EventListenerImpl.getInstance());
			
		} catch (NativeHookException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 结束录制
	 */
	private void stopRecord() {
		ThreadUtils.sleep(15);
		GlobalScreen.removeNativeKeyListener(EventListenerImpl.getInstance());
		GlobalScreen.removeNativeMouseListener(EventListenerImpl.getInstance());
		
		try {
			GlobalScreen.unregisterNativeHook();
		} catch (NativeHookException e) {
			e.printStackTrace();
		}
		if(JOptionPaneUtils.confirm("是否生成XML？")){
			int lastStep;
			try {
				lastStep = Integer.parseInt(JOptionPaneUtils.inputDialog("请输入去除的最后无用的操作次数，不填或非法输入则为1"));
			} catch (NumberFormatException e) {
				lastStep = 1;
			}
			removeLastAction(lastStep);
			boolean isFast = false;
			if(JOptionPaneUtils.confirm("是否记录为全速执行的动作？")){
				isFast = true;
			}
			
			String fileName = JOptionPaneUtils.inputDialog("请输入文件名称，不需要扩展名，默认文件名为new");
			if (null == fileName) {
				fileName = "new";
			}
			//开始写入文件
			XMLWriteUtils.createDoc();
			for (Action action: ActionList.getList()) {
				if (action instanceof MouseAction) {
					XMLWriteUtils.createMouseStep((MouseAction) action, isFast);
				} else if(action instanceof KeyAction) {
					XMLWriteUtils.createKeyStep((KeyAction) action, isFast);
				} else {
					JOptionPaneUtils.alert("错误");
					System.exit(0);
				}
			}
			
			XMLWriteUtils.writeXML(XMLWriteUtils.doc, fileName + ".xml");
			//初始化
			XMLWriteUtils.doc = null;
			XMLWriteUtils.stepNum = 1;
			
			JOptionPaneUtils.alert("保存成功");
		}
		MainWindow.jTextArea.append("点击start按钮可再次录制\n");
	}
	
	/**
	 * 去除最后多余的动作
	 */
	private synchronized void removeLastAction(int lastStep) {
		int[] lastStepIndx = new int[lastStep * 2];
		for (int i = 0; i < lastStepIndx.length; i++) {
			lastStepIndx[i] = i + 1;
		}
		
		List<Action> actions = ActionList.getList();
		List<Action> lastActions = new ArrayList<Action>();
		
		for (int i = 0; i < lastStepIndx.length; i++) {
			for (Action action : actions) {
				if (action.getStep() == ActionList.getStep() - lastStepIndx[i]) {
					lastActions.add(action);
					break;
				}
			}
		}
		actions.removeAll(lastActions);
	}
	
}
