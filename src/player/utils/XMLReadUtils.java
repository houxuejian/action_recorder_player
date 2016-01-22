package player.utils;

import java.awt.event.InputEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import player.model.ActionList;
import player.model.KeyAction;
import player.model.MouseAction;
import utils.JOptionPaneUtils;

public class XMLReadUtils {
	public static Document doc;
	public static Element rootElement;
	public static List<Element> steps;
	
	public static void main(String[] args) {
		readXMLDocument("test2.xml");
		validateDocument();
		stepsConvertToActions();
	}
	
	/**
	 * XML中全部动作读到ActionList中
	 */
	public static void stepsConvertToActions() {
		ActionList.setStep(steps.size());
		for (Element step : steps) {
			if ("mouse".equals(step.getAttributeValue("actionType"))) {
				ActionList.getList().add(convertMouseAction(step));
			} else if ("key".equals(step.getAttributeValue("actionType"))) {
				ActionList.getList().add(convertKeyAction(step));
			}
		}
	}
	
	/**
	 * 一条step记录转换为MouseAction对象
	 * @param step
	 * @return
	 */
	private static MouseAction convertMouseAction(Element step) {
		MouseAction mouseAction = new MouseAction();
		mouseAction.setX(Integer.parseInt(step.getAttributeValue("x")));
		mouseAction.setY(Integer.parseInt(step.getAttributeValue("y")));
		mouseAction.setTime(Integer.parseInt(step.getAttributeValue("time")));
		mouseAction.setStep(Integer.parseInt(step.getAttributeValue("step")));
		mouseAction.setType(Integer.parseInt(step.getAttributeValue("type")));
		if ("1".equals(step.getAttributeValue("key"))) {
			mouseAction.setKey(InputEvent.BUTTON1_MASK);
		}
		if ("2".equals(step.getAttributeValue("key"))) {
			mouseAction.setKey(InputEvent.BUTTON3_MASK);
		}
		return mouseAction;
	}
	
	/**
	 * 一条step记录转换为KeyAction对象
	 * @param step
	 * @return
	 */
	private static KeyAction convertKeyAction(Element step) {
		KeyAction keyAction = new KeyAction();
		keyAction.setStep(Integer.parseInt(step.getAttributeValue("step")));
		keyAction.setTime(Integer.parseInt(step.getAttributeValue("time")));
		keyAction.setKeyName(step.getAttributeValue("keyName"));
		keyAction.setType(Integer.parseInt(step.getAttributeValue("type")));
		keyAction.setKeyRawCode(Integer.parseInt(step.getAttributeValue("keyRawCode")));
		return keyAction;
	}
	
	/**
	 * 从XML文件生成document
	 * @param fileName
	 */
	public static void readXMLDocument(String fileName) {
		SAXBuilder builder = new SAXBuilder();
		try {
			doc = builder.build(new File(fileName));
			rootElement = doc.getRootElement();
			steps = rootElement.getChildren();
		} catch (JDOMException | IOException e) {
			JOptionPaneUtils.alert("读取文件异常" + e.getMessage());
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	/**
	 * 验证XML
	 * 不通过验证则程序结束
	 */
	public static void validateDocument() {
		if (!"action".equals(rootElement.getName())) {
			JOptionPaneUtils.alert("标签名称错误");
			System.exit(0);
		}
		if (steps.size() == 0) {
			JOptionPaneUtils.alert("空文档");
			System.exit(0);
		}
		if (!Integer.toString(steps.size()).equals(rootElement.getAttributeValue("count"))) {
			JOptionPaneUtils.alert("步数错误");
			System.exit(0);
		}
		for (int i = 0; i < steps.size(); i++) {
			if (Integer.parseInt( steps.get(i).getAttributeValue("step")) != i + 1) {
				JOptionPaneUtils.alert("步数不连续");
				System.exit(0);
			}
		}
	}
	
}

