package codes;

import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Ground {
	private ImageIcon img;
	private JLabel jlabel;
	private Rectangle rectangle;
	
	Ground(int x, int y){
		this.img = new ImageIcon(getClass().getResource("/images/ground.png"));
		this.rectangle = new Rectangle();
		this.rectangle.setBounds(x, y, 50, 50);
		this.jlabel = new JLabel(img);
		this.jlabel.setBounds(this.rectangle);
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
