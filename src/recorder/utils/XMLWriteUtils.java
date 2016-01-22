package recorder.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import recorder.model.KeyAction;
import recorder.model.MouseAction;
import utils.JOptionPaneUtils;

public class XMLWriteUtils {
	public static Document doc;
	public static Element rootElement;
	public static int stepNum = 1;
	
	public static void main(String[] args) {
		KeyAction action = new KeyAction();
		action.setKeyName("ctrl");
		action.setTime(24442455);
		createKeyStep(action, true);
		createKeyStep(action, true);
		XMLWriteUtils.writeXML(doc, "1.xml");
	}
	
	/**
	 * 新建一个空的步骤
	 * @return
	 */
	private static Element newStep() {
		rootElement.setAttribute("count", stepNum + "");
		Element element = new Element("step");
		stepNum ++;
		return element;
	}
	
	/**
	 * 创建鼠标step，isFast为真时，时间间隔为0
	 * @param action
	 * @param isFast
	 */
	public static void createMouseStep(MouseAction action, boolean isFast) {
		Element element = newStep();
		element.setAttribute("actionType", "mouse");
		element.setAttribute("x", action.getX() + "");
		element.setAttribute("y", action.getY() + "");
		element.setAttribute("key", action.getKey() + "");
		element.setAttribute("type", action.getType() + "");
		element.setAttribute("step", action.getStep() + "");
		element.setAttribute("realTime", action.getTime() + "");
		//如果为全速执行动作，间隔时间设为0
		if (isFast) {
			element.setAttribute("time", "0");
		} else {
			element.setAttribute("time", action.getTime() + "");
		}
		rootElement.addContent(element);
	}
	
	/**
	 * 创建键盘step，isFast为真时，时间间隔为0
	 * @param action
	 * @param isFast
	 */
	public static void createKeyStep(KeyAction action, boolean isFast) {
		Element element = newStep();
		element.setAttribute("actionType", "key");
		element.setAttribute("type", action.getType() + "");
		element.setAttribute("keyName", action.getKeyName());
		element.setAttribute("keyRawCode", action.getKeyRawCode());
		element.setAttribute("step", action.getStep() + "");
		element.setAttribute("realTime", action.getTime() + "");
		element.setAttribute("time", action.getTime() + "");
		//如果为全速执行动作，间隔时间设为0
		if (isFast) {
			element.setAttribute("time", "0");
		} else {
			element.setAttribute("time", action.getTime() + "");
		}
		rootElement.addContent(element);
	}
	
	
	/**
	 * 将doc写入xml文件
	 * @param doc XML文档对象
	 * @param fileName 文件名
	 * @return
	 */
	public static boolean writeXML(Document doc,String fileName) {
		XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat().setEncoding("utf-8"));
		try {
			outputter.output(doc, new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8"));
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPaneUtils.alert("写入文件异常：" + e.getMessage());
		}
		return false;
	}
	
	/**
	 * 创建XML文档对象
	 * @return 
	 */
	public static void createDoc() {
		rootElement = new Element("action").setAttribute("count", "0");
		doc = new Document(rootElement);
	}

}

