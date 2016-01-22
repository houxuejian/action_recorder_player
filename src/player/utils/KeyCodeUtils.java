package player.utils;

import player.model.KeyAction;

/**
 * under win7 test
 * @author hou
 *
 */
public class KeyCodeUtils {
	public static int convertKeyCode(KeyAction keyAction) {
		int keyCode;
		switch (keyAction.getKeyRawCode()) {
		case 13:
			keyCode = 10;	//回车
			break;
		case 160:
			keyCode = 16;	//shift
			break;
		case 161:
			keyCode = 16;	//shift
			break;
		case 162:
			keyCode = 17;	//ctrl
			break;
		case 163:
			keyCode = 17;	//ctrl
			break;
		case 164:
			keyCode = 18;	//alt
			break;
		case 165:
			keyCode = 18;	//alt
			break;
		case 189:
			keyCode = 45;	//-
			break;
		case 187:
			keyCode = 61;	//=
			break;
		case 220:
			keyCode = 92;	//\
			break;
		case 221:
			keyCode = 93;	//]
			break;
		case 219:
			keyCode = 91;	//[
			break;
		case 186:
			keyCode = 59;	//;
			break;
		case 188:
			keyCode = 44;	//,
			break;
		case 190:
			keyCode = 46;	//.
			break;
		case 191:
			keyCode = 47;	///
			break;
		case 45:
			keyCode = 155;	//insert
			break;
		case 46:
			keyCode = 127;	//delete
			break;
		case 91:
			keyCode = 524;	//win键
			break;
		case 93:
			keyCode = 525;	//右键菜单键
			break;
		case 255:
			keyCode = 0;	//右键菜单键
			break;
		
		default:
			keyCode = keyAction.getKeyRawCode();
			break;
		}
		return keyCode;
	}
}
