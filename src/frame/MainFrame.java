package frame;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.ListIterator;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.FontUIResource;

import listener.*;

public class MainFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8109310565800527477L;
	private Box hbox;
	/** 承载背景图的按钮，放于JLabel中 */
	private ImageIcon BGP;
	private JLabel BGPLabel = null;
	private JPanel panel = null;
	private JTextField videoPath_TextField = null;
	private JButton setBGP_Button = null;
	private JCheckBox showBGP_check = null;
	private JButton importButton = null;
	private File videoPath = null;
	private String videoDrc = null;
	private JTextField example_TextField = null;
	private JComboBox<String> exampleSelectBox = null;
	private JTextField prev_TextField = null;
	private JTextField ends_TextField = null;

	private JCheckBox sameDrc_check = null;
	private JTextField subtitlePath_TextField = null;
	private JButton importButton_1 = null;
	private File subtitlePath = null;
	private String subtitleDrc = null;
	private JTextField example_TextField_1 = null;
	private JComboBox<String> exampleSelectBox_1 = null;
	private JTextField prev_TextField_1 = null;
	private JTextField ends_TextField_1 = null;
	private JTextField abandonedLag_TextField = null;
	private JCheckBox del_check = null;
	/** 完成按钮，放在第九层 */
	private JButton start_Button;
	private ArrayList<String> matchedVideos = null;
	private ArrayList<String> matchedSubs = null;
	private ArrayList<Integer> videoEpLocation = null;
	private ArrayList<Integer> subEpLocation = null;
	private final String tipInvideoPathTextField = "复制文件夹绝对路径粘贴此处/按[导入]";
	private final Dimension size = new Dimension(600, 330);

	public static void main(String[] args) {
		try {
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		new MainFrame();
	}

	public MainFrame() {
		super();
		new Config().readIni();
		initialize();
		importButton.requestFocus();
		// showBGP_check.setSelected(true);
	}

	private void initialize() {
		// setBackground(new Color(240, 240, 240));
		// new ImageTest().setOpaque(false);;
		getLayeredPane().add(getBGP(), Integer.valueOf(Integer.MIN_VALUE));
		
		panel = (JPanel) getContentPane();
		// panel.setOpaque(false);
		panel.add(getBigBox(), BorderLayout.CENTER);// , BorderLayout.NORTH
		panel.setMaximumSize(size);
		panel.setPreferredSize(size);
		setTitle("字幕文件名字替换");
		setLocationRelativeTo(null);
		// setResizable(false);
		ImageIcon imageIcon = new ImageIcon("icon/re.png");
		setIconImage(imageIcon.getImage());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addWindowListener(new MyWindowListener());
		pack();
		// if(!getShowBGP_check().isSelected()) {
		// getVideoPath_TextField().setOpaque(true);
		// getSubtitlePath_TextField().setOpaque(true);
		// getVideoPath_TextField().setBackground(Color.WHITE);
		// getSubtitlePath_TextField().setBackground(Color.white);
		// }
		repaint();
		setVisible(true);
	}

	public JLabel getBGP() {
		if (BGP == null) {
			BGP = new ImageIcon(Config.iconPath);
		}
		if (BGPLabel == null) {
			BGPLabel = new JLabel(BGP);
			BGPLabel.setBounds(0, 0, BGP.getIconWidth(), BGP.getIconHeight());
			BGPLabel.setOpaque(false);
			BGPLabel.setPreferredSize(size);
			BGPLabel.setVisible(false);
		}
		return BGPLabel;
	}

	public Box getBigBox() {
		if (hbox != null)
			return hbox;
		Box bigBox = Box.createVerticalBox();
		int gap = 7;

		/* 第一层箱子，放标签：视频文件路径 */
		Box box_1 = Box.createHorizontalBox();
		box_1.add(new JLabel("*视频文件夹路径：↓"));
		box_1.add(Box.createHorizontalGlue());
		box_1.add(getSetButton());
		box_1.add(getShowBGP_check());

		/* 第二层箱子，放文本框和导入按钮 */
		Box box_2 = Box.createHorizontalBox();
		box_2.add(getVideoPath_TextField());
		box_2.add(Box.createHorizontalStrut(5));
		box_2.add(getImportButton());

		/* 第三层箱子，放标签：example、文本框 */
		Box box_3 = Box.createHorizontalBox();
		box_3.add(new JLabel("example"));
		box_3.add(Box.createHorizontalStrut(5));
		box_3.add(getExampleSelectBox());

		/** 第四层箱子，放标签：前缀、文本框、标签：后缀、文本框 */
		Box box_4 = Box.createHorizontalBox();
		box_4.add(new JLabel("*前 缀："));
		box_4.add(getPrev_TextField());
		box_4.add(Box.createHorizontalStrut(5));
		box_4.add(new JLabel("后缀："));
		box_4.add(getEnds_TextField());

		/** 第五层箱子，放标签：字幕文件路径、按钮：与视频不同路径 */
		Box box_5 = Box.createHorizontalBox();
		box_5.add(new JLabel("*字幕文件夹路径：↓"));
		box_5.add(Box.createHorizontalStrut(5));
		box_5.add(getSameDrc_check());
		box_5.add(Box.createHorizontalStrut(30));
		box_5.add(new JLabel("字幕语言过滤："));
		box_5.add(Box.createHorizontalStrut(5));
		box_5.add(getAbandonedLag_TextField());
		box_5.add(getDel_check());

		/** 第六层箱子，放文本框、导入按钮 */
		Box box_6 = Box.createHorizontalBox();
		box_6.add(getSubtitlePath_TextField());
		box_6.add(Box.createHorizontalStrut(5));
		box_6.add(getImportButton_1());

		/** 第七层箱子，放标签：example、文本框 */
		Box box_7 = Box.createHorizontalBox();
		box_7.add(new JLabel("example"));
		box_7.add(Box.createHorizontalStrut(5));
		box_7.add(getExampleSelectBox_1());

		/** 第八层箱子，放标签：前缀、文本框、标签：后缀、文本框 */
		Box box_8 = Box.createHorizontalBox();
		box_8.add(new JLabel("*前 缀："));
		box_8.add(getPrev_TextField_1());
		box_8.add(Box.createHorizontalStrut(5));
		box_8.add(new JLabel("后缀保留位数："));
		box_8.add(getEnds_TextField_1());

		/** 第九层箱子，放字幕语言过滤 */
		/** 第十层箱子，放开始按钮 */
		Box box_10 = Box.createHorizontalBox();
		box_10.add(getStart_Button());

		bigBox.add(Box.createVerticalStrut(5));
		bigBox.add(box_1);
		bigBox.add(Box.createVerticalStrut(1));
		bigBox.add(box_2);
		bigBox.add(Box.createVerticalStrut(gap));
		bigBox.add(box_3);
		// bigBox.add(Box.createVerticalStrut(gap));
		// bigBox.add(getExampleSelectBox());
		bigBox.add(Box.createVerticalStrut(gap));
		bigBox.add(box_4);
		bigBox.add(Box.createVerticalStrut(15));
		// JLabel fengexian = new
		// JLabel("========================================================================");
		JLabel splitLine = new JLabel("→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→→");
		splitLine.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		bigBox.add(splitLine);
		bigBox.add(box_5);
		bigBox.add(Box.createVerticalStrut(gap));
		bigBox.add(box_6);
		bigBox.add(Box.createVerticalStrut(gap));
		bigBox.add(box_7);
		bigBox.add(Box.createVerticalStrut(gap));
		bigBox.add(box_8);
		// bigBox.add(Box.createVerticalStrut(gap));
		// bigBox.add(box_9);
		bigBox.add(Box.createVerticalStrut(10));
		bigBox.add(box_10);
		bigBox.add(Box.createVerticalStrut(gap));
		hbox = Box.createHorizontalBox();
		hbox.add(Box.createHorizontalStrut(15));
		hbox.add(bigBox);
		hbox.add(Box.createHorizontalStrut(15));
		hbox.setMaximumSize(size);
		return hbox;
	}

	/** 将所有需要设置是否不透明的组件设置一遍 */
	public void setComponentsOpaque(boolean opaque) {
		getVideoPath_TextField().setOpaque(opaque);
		getImportButton().setOpaque(opaque);
		getExampleSelectBox().setOpaque(opaque);
		getPrev_TextField().setOpaque(opaque);
		getEnds_TextField().setOpaque(opaque);
		getAbandonedLag_TextField().setOpaque(opaque);
		getSubtitlePath_TextField().setOpaque(opaque);
		getImportButton_1().setOpaque(opaque);
		getExampleSelectBox_1().setOpaque(opaque);
		getPrev_TextField_1().setOpaque(opaque);
		getEnds_TextField_1().setOpaque(opaque);
		getStart_Button().setOpaque(opaque);
	}

	public JCheckBox getShowBGP_check() {
		if (showBGP_check == null) {
			showBGP_check = new JCheckBox("显示背景图");
			showBGP_check.setContentAreaFilled(false);
			showBGP_check.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					getBGP().setVisible(showBGP_check.isSelected());
					panel.setOpaque(!showBGP_check.isSelected());
					setComponentsOpaque(!showBGP_check.isSelected());
					repaint();
				}
			});
		}
		return showBGP_check;
	}

	public JTextField getVideoPath_TextField() {
		if (videoPath_TextField == null) {
			videoPath_TextField = new JTextField(tipInvideoPathTextField);
			// videoPath_TextField.setOpaque(false);
			videoPath_TextField.setMaximumSize(new Dimension(1000,23));
			videoPath_TextField.addMouseListener(new MyMouseListener(MainFrame.this, videoPath_TextField));
			videoPath_TextField.addKeyListener(new Enter_Key_Listener(this));
			videoPath_TextField.addFocusListener(new FocusListener() {
				public void focusLost(FocusEvent e) {
					autoAdd(videoPath_TextField);
					autofillForAll();
					if (videoPath_TextField.getText().equals("")) {
						videoPath_TextField.setText(tipInvideoPathTextField);
					}
					autofillForAll();
					videoPath_TextField.select(0, 0);
				}

				public void focusGained(FocusEvent e) {
					if (videoPath_TextField.getText().equals(tipInvideoPathTextField)) {
						videoPath_TextField.setText("");
					}
					videoPath_TextField.selectAll();
				}
			});
		}
		return videoPath_TextField;
	}

	public JButton getImportButton() {
		if (importButton == null) {
			importButton = new JButton("导入");
			// importButton.setPreferredSize(new Dimension(60, 20));
			// importButton.setContentAreaFilled(false);
			// importButton.setBorderPainted(false);
			// importButton.setIcon(new ImageIcon("icon/imageres_5303.ico"));
			importButton.setToolTipText("导入视频文件夹");
			importButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					FileDialog fileChooser = new FileDialog(MainFrame.this, "选择视频所在的文件夹的其中一个视频", FileDialog.LOAD);
					fileChooser.setVisible(true);
					videoDrc = fileChooser.getDirectory();
					if (videoDrc != null) {
						getVideoPath_TextField().setText(videoDrc);
						autofillForAll();
					}
				}
			});
		}
		return importButton;
	}

	@Deprecated
	public JTextField getExample_TextField() {
		if (example_TextField == null) {
			example_TextField = new JTextField();
			example_TextField.addMouseListener(new MyMouseListener(this, example_TextField));
			example_TextField.addFocusListener(new SelectAll_Listener(this, example_TextField));
			example_TextField.addKeyListener(new Enter_Key_Listener(this));
		}
		return example_TextField;
	}

	public JComboBox<String> getExampleSelectBox() {
		if (exampleSelectBox == null) {
			exampleSelectBox = new JComboBox<String>();
			exampleSelectBox.setOpaque(false);
			exampleSelectBox.setEditable(true);
			exampleSelectBox.setMaximumSize(size);
			// UIManager.getUI(exampleSelectBox);
			// exampleSelectBox.setUI(UIManager.getUI(exampleSelectBox));
			exampleSelectBox.setToolTipText("从导入的文件中选择一个正片");
			// exampleSelectBox.setRenderer(new MyCellRenderer());
			// exampleSelectBox.addMouseListener(new MyMouseListener(MainFrame.this,
			// exampleSelectBox));
			exampleSelectBox.addKeyListener(new Enter_Key_Listener(this));
			exampleSelectBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					getPrev_TextField().setText(findPrev(true));
				}
			});
		}
		return exampleSelectBox;
	}

	public JTextField getPrev_TextField() {
		if (prev_TextField == null) {
			prev_TextField = new JTextField();
			prev_TextField.setToolTipText("集数之前的部分");
			prev_TextField.setMaximumSize(new Dimension(1000,23));
			prev_TextField.addMouseListener(new MyMouseListener(this, prev_TextField, "注：与集数之前部分相同的话则没问题，否则手动复制粘贴"));
			prev_TextField.addFocusListener(new SelectAll_Listener(this, prev_TextField));
			prev_TextField.addKeyListener(new Enter_Key_Listener(this));
		}
		return prev_TextField;
	}

	public JTextField getEnds_TextField() {
		if (ends_TextField == null) {
			ends_TextField = new JTextField();
			ends_TextField.setToolTipText("视频文件的后缀");
			ends_TextField.setPreferredSize(new Dimension(150, 23));
			ends_TextField.setMaximumSize(new Dimension(150, 23));
			ends_TextField.addMouseListener(new MyMouseListener(this, ends_TextField));
			ends_TextField.addFocusListener(new SelectAll_Listener(this, ends_TextField));
			ends_TextField.addKeyListener(new Enter_Key_Listener(this));
		}
		return ends_TextField;
	}

	public JCheckBox getSameDrc_check() {
		sameDrc_check = new JCheckBox("与视频同路径");
		sameDrc_check.setSelected(true);
		sameDrc_check.setOpaque(false);
		sameDrc_check.setMaximumSize(new Dimension(1000,23));
		sameDrc_check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (sameDrc_check.isSelected()) {
					getSubtitlePath_TextField().setText(getVideoPath_TextField().getText());
					getExampleSelectBox_1().setSelectedItem(getMatchedSubs().get(0).toString());
					getPrev_TextField_1().setText(findPrev(false));
				} else {
					// subtitlePath_TextField.setText("");
				}
			}
		});
		return sameDrc_check;
	}

	public JTextField getAbandonedLag_TextField() {
		if (abandonedLag_TextField == null) {
			abandonedLag_TextField = new JTextField();
			abandonedLag_TextField.setMaximumSize(new Dimension(500,23));
			abandonedLag_TextField.addKeyListener(new Enter_Key_Listener(this));
			abandonedLag_TextField
					.addMouseListener(new MyMouseListener(this, abandonedLag_TextField, "注：多个关键词之间的分割符为&，如jp&tc"));
			abandonedLag_TextField.addFocusListener(new FocusListener() {
				public void focusLost(FocusEvent arg0) {
					abandonedLag_TextField.select(0, 0);
				}

				public void focusGained(FocusEvent arg0) {
					if (abandonedLag_TextField.getText().equals("")) {
						abandonedLag_TextField.setText("cht&tc");
					}
					abandonedLag_TextField.selectAll();
				}
			});
		}
		return abandonedLag_TextField;
	}

	public JCheckBox getDel_check() {
		if (del_check == null) {
			del_check = new JCheckBox("直接删除");
			del_check.setContentAreaFilled(false);
			del_check.setSelected(true);
			del_check.setToolTipText("如果勾上，重命名的时候直接删除过滤的语言的文件");
		}
		return del_check;
	}

	public JButton getImportButton_1() {
		importButton_1 = new JButton("导入");
		// importButton_1.setPreferredSize(new Dimension(60, 20));
		// importButton_1.setContentAreaFilled(false);
		// importButton_1.setBorderPainted(true);
		importButton_1.setToolTipText("导入字幕文件夹");
		importButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FileDialog fileChooser = new FileDialog(MainFrame.this, "选择字幕所在的文件夹的其中一个字幕文件", FileDialog.LOAD);
				fileChooser.setVisible(true);
				subtitleDrc = fileChooser.getDirectory();
				if (subtitleDrc != null) {
					getSubtitlePath_TextField().setText(subtitleDrc);
					getExampleSelectBox_1().setSelectedItem(getMatchedSubs().get(0).toString());
					getPrev_TextField_1().setText(findPrev(false));
				}
			}
		});
		return importButton_1;
	}

	public JTextField getSubtitlePath_TextField() {
		if (subtitlePath_TextField == null) {
			subtitlePath_TextField = new JTextField();
			subtitlePath_TextField	.setMaximumSize(new Dimension(1000,23));
			subtitlePath_TextField.addKeyListener(new Enter_Key_Listener(this));
			subtitlePath_TextField.addMouseListener(new MyMouseListener(this, subtitlePath_TextField));
			subtitlePath_TextField.addFocusListener(new FocusListener() {
				public void focusLost(FocusEvent e) {
					autoAdd(subtitlePath_TextField);
					autofillForSub();
					subtitlePath_TextField.select(0, 0);
				}

				public void focusGained(FocusEvent e) {
					subtitlePath_TextField.selectAll();
				}
			});
		}
		return subtitlePath_TextField;
	}

	@Deprecated
	public JTextField getExample_TextField_1() {
		if (example_TextField_1 == null) {
			example_TextField_1 = new JTextField();
			example_TextField_1.addMouseListener(new MyMouseListener(MainFrame.this, example_TextField_1));
			example_TextField_1.addKeyListener(new Enter_Key_Listener(this) {
				public void keyTyped(KeyEvent e) {
					if (e.getKeyChar() == KeyEvent.VK_ENTER) {
						getPrev_TextField_1().setText(findPrev(false));
						getStart_Button().requestFocus();
					}
				}
			});
			example_TextField_1.addFocusListener(new SelectAll_Listener(this, example_TextField_1));
		}
		return example_TextField_1;
	}

	public JComboBox<String> getExampleSelectBox_1() {
		if (exampleSelectBox_1 == null) {
			exampleSelectBox_1 = new JComboBox<String>();
			// exampleSelectBox_1.setOpaque(false);
			// exampleSelectBox_1.setRenderer(new MyCellRenderer());
			exampleSelectBox_1.setEditable(true);
			exampleSelectBox_1.setMaximumSize(size);
			exampleSelectBox_1.setToolTipText("从导入的文件中选择一个正片的字幕");
			// exampleSelectBox.addMouseListener(new MyMouseListener(MainFrame.this,
			// exampleSelectBox));
			exampleSelectBox_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					getPrev_TextField_1().setText(findPrev(false));
				}
			});
		}
		return exampleSelectBox_1;
	}

	public JTextField getPrev_TextField_1() {
		if (prev_TextField_1 == null) {
			prev_TextField_1 = new JTextField();
			prev_TextField_1.setMaximumSize(new Dimension(1000,23));
			prev_TextField_1.addFocusListener(new SelectAll_Listener(this, prev_TextField_1));
			prev_TextField_1.addMouseListener(
					new MyMouseListener(MainFrame.this, prev_TextField_1, "注：与集数之前部分相同的话则没问题，否则手动复制粘贴"));
			prev_TextField_1.addKeyListener(new Enter_Key_Listener(this));

		}
		return prev_TextField_1;
	}

	public JTextField getEnds_TextField_1() {
		if (ends_TextField_1 == null) {
			ends_TextField_1 = new JTextField("1");
			ends_TextField_1.setPreferredSize(new Dimension(150, 23));
			ends_TextField_1.setMaximumSize(new Dimension(150, 23));
			ends_TextField_1.addMouseListener(new MyMouseListener(this, ends_TextField_1,
					"注：字幕文件从该序号的小数点处开始截取后缀，\n比如：.ass的话为1，.cht.ass的话为2，默认为1"));
			ends_TextField_1.addKeyListener(new Enter_Key_Listener(this));
			ends_TextField_1.addFocusListener(new SelectAll_Listener(this, ends_TextField_1));
		}
		return ends_TextField_1;
	}

	public JButton getSetButton() {
		if (setBGP_Button == null) {
			setBGP_Button = new JButton("设置背景图");
			setBGP_Button.setIcon(new ImageIcon("icon/设置.png"));
			setBGP_Button.setContentAreaFilled(false);
			setBGP_Button.setBorderPainted(false);
			setBGP_Button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					FileDialog fileDialog = new FileDialog(MainFrame.this, "选择一张图片", FileDialog.LOAD);
					fileDialog.setVisible(true);
					String path = fileDialog.getDirectory() + fileDialog.getFile();
					System.out.println(path);
					if (fileDialog.getFile() != null) {
						File file = new File(path);
						System.out.println(file);
						if (file.exists()) {
							ImageIcon tempIcon = new ImageIcon(path);
							BGP.setImage(tempIcon.getImage());
							// getAbandonedLag_TextField().setBackground(null);
							// getAbandonedLag_TextField().setOpaque(false);
							MainFrame.this.repaint();
							Config.iconPath = path;
						}
					}
				}
			});
		}
		return setBGP_Button;
	}

	public JButton getStart_Button() {
		if (start_Button == null) {
			start_Button = new JButton("开始替换");
			// start_Button.setContentAreaFilled(false);
			start_Button.setPreferredSize(new Dimension(81, 27));
			start_Button.setMaximumSize(new Dimension(81, 27));
			start_Button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if ((!getVideoPath_TextField().getText().equals(tipInvideoPathTextField))
							&& getPrev_TextField().getText() != null && getSubtitlePath_TextField().getText() != null
							&& getPrev_TextField_1().getText() != null) {
						renameBegin();
					}
				}
			});
		}
		return start_Button;
	}

	public void autoAdd(JTextField jTextField) {
		String path = jTextField.getText();
		File drc = new File(path);
		if (drc.exists() & !path.endsWith("\\")) {
			jTextField.setText(path + "\\");
		}
	}

	public void autofillForAll() {
		videoDrc = getVideoPath_TextField().getText();
		videoPath = new File(videoDrc);
		if (videoPath.exists()) {
			matchedVideos = null;
			ArrayList<String> matchedVideos = getMatchedVideos();
			if (matchedVideos.size() > 0) {
				// String exampleVideo = matchedVideos.get(0).toString();
				// getExample_TextField().setText(exampleVideo);
				getExampleSelectBox().removeAllItems();
				for (String example : matchedVideos) {
					getExampleSelectBox().addItem(example);
				}
				getPrev_TextField().setText(findPrev(true));
			}
			if (getSameDrc_check().isSelected()) {
				getSubtitlePath_TextField().setText(videoDrc);
				autofillForSub();
			} else {
				System.out.println("字幕与视频不同路径");
			}
		}
	}

	public void autofillForSub() {
		subtitleDrc = getSubtitlePath_TextField().getText();
		subtitlePath = new File(subtitleDrc);
		if (subtitlePath.exists()) {
			matchedSubs = null;
			ArrayList<String> matchedSubs = getMatchedSubs();
			if (matchedSubs.size() > 0) {
				// String exampleSub = matchedSubs.get(0).toString();
				// getExample_TextField_1().setText(exampleSub);
				getExampleSelectBox_1().removeAllItems();
				for (String example : matchedSubs) {
					getExampleSelectBox_1().addItem(example);
				}
				getPrev_TextField_1().setText(findPrev(false));
				MainFrame.this.repaint();
			}
		}
	}

	public ArrayList<String> findLikelySubs(String parentPath) {// 未过滤sp等
		System.out.println("开始寻找字幕文件");
		File drc = new File(parentPath);
		File[] files = drc.listFiles();
		ArrayList<String> matches = new ArrayList<>();
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				continue;
			}
			String fileName = files[i].getName();
			// System.out.println(fileName);
			String ends = fileName.substring(fileName.length() - 3);
			// System.out.println(fileName + "的后缀是" + ends);
			if (ends.equalsIgnoreCase("ass") || ends.equalsIgnoreCase("srt") || ends.equalsIgnoreCase("sub")) {// 是视频文件的话开始循环判断是不是正片了
				// System.out.println(fileName + "是字幕文件");
				matches.add(files[i].getName());
			} else {
				// System.out.println(fileName + "不是字幕文件");
			}
		}
		// System.out.println("全部字幕文件为" + matches);
		return matches;
	}

	/** 从指定目录下找正片,返回正片的名字或null */
	public ArrayList<String> findLikelyVideo(String parentPath) {
		System.out.println("开始寻找视频文件");
		File drc = new File(parentPath);
		File[] files = drc.listFiles();
		String[] formats = { "OP", "ED", "PV", "Menu", "Movie", "Song", "CM" };
		ArrayList<String> matches = new ArrayList<>();
		LOOP_0: for (int i = 0; i < files.length; i++) {
			// System.out.println("准备分割"+files[i].toString());
			// String[] nodes=files[i].toString().split("\\",100);
			// System.out.println("用\\分割后的长度为"+nodes.length);
			// String tempName = nodes[nodes.length-1];
			String fileName = files[i].getName();
			// String tempName=files[i].toString();
			// System.out.println(fileName);
			String ends = fileName.substring(fileName.lastIndexOf(".") + 1);
			// System.out.println(fileName + "的后缀是" + ends);
			String endsBase = "mp4,mkv,rmvb";// 所有格式的视频后缀
			if (endsBase.indexOf(ends.toLowerCase()) > -1) {// 是视频文件的话开始循环判断是不是正片了
				for (String format : formats) {// 能坚持到循环结束的话算合格
					if (fileName.indexOf(format) > -1) {// 如果包含非正片关键字，那么直接判断下一个文件了，不包含所有关键字才合格
						// System.out.println(fileName + "不是正片");
						continue LOOP_0;
					}
				}
				getEnds_TextField().setText(ends);
			} else {
				// System.out.println(fileName + "不是mp4或mkv的视频文件");
				continue;
			}
			matches.add(files[i].getName());
		}
		// System.out.println("全部正片为" + matches);
		return matches;
	}

	@Deprecated
	public static String findStart(String example) {
		int location = -1;
		String start = "无法自动识别";
		int count = 0;
		String reg = null;
		int tempLocation;
		while (start.equals("无法自动识别") && count < 25) {
			if (count < 10) {
				reg = "0" + String.valueOf(count);
			} else {
				reg = String.valueOf(count);
			}
			int fromIndex = 0;
			while (example.indexOf(reg, fromIndex) > -1) {
				tempLocation = example.indexOf(reg, fromIndex);
				if (example.indexOf("2" + reg, fromIndex) == tempLocation - 1) {
					/*
					 * if ((example.indexOf("1280", fromIndex) > -1 && (example.indexOf("1280",
					 * fromIndex) == tempLocation || example.indexOf("1280", fromIndex) ==
					 * tempLocation + 1 || example.indexOf("1280", fromIndex) == tempLocation + 2))
					 * 
					 * || (example.indexOf("720", fromIndex) > -1 && (example.indexOf("720",
					 * fromIndex) == tempLocation || example.indexOf("720", fromIndex) ==
					 * tempLocation + 1))
					 * 
					 * || (example.indexOf("264", fromIndex) > -1 && (example.indexOf("264",
					 * fromIndex) == tempLocation || example.indexOf("264", fromIndex) ==
					 * tempLocation + 1)))
					 */
					fromIndex = tempLocation + 2;
				} else {
					location = tempLocation;
					break;
				}
			}
			if (location > -1) {
				start = example.substring(0, location);
			} else {
				count++;
			}
		}
		return start;
	}

	// 还不行
	public ArrayList<Integer> findEpLocation(ArrayList<String> names) {
		ArrayList<Integer> epLocation = new ArrayList<Integer>();
		int maxLength = 0;
		for (String name : names) {
			maxLength = Math.max(name.length(), maxLength);
		}
		System.out.println("maxLength=" + maxLength);
		for (int i = 0; i < maxLength; i++) {
			System.out.println("epLocation.size()=" + epLocation.size());
			if (epLocation.size() > 0) {
				if (i > epLocation.get(epLocation.size() - 1) + 1) {// 已经间隔一位不是集数的了，任务完成
					System.out.println("已经间隔一位不是集数的了，任务完成");
					break;
				}
			}
			Boolean hasNumber = false;
			Boolean numberChanged = false;
			if (names.get(0).length() < i) {
				System.out.println("names.get(0).length() < maxLength");
				break;
			}
			Character compare = null;
			for (String name : names) {
				if (name.length() < i) {
					break;
				}
				Character a = name.charAt(i);
				// System.out.println("aChar="+a);
				if (Character.getType(a) == Character.DECIMAL_DIGIT_NUMBER) {
					hasNumber = true;
					if (compare == null) {
						compare = a;// 找到第一个是数字的值
						System.out.println("compare=" + compare);
					} else {
						if (!a.equals(compare)) {// 这次找到的数字值跟之前的不一样
							System.out.println("thisChar=" + a);
							numberChanged = true;
							break;
						}
					}
				}
			}
			System.out.print("hasNumber & numberChanged=");
			System.out.println(hasNumber & numberChanged);// 跟上一行的放一起会提示错误
			if (hasNumber & numberChanged) {
				epLocation.add(i);
				System.out.println(i + "是集数所在位置");
			}
		}
		return epLocation;
	}

	/** 用example框中的文件名截取前缀 */
	public String findPrev(Boolean videoStart) {
		if (videoStart == true) {
			if (getExampleSelectBox().getItemCount() > 0) {
				return getExampleSelectBox().getSelectedItem().toString().substring(0, getVideoEpLocation().get(0));
			} else {
				return "";
			}
		} else {
			if (getExampleSelectBox_1().getItemCount() > 0) {
				return getExampleSelectBox_1().getSelectedItem().toString().substring(0, getSubEpLocation().get(0));
			} else {
				return "";
			}
		}
	}

	// @Deprecated
	// public String findStart(ArrayList<String> files,String example) {
	// return example.substring(0, findEpLocation(files).get(0));
	// }
	/** 点开始替换后开始执行 */
	public ArrayList<String> findPassedSubtitle(ArrayList<String> allSubs) {
		String abandonedLag = getAbandonedLag_TextField().getText();
		ArrayList<String> passedSubs = new ArrayList<>();
		if (abandonedLag.equals("")) {
			return allSubs;
		} else {
			if (abandonedLag.indexOf("&") > -1) {
				String[] filters = abandonedLag.split("&");
				for (String tempSub : allSubs) {// 开始对每个字幕文件进行检查
					Boolean thisSubIsOK = true;
					for (int i = 0; i < filters.length; i++) {
						if (tempSub.toLowerCase().indexOf(filters[i]) > -1) {
							if (getDel_check().isSelected()) {// 如果直接删除的复选框被选中
								File abandenedSub = new File(getSubtitlePath_TextField().getText() + tempSub);
								abandenedSub.delete();// 是通缉对象就删除
							}
							thisSubIsOK = false;
							break;
						}
					}
					if (thisSubIsOK) {
						passedSubs.add(tempSub);
					}
				}
			} else {
				String filter = abandonedLag;
				ListIterator<String> iterator = allSubs.listIterator();
				while (iterator.hasNext()) {// 开始对每个字幕文件进行检查
					String tempSub = iterator.next();
					if (tempSub.indexOf(filter) > -1) {
						if (getDel_check().isSelected()) {// 如果直接删除的复选框被选中
							File abandenedSub = new File(getSubtitlePath_TextField().getText() + tempSub);
							Boolean good = abandenedSub.delete();// 是通缉对象就删除
							System.out.println(tempSub + "文件存在吗" + abandenedSub.exists());
							System.out.println(tempSub + "文件含有" + filter + "删除成功了吗？" + good);
						}
					} else {
						passedSubs.add(tempSub);
					}
				}
			}
			return passedSubs;
		}
	}

	public ArrayList<String> getMatchedVideos() {
		if (matchedVideos == null) {
			matchedVideos = findLikelyVideo(getVideoPath_TextField().getText());
		}
		return matchedVideos;
	}

	public ArrayList<String> getMatchedSubs() {
		if (matchedSubs == null) {
			matchedSubs = findPassedSubtitle(findLikelySubs(getSubtitlePath_TextField().getText()));
		}
		return matchedSubs;
	}

	public ArrayList<Integer> getVideoEpLocation() {
		if (videoEpLocation == null) {
			videoEpLocation = findEpLocation(getMatchedVideos());
		}
		return videoEpLocation;
	}

	public ArrayList<Integer> getSubEpLocation() {
		if (subEpLocation == null) {
			subEpLocation = findEpLocation(getMatchedSubs());
		}
		return subEpLocation;
	}

	public ArrayList<Integer> getIndexesOfReg(String example, String reg) {
		Integer index = example.indexOf(reg, 0);
		ArrayList<Integer> indexes = new ArrayList<>();
		while (index > -1) {
			indexes.add(index);
			index = example.indexOf(reg, index + 1);
		}
		return indexes;
	}

	public String getSubEnds() {
		String subEnds;// 字幕文件的格式，包括.
		String exampleSub = getExampleSelectBox_1().getSelectedItem().toString();
		if (getEnds_TextField_1().getText().equals("")) {// 后缀位数为空时，默认位数
			subEnds = exampleSub.substring(exampleSub.lastIndexOf("."));// 获取字幕文件后缀名，如.ass
		} else {
			int dotCount = Integer.parseInt(getEnds_TextField_1().getText());
			ArrayList<Integer> indexes = getIndexesOfReg(exampleSub, ".");
			if (dotCount > indexes.size()) {
				JOptionPane.showMessageDialog(MainFrame.this, "字幕文件中没有那么多小数点！已改成最多的位数", "参数错误",
						JOptionPane.ERROR_MESSAGE);
				dotCount = indexes.size();
				getEnds_TextField_1().setText(String.valueOf(indexes.size()));
			}
			int fromindex = indexes.get(indexes.size() - dotCount);
			subEnds = exampleSub.substring(fromindex);
			System.out.println("字幕文件后缀" + subEnds);
		}
		return subEnds;
	}

	public void renameBegin() {
		System.out.println("同路径吗" + getSameDrc_check().isSelected());
		String videoStrat = getPrev_TextField().getText();
		String subStart = getPrev_TextField_1().getText();
		String subEnds = getSubEnds();
		int indexAtvideo = videoStrat.length();
		int indexAtSub = subStart.length();// number在字幕文件中的位置
		String numberAtSub;// 字幕文件中第number集
		String numberAtVideo;
		String nameOfVideo;
		String nameForSub;
		File oldSubFile;
		File newSubFile;
		Boolean deleteOldSameSub = false;
		Boolean asked = false;
		int count = 0;
		ArrayList<String> allVideo = getMatchedVideos();
		ArrayList<String> allSubs = getMatchedSubs();
		System.out.println("全部视频文件：\n" + allVideo);
		System.out.println("全部字幕文件：\n" + allSubs);
		for (String tempSub : allSubs) {// 挨个读取字幕
			if (tempSub.startsWith(subStart)) {
				numberAtSub = tempSub.substring(indexAtSub, indexAtSub + getSubEpLocation().size());
				for (String tempVideo : allVideo) {// 视频未找到时挨个找
					if (tempVideo.startsWith(videoStrat)) {
						numberAtVideo = tempVideo.substring(indexAtvideo, indexAtvideo + getVideoEpLocation().size());
						if (numberAtVideo.equals(numberAtSub)) {// 如果集数对上
							nameOfVideo = tempVideo.substring(0, tempVideo.lastIndexOf("."));
							nameForSub = nameOfVideo + subEnds;
							oldSubFile = new File(getSubtitlePath_TextField().getText() + tempSub);
							newSubFile = new File(getVideoPath_TextField().getText() + nameForSub);
							if (newSubFile.exists()) {
								if (asked == false) {// 当视频文件夹下存在重名文件时弹窗判断是否要删除旧文件
									String message = "尝试将\n" + oldSubFile + "\n重命名成\n" + newSubFile
											+ "\n时发现视频文件夹下存在同名文件了\n是否删除视频文件夹下原有的同名文件";
									int option = JOptionPane.showConfirmDialog(MainFrame.this, message, "发现同名文件",
											JOptionPane.YES_NO_CANCEL_OPTION);
									asked = true;
									if (option == JOptionPane.YES_OPTION) {
										deleteOldSameSub = true;// 删除旧的
									} else if (option == JOptionPane.NO_OPTION) {
										deleteOldSameSub = false;// 不删除，重命名会失败
									} else {
										return;// 结束操作
									}
								}
								if (deleteOldSameSub) {
									newSubFile.delete();
								}
							}
							Boolean success = oldSubFile.renameTo(newSubFile);
							System.out.println("重命名成功了吗？" + success);
							if (success) {
								count++;
							}
							break;
						}
					}
				}
			}
		}
		JOptionPane.showMessageDialog(this, "总共重命名了" + count + "个字幕文件\n没完成的话尝试修改条件继续", "重命名完毕",
				JOptionPane.PLAIN_MESSAGE);
	}
}
