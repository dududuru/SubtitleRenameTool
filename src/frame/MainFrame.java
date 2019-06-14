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
	/** ���ر���ͼ�İ�ť������JLabel�� */
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
	/** ��ɰ�ť�����ڵھŲ� */
	private JButton start_Button;
	private ArrayList<String> matchedVideos = null;
	private ArrayList<String> matchedSubs = null;
	private ArrayList<Integer> videoEpLocation = null;
	private ArrayList<Integer> subEpLocation = null;
	private final String tipInvideoPathTextField = "�����ļ��о���·��ճ���˴�/��[����]";
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
		setTitle("��Ļ�ļ������滻");
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

		/* ��һ�����ӣ��ű�ǩ����Ƶ�ļ�·�� */
		Box box_1 = Box.createHorizontalBox();
		box_1.add(new JLabel("*��Ƶ�ļ���·������"));
		box_1.add(Box.createHorizontalGlue());
		box_1.add(getSetButton());
		box_1.add(getShowBGP_check());

		/* �ڶ������ӣ����ı���͵��밴ť */
		Box box_2 = Box.createHorizontalBox();
		box_2.add(getVideoPath_TextField());
		box_2.add(Box.createHorizontalStrut(5));
		box_2.add(getImportButton());

		/* ���������ӣ��ű�ǩ��example���ı��� */
		Box box_3 = Box.createHorizontalBox();
		box_3.add(new JLabel("example"));
		box_3.add(Box.createHorizontalStrut(5));
		box_3.add(getExampleSelectBox());

		/** ���Ĳ����ӣ��ű�ǩ��ǰ׺���ı��򡢱�ǩ����׺���ı��� */
		Box box_4 = Box.createHorizontalBox();
		box_4.add(new JLabel("*ǰ ׺��"));
		box_4.add(getPrev_TextField());
		box_4.add(Box.createHorizontalStrut(5));
		box_4.add(new JLabel("��׺��"));
		box_4.add(getEnds_TextField());

		/** ��������ӣ��ű�ǩ����Ļ�ļ�·������ť������Ƶ��ͬ·�� */
		Box box_5 = Box.createHorizontalBox();
		box_5.add(new JLabel("*��Ļ�ļ���·������"));
		box_5.add(Box.createHorizontalStrut(5));
		box_5.add(getSameDrc_check());
		box_5.add(Box.createHorizontalStrut(30));
		box_5.add(new JLabel("��Ļ���Թ��ˣ�"));
		box_5.add(Box.createHorizontalStrut(5));
		box_5.add(getAbandonedLag_TextField());
		box_5.add(getDel_check());

		/** ���������ӣ����ı��򡢵��밴ť */
		Box box_6 = Box.createHorizontalBox();
		box_6.add(getSubtitlePath_TextField());
		box_6.add(Box.createHorizontalStrut(5));
		box_6.add(getImportButton_1());

		/** ���߲����ӣ��ű�ǩ��example���ı��� */
		Box box_7 = Box.createHorizontalBox();
		box_7.add(new JLabel("example"));
		box_7.add(Box.createHorizontalStrut(5));
		box_7.add(getExampleSelectBox_1());

		/** �ڰ˲����ӣ��ű�ǩ��ǰ׺���ı��򡢱�ǩ����׺���ı��� */
		Box box_8 = Box.createHorizontalBox();
		box_8.add(new JLabel("*ǰ ׺��"));
		box_8.add(getPrev_TextField_1());
		box_8.add(Box.createHorizontalStrut(5));
		box_8.add(new JLabel("��׺����λ����"));
		box_8.add(getEnds_TextField_1());

		/** �ھŲ����ӣ�����Ļ���Թ��� */
		/** ��ʮ�����ӣ��ſ�ʼ��ť */
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
		JLabel splitLine = new JLabel("��������������������������������������������������������������������������������������������");
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

	/** ��������Ҫ�����Ƿ�͸�����������һ�� */
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
			showBGP_check = new JCheckBox("��ʾ����ͼ");
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
			importButton = new JButton("����");
			// importButton.setPreferredSize(new Dimension(60, 20));
			// importButton.setContentAreaFilled(false);
			// importButton.setBorderPainted(false);
			// importButton.setIcon(new ImageIcon("icon/imageres_5303.ico"));
			importButton.setToolTipText("������Ƶ�ļ���");
			importButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					FileDialog fileChooser = new FileDialog(MainFrame.this, "ѡ����Ƶ���ڵ��ļ��е�����һ����Ƶ", FileDialog.LOAD);
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
			exampleSelectBox.setToolTipText("�ӵ�����ļ���ѡ��һ����Ƭ");
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
			prev_TextField.setToolTipText("����֮ǰ�Ĳ���");
			prev_TextField.setMaximumSize(new Dimension(1000,23));
			prev_TextField.addMouseListener(new MyMouseListener(this, prev_TextField, "ע���뼯��֮ǰ������ͬ�Ļ���û���⣬�����ֶ�����ճ��"));
			prev_TextField.addFocusListener(new SelectAll_Listener(this, prev_TextField));
			prev_TextField.addKeyListener(new Enter_Key_Listener(this));
		}
		return prev_TextField;
	}

	public JTextField getEnds_TextField() {
		if (ends_TextField == null) {
			ends_TextField = new JTextField();
			ends_TextField.setToolTipText("��Ƶ�ļ��ĺ�׺");
			ends_TextField.setPreferredSize(new Dimension(150, 23));
			ends_TextField.setMaximumSize(new Dimension(150, 23));
			ends_TextField.addMouseListener(new MyMouseListener(this, ends_TextField));
			ends_TextField.addFocusListener(new SelectAll_Listener(this, ends_TextField));
			ends_TextField.addKeyListener(new Enter_Key_Listener(this));
		}
		return ends_TextField;
	}

	public JCheckBox getSameDrc_check() {
		sameDrc_check = new JCheckBox("����Ƶͬ·��");
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
					.addMouseListener(new MyMouseListener(this, abandonedLag_TextField, "ע������ؼ���֮��ķָ��Ϊ&����jp&tc"));
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
			del_check = new JCheckBox("ֱ��ɾ��");
			del_check.setContentAreaFilled(false);
			del_check.setSelected(true);
			del_check.setToolTipText("������ϣ���������ʱ��ֱ��ɾ�����˵����Ե��ļ�");
		}
		return del_check;
	}

	public JButton getImportButton_1() {
		importButton_1 = new JButton("����");
		// importButton_1.setPreferredSize(new Dimension(60, 20));
		// importButton_1.setContentAreaFilled(false);
		// importButton_1.setBorderPainted(true);
		importButton_1.setToolTipText("������Ļ�ļ���");
		importButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FileDialog fileChooser = new FileDialog(MainFrame.this, "ѡ����Ļ���ڵ��ļ��е�����һ����Ļ�ļ�", FileDialog.LOAD);
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
			exampleSelectBox_1.setToolTipText("�ӵ�����ļ���ѡ��һ����Ƭ����Ļ");
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
					new MyMouseListener(MainFrame.this, prev_TextField_1, "ע���뼯��֮ǰ������ͬ�Ļ���û���⣬�����ֶ�����ճ��"));
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
					"ע����Ļ�ļ��Ӹ���ŵ�С���㴦��ʼ��ȡ��׺��\n���磺.ass�Ļ�Ϊ1��.cht.ass�Ļ�Ϊ2��Ĭ��Ϊ1"));
			ends_TextField_1.addKeyListener(new Enter_Key_Listener(this));
			ends_TextField_1.addFocusListener(new SelectAll_Listener(this, ends_TextField_1));
		}
		return ends_TextField_1;
	}

	public JButton getSetButton() {
		if (setBGP_Button == null) {
			setBGP_Button = new JButton("���ñ���ͼ");
			setBGP_Button.setIcon(new ImageIcon("icon/����.png"));
			setBGP_Button.setContentAreaFilled(false);
			setBGP_Button.setBorderPainted(false);
			setBGP_Button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					FileDialog fileDialog = new FileDialog(MainFrame.this, "ѡ��һ��ͼƬ", FileDialog.LOAD);
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
			start_Button = new JButton("��ʼ�滻");
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
				System.out.println("��Ļ����Ƶ��ͬ·��");
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

	public ArrayList<String> findLikelySubs(String parentPath) {// δ����sp��
		System.out.println("��ʼѰ����Ļ�ļ�");
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
			// System.out.println(fileName + "�ĺ�׺��" + ends);
			if (ends.equalsIgnoreCase("ass") || ends.equalsIgnoreCase("srt") || ends.equalsIgnoreCase("sub")) {// ����Ƶ�ļ��Ļ���ʼѭ���ж��ǲ�����Ƭ��
				// System.out.println(fileName + "����Ļ�ļ�");
				matches.add(files[i].getName());
			} else {
				// System.out.println(fileName + "������Ļ�ļ�");
			}
		}
		// System.out.println("ȫ����Ļ�ļ�Ϊ" + matches);
		return matches;
	}

	/** ��ָ��Ŀ¼������Ƭ,������Ƭ�����ֻ�null */
	public ArrayList<String> findLikelyVideo(String parentPath) {
		System.out.println("��ʼѰ����Ƶ�ļ�");
		File drc = new File(parentPath);
		File[] files = drc.listFiles();
		String[] formats = { "OP", "ED", "PV", "Menu", "Movie", "Song", "CM" };
		ArrayList<String> matches = new ArrayList<>();
		LOOP_0: for (int i = 0; i < files.length; i++) {
			// System.out.println("׼���ָ�"+files[i].toString());
			// String[] nodes=files[i].toString().split("\\",100);
			// System.out.println("��\\�ָ��ĳ���Ϊ"+nodes.length);
			// String tempName = nodes[nodes.length-1];
			String fileName = files[i].getName();
			// String tempName=files[i].toString();
			// System.out.println(fileName);
			String ends = fileName.substring(fileName.lastIndexOf(".") + 1);
			// System.out.println(fileName + "�ĺ�׺��" + ends);
			String endsBase = "mp4,mkv,rmvb";// ���и�ʽ����Ƶ��׺
			if (endsBase.indexOf(ends.toLowerCase()) > -1) {// ����Ƶ�ļ��Ļ���ʼѭ���ж��ǲ�����Ƭ��
				for (String format : formats) {// �ܼ�ֵ�ѭ�������Ļ���ϸ�
					if (fileName.indexOf(format) > -1) {// �����������Ƭ�ؼ��֣���ôֱ���ж���һ���ļ��ˣ����������йؼ��ֲźϸ�
						// System.out.println(fileName + "������Ƭ");
						continue LOOP_0;
					}
				}
				getEnds_TextField().setText(ends);
			} else {
				// System.out.println(fileName + "����mp4��mkv����Ƶ�ļ�");
				continue;
			}
			matches.add(files[i].getName());
		}
		// System.out.println("ȫ����ƬΪ" + matches);
		return matches;
	}

	@Deprecated
	public static String findStart(String example) {
		int location = -1;
		String start = "�޷��Զ�ʶ��";
		int count = 0;
		String reg = null;
		int tempLocation;
		while (start.equals("�޷��Զ�ʶ��") && count < 25) {
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

	// ������
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
				if (i > epLocation.get(epLocation.size() - 1) + 1) {// �Ѿ����һλ���Ǽ������ˣ��������
					System.out.println("�Ѿ����һλ���Ǽ������ˣ��������");
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
						compare = a;// �ҵ���һ�������ֵ�ֵ
						System.out.println("compare=" + compare);
					} else {
						if (!a.equals(compare)) {// ����ҵ�������ֵ��֮ǰ�Ĳ�һ��
							System.out.println("thisChar=" + a);
							numberChanged = true;
							break;
						}
					}
				}
			}
			System.out.print("hasNumber & numberChanged=");
			System.out.println(hasNumber & numberChanged);// ����һ�еķ�һ�����ʾ����
			if (hasNumber & numberChanged) {
				epLocation.add(i);
				System.out.println(i + "�Ǽ�������λ��");
			}
		}
		return epLocation;
	}

	/** ��example���е��ļ�����ȡǰ׺ */
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
	/** �㿪ʼ�滻��ʼִ�� */
	public ArrayList<String> findPassedSubtitle(ArrayList<String> allSubs) {
		String abandonedLag = getAbandonedLag_TextField().getText();
		ArrayList<String> passedSubs = new ArrayList<>();
		if (abandonedLag.equals("")) {
			return allSubs;
		} else {
			if (abandonedLag.indexOf("&") > -1) {
				String[] filters = abandonedLag.split("&");
				for (String tempSub : allSubs) {// ��ʼ��ÿ����Ļ�ļ����м��
					Boolean thisSubIsOK = true;
					for (int i = 0; i < filters.length; i++) {
						if (tempSub.toLowerCase().indexOf(filters[i]) > -1) {
							if (getDel_check().isSelected()) {// ���ֱ��ɾ���ĸ�ѡ��ѡ��
								File abandenedSub = new File(getSubtitlePath_TextField().getText() + tempSub);
								abandenedSub.delete();// ��ͨ�������ɾ��
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
				while (iterator.hasNext()) {// ��ʼ��ÿ����Ļ�ļ����м��
					String tempSub = iterator.next();
					if (tempSub.indexOf(filter) > -1) {
						if (getDel_check().isSelected()) {// ���ֱ��ɾ���ĸ�ѡ��ѡ��
							File abandenedSub = new File(getSubtitlePath_TextField().getText() + tempSub);
							Boolean good = abandenedSub.delete();// ��ͨ�������ɾ��
							System.out.println(tempSub + "�ļ�������" + abandenedSub.exists());
							System.out.println(tempSub + "�ļ�����" + filter + "ɾ���ɹ�����" + good);
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
		String subEnds;// ��Ļ�ļ��ĸ�ʽ������.
		String exampleSub = getExampleSelectBox_1().getSelectedItem().toString();
		if (getEnds_TextField_1().getText().equals("")) {// ��׺λ��Ϊ��ʱ��Ĭ��λ��
			subEnds = exampleSub.substring(exampleSub.lastIndexOf("."));// ��ȡ��Ļ�ļ���׺������.ass
		} else {
			int dotCount = Integer.parseInt(getEnds_TextField_1().getText());
			ArrayList<Integer> indexes = getIndexesOfReg(exampleSub, ".");
			if (dotCount > indexes.size()) {
				JOptionPane.showMessageDialog(MainFrame.this, "��Ļ�ļ���û����ô��С���㣡�Ѹĳ�����λ��", "��������",
						JOptionPane.ERROR_MESSAGE);
				dotCount = indexes.size();
				getEnds_TextField_1().setText(String.valueOf(indexes.size()));
			}
			int fromindex = indexes.get(indexes.size() - dotCount);
			subEnds = exampleSub.substring(fromindex);
			System.out.println("��Ļ�ļ���׺" + subEnds);
		}
		return subEnds;
	}

	public void renameBegin() {
		System.out.println("ͬ·����" + getSameDrc_check().isSelected());
		String videoStrat = getPrev_TextField().getText();
		String subStart = getPrev_TextField_1().getText();
		String subEnds = getSubEnds();
		int indexAtvideo = videoStrat.length();
		int indexAtSub = subStart.length();// number����Ļ�ļ��е�λ��
		String numberAtSub;// ��Ļ�ļ��е�number��
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
		System.out.println("ȫ����Ƶ�ļ���\n" + allVideo);
		System.out.println("ȫ����Ļ�ļ���\n" + allSubs);
		for (String tempSub : allSubs) {// ������ȡ��Ļ
			if (tempSub.startsWith(subStart)) {
				numberAtSub = tempSub.substring(indexAtSub, indexAtSub + getSubEpLocation().size());
				for (String tempVideo : allVideo) {// ��Ƶδ�ҵ�ʱ������
					if (tempVideo.startsWith(videoStrat)) {
						numberAtVideo = tempVideo.substring(indexAtvideo, indexAtvideo + getVideoEpLocation().size());
						if (numberAtVideo.equals(numberAtSub)) {// �����������
							nameOfVideo = tempVideo.substring(0, tempVideo.lastIndexOf("."));
							nameForSub = nameOfVideo + subEnds;
							oldSubFile = new File(getSubtitlePath_TextField().getText() + tempSub);
							newSubFile = new File(getVideoPath_TextField().getText() + nameForSub);
							if (newSubFile.exists()) {
								if (asked == false) {// ����Ƶ�ļ����´��������ļ�ʱ�����ж��Ƿ�Ҫɾ�����ļ�
									String message = "���Խ�\n" + oldSubFile + "\n��������\n" + newSubFile
											+ "\nʱ������Ƶ�ļ����´���ͬ���ļ���\n�Ƿ�ɾ����Ƶ�ļ�����ԭ�е�ͬ���ļ�";
									int option = JOptionPane.showConfirmDialog(MainFrame.this, message, "����ͬ���ļ�",
											JOptionPane.YES_NO_CANCEL_OPTION);
									asked = true;
									if (option == JOptionPane.YES_OPTION) {
										deleteOldSameSub = true;// ɾ���ɵ�
									} else if (option == JOptionPane.NO_OPTION) {
										deleteOldSameSub = false;// ��ɾ������������ʧ��
									} else {
										return;// ��������
									}
								}
								if (deleteOldSameSub) {
									newSubFile.delete();
								}
							}
							Boolean success = oldSubFile.renameTo(newSubFile);
							System.out.println("�������ɹ�����" + success);
							if (success) {
								count++;
							}
							break;
						}
					}
				}
			}
		}
		JOptionPane.showMessageDialog(this, "�ܹ���������" + count + "����Ļ�ļ�\nû��ɵĻ������޸���������", "���������",
				JOptionPane.PLAIN_MESSAGE);
	}
}
