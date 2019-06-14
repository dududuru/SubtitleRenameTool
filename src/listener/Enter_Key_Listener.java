package listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import frame.MainFrame;

/** 参数model=1的话全部区域自动填充，=2的话字幕部分自动填充 */
public class Enter_Key_Listener implements KeyListener {
	int mode;
	private MainFrame frame;

	public Enter_Key_Listener(MainFrame frame) {
		this.frame=frame;
		// this.mode = mode;
	}

	public void keyPressed(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
		if (e.getKeyChar() == KeyEvent.VK_ENTER) {
			frame.getStart_Button().requestFocus();
		}
	}
}
