package codes;

import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Wall{
	
	private ImageIcon img;
	private JLabel jlabel;
	private Rectangle rectangle;
	
	Wall(int x, int y){
		this.rectangle = new Rectangle();
		this.rectangle.setBounds(x, y, 50, 50);
	}

	public void setRectangle(Rectangle rectangle){
		this.rectangle = rectangle;
	}
	
	public Rectangle getRectangle(){
		return rectangle;
	}
	
	public ImageIcon getImg(){
		return img;
	}

	public void setImg(ImageIcon img){
		this.img = img;
	}

	public JLabel getJLabel(){
		return jlabel;
	}

	public void setJLabel(JLabel jlabel){
		this.jlabel = jlabel;
	}
	
	
}
