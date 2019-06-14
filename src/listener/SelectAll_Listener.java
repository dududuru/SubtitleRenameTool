package listener;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JFrame;
import javax.swing.JTextField;

import frame.MainFrame;

public 	class SelectAll_Listener implements FocusListener {
	private JTextField jTextField;
	private MainFrame frame;

	public SelectAll_Listener(MainFrame frame, JTextField jTextField) {
		this.frame=frame;
		this.jTextField = jTextField;
	}

	public void focusGained(FocusEvent e) {
		jTextField.selectAll();
	}

	public void focusLost(FocusEvent e) {
		frame.repaint();
		jTextField.select(0, 0);
		frame.setPreferredSize(frame.getBigBox().getPreferredSize());
		//frame.pack();
	}
}

