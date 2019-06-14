package listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import frame.MainFrame;

/** ����model=1�Ļ�ȫ�������Զ���䣬=2�Ļ���Ļ�����Զ���� */
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
