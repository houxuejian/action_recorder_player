package utils;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * 工具类
 * @author hou
 *
 */
public class WinUtils {
	public static final Dimension DIMENSION = getScreenDimension();
	/**
	 * 屏幕宽度
	 */
	public static final int WIDTH = DIMENSION.width;
	/**
	 * 屏幕高度
	 */
	public static final int HEIGHT = DIMENSION.height;
	
	/**
	 * 获取屏幕尺寸
	 * @return
	 */
	public static Dimension getScreenDimension() {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		return dimension;
	}
	
}
