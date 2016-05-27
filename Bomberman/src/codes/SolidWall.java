package codes;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class SolidWall extends Wall{

	SolidWall(int x, int y){
		super(x, y);
		this.setImg(new ImageIcon(getClass().getResource("/images/solidWall.png")));
		this.setJLabel(new JLabel(this.getImg()));
		this.getJLabel().setBounds(this.getRectangle());
	}
}
