package frame;

import java.awt.Desktop;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;

public class SubStyleChanger extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6705941965175878915L;

	public static void main(String[] args) {
		try {
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("调用系统UI的时候出现错误");
			e.printStackTrace();
		}
		new SubStyleChanger();
	}

	public SubStyleChanger() {
		//super();
		//init();
		saveToFile("d:\\zimu.ass");
	}

	public void init() {
		String fileURL = "G:\\电视剧\\哥谭\\哥谭第四季\\gotham.s04e14.720p.web.x264-tbs.简体&英文.ass";
		add(new JScrollPane(new JTextArea(readAndChangeFileContent(fileURL))));
		setVisible(true);
		setPreferredSize(new Dimension(600, 450));
		setResizable(true);
		setTitle("字幕测试");
		// setIconImage(new ImageIcon("resource/smalltv.png").getImage());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
	}

	public String readAndChangeFileContent(String fileURL) {
		String target = "{\\fn方正综艺简体}{\\fs14}{\\b0}{\\c&HFFFFFF&}{\\3c&H2F2F2F&}{\\4c&H000000&}";
		File file = new File(fileURL);
		try {
			FileReader fr = new FileReader(file);
			BufferedReader reader = new BufferedReader(fr);
			StringBuilder changedContent = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.indexOf("{") != -1) {
					int beginIndex = line.indexOf("{");
					int endIndex = line.lastIndexOf("}");
					line = line.replace(line.substring(beginIndex, endIndex), target);
				}
				changedContent.append(line + "\n");
			}
			reader.close();
			return changedContent.toString();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void saveToFile(String newFileURL) {
		File file = new File(newFileURL);
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter(file));
			String fileURL = "G:\\电视剧\\哥谭\\哥谭第四季\\gotham.s04e14.720p.web.x264-tbs.简体&英文.ass";
			bw.write(readAndChangeFileContent(fileURL));
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		if(Desktop.isDesktopSupported()) {
//			try {
//				Desktop.getDesktop().open(file);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
	}
}
