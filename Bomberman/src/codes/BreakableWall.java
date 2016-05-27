package codes;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class BreakableWall extends Wall {

	BreakableWall(int x, int y){
		super(x, y);
		this.setImg(new ImageIcon(getClass().getResource("/images/breakableWall.png")));
		this.setJLabel(new JLabel(this.getImg()));
		this.getJLabel().setBounds(this.getRectangle());
	}
}
