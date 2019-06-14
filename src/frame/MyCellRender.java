package frame;

import java.awt.*;

import javax.swing.*;

/**现在是没什么用的状态*/
@Deprecated
class MyCellRenderer implements ListCellRenderer<Object> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyCellRenderer() {
		super();
		//setOpaque(false);
	}

	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {

		// setText(value.toString());
		JLabel renderer = (JLabel) new DefaultListCellRenderer().getListCellRendererComponent(list, value, index,
				isSelected, cellHasFocus);
		Color background;
		Color foreground;//font-color

		// check if this cell represents the current DnD drop location
		JList.DropLocation dropLocation = list.getDropLocation();
		if (dropLocation != null && !dropLocation.isInsert() && dropLocation.getIndex() == index) {

			background = Color.green;
			foreground = Color.BLUE;

			// check if this cell is selected
		} else if (isSelected) {
			background = Color.ORANGE;
			foreground = Color.RED;

			// unselected, and not the DnD drop location
		} else {
			background = new Color(255,127,127);
			foreground = Color.BLACK;
		}
		list.setSelectionBackground(Color.white);
		//list.setBorder(null);
		renderer.setBackground(new Color(255,127,127));
		renderer.setOpaque(false);
		renderer.setBackground(background);
		renderer.setForeground(foreground);

		return renderer;
	}
}
