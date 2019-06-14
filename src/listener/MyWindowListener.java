package listener;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import frame.Config;

public class MyWindowListener implements WindowListener {
	public void windowOpened(WindowEvent e) {
	}

	public void windowIconified(WindowEvent e) {
	}

	public void windowDeiconified(WindowEvent e) {
	}

	public void windowDeactivated(WindowEvent e) {
	}

	public void windowClosing(WindowEvent e) {
		new Config().writeIni();
	}

	public void windowClosed(WindowEvent e) {
	}

	public void windowActivated(WindowEvent e) {
	}
}
