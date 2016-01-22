package recorder.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ActionList {
	
	/**
	 * 步数
	 */
	private static volatile int step = 1;
	
	private static volatile List<Action> list = Collections.synchronizedList(new ArrayList<Action>());

	public synchronized static List<Action> getList() {
		return list;
	}

	public synchronized static void setList(List<Action> list) {
		ActionList.list = list;
	}

	public synchronized static int getStep() {
		return step;
	}

	public synchronized static void setStep(int step) {
		ActionList.step = step;
	}
	
}
