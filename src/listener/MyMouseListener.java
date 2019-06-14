package listener;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JWindow;

import frame.MainFrame;

public class MyMouseListener implements MouseListener {
	private JTextField jTextField;
	private JWindow wholeTextWindow;
	private JTextArea windowTextArea;
	private String rawText = "";
	private String attachedText;
	private String textForWindow;
	private MainFrame frame;

	public MyMouseListener(MainFrame frame, JTextField jTextField) {// 内部类的构造方法
		this.jTextField = jTextField;
		//this.jTextField.setOpaque(false);
		this.frame = frame;
		attachedText = "";
	}

	public MyMouseListener(MainFrame frame, JTextField jTextField, String attachedText) {// 内部类的构造方法
		this.jTextField = jTextField;
		// this.jTextField.setOpaque(false);
		this.attachedText = attachedText;
		this.frame = frame;
	}

	public JWindow getWindow() {
		if (jTextField.getPreferredSize().getWidth() > jTextField.getSize().getWidth()) {
			rawText = jTextField.getText();
		}
		if (rawText.equals("") || attachedText.equals("")) {
			textForWindow = rawText + attachedText;
		} else {
			textForWindow = rawText + "\n" + attachedText;
		}
		if (wholeTextWindow == null) {
			wholeTextWindow = new JWindow();
			windowTextArea = new JTextArea(textForWindow);
			windowTextArea.setBackground(new Color(255, 255, 180));
			windowTextArea.setBorder(jTextField.getBorder());
			// wholeTextWindow.setOpacity((float) 0.9);
			wholeTextWindow.add(windowTextArea);
		} else {
			windowTextArea.setText(textForWindow);
		}
		return wholeTextWindow;
	};

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {
		if (frame.getShowBGP_check().isSelected()) {
			jTextField.setOpaque(true);
			frame.repaint();
		}
		// System.out.println(jTextField.getPreferredSize().getWidth());
		// System.out.println(jTextField.getSize().getWidth());
		if (jTextField.getPreferredSize().getWidth() > jTextField.getSize().getWidth() || attachedText != "") {
			getWindow().pack();
			getWindow().setVisible(true);
			getWindow().setLocation(jTextField.getLocationOnScreen().x, jTextField.getLocationOnScreen().y + 28);
		}
	}

	public void mouseExited(MouseEvent e) {
		if (frame.getShowBGP_check().isSelected()) {
			jTextField.setOpaque(false);
			frame.repaint();
		}
		frame.repaint();
		getWindow().setVisible(false);
	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}

}
