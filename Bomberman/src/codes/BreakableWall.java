package codes;

import javax.swing.ImageIcon;

public class BreakableWall extends Wall{

	public BreakableWall(int x, int y, ImageIcon img) {
		super(x, y, img);
	}
	
	public Item breakWall(){
		return new Item();
	}
	 
}
