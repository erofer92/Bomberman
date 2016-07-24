package codes;

import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Wall {
	
	private JLabel jlabel;
	private ImageIcon img;
	private Rectangle rectangle;
	
	public Wall(int x, int y, ImageIcon img){
		this.img = img;
		this.jlabel = new JLabel();
		this.rectangle = new Rectangle(x, y, this.img.getIconWidth(), this.img.getIconHeight());
		this.jlabel.setBounds(this.rectangle);
		this.jlabel.setIcon(img);
	}
	
	public JLabel getJLabel(){
		return this.jlabel;
	}
	
	public Rectangle getRectangle(){
		return this.rectangle;
	}
}
