package recorder.business;

import java.awt.Color;
import java.awt.Font;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import org.jnativehook.GlobalScreen;

import recorder.utils.Const;
import utils.WinUtils;

/**
 * 主窗口，入口
 * @author hou
 *
 */
@SuppressWarnings("serial")
public final class MainWindow extends JFrame implements Const{
	private static final Logger LOGGER = Logger.getLogger(GlobalScreen.class.getPackage().getName()); 
	static {
		LOGGER.setUseParentHandlers(false);
		LOGGER.setLevel(Level.ALL);
	}
	
	public static JTextArea jTextArea = new JTextArea();;
	
	private JButton startButton = new JButton("start");
	private JButton stopButton = new JButton("stop");
	
	public void createJButton() {
		startButton.setLocation(STARTWIDTH, STARTHEIGHT);
		stopButton.setLocation(STOPWIDTH, STOPHEIGHT);
		startButton.setSize(80, 30);
		stopButton.setSize(80, 30);
		
		startButton.addActionListener(ActionListenerImpl.getInstance());
		stopButton.addActionListener(ActionListenerImpl.getInstance());
	}
	
	public void createJTextArea() {
		Font font = new Font("宋体",Font.LAYOUT_LEFT_TO_RIGHT,16);
		
		jTextArea.setEditable(true); 
		jTextArea.setFont(font);
		jTextArea.setLineWrap(true);
	}
	
	private MainWindow() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		setTitle("录制动作");
		setBackground(Color.WHITE);
		setLocation(WinUtils.WIDTH / 4, WinUtils.HEIGHT / 4);
		setSize(Const.FRAME_WIDTH, Const.FRAME_HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		
		createJTextArea();
		this.createJButton();
		
		JScrollPane jScrollPane = new JScrollPane(jTextArea);
		jScrollPane.setSize(Const.TEXTAREA_WIDTH, Const.TEXTAREA_HEIGHT);
		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		add(jScrollPane);
		add(this.startButton);
		add(this.stopButton);
		
		setVisible(true);
	}
	
	
	
	public static void main(String[] args) {
		new MainWindow();
	}

}
