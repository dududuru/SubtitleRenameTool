package frame;

import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImageTest extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Icon BGP = new ImageIcon("QQ��ͼ20170904201407.png");
	Image image = Toolkit.getDefaultToolkit()
			.getImage("C:\\Users\\Bloch\\Pictures\\PotPlayer��ͼ\\QQ��ͼ20170904201407.png");

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
		setOpaque(false);
	}
}
