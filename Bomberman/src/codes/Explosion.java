package codes;

import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Explosion{
	
	private Rectangle rectangle;
	private ImageIcon topExplosion;
	private ImageIcon bottomExplosion;
	private ImageIcon leftExplosion;
	private ImageIcon rightExplosion;
	private ImageIcon verticalExplosion;
	private ImageIcon horizontalExplosion;
	private ImageIcon centerExplosion;
	private JLabel topExplosionJLabel;
	private JLabel bottomExplosionJLabel;
	private JLabel leftExplosionJLabel;
	private JLabel rightExplosionJLabel;
	private JLabel verticalExplosionJLabel;
	private JLabel horizontalExplosionJLabel;
	private JLabel centerExplosionJLabel;
	private int explosionRange;

	Explosion(int x, int y){
		this.rectangle = new Rectangle(x, y, 50, 50);
		this.explosionRange = 50;
		this.topExplosion = new ImageIcon(getClass().getResource("topExplosion.png"));
		this.bottomExplosion = new ImageIcon(getClass().getResource("bottomExplosion.png"));
		this.leftExplosion = new ImageIcon(getClass().getResource("leftExplosion.png"));
		this.rightExplosion = new ImageIcon(getClass().getResource("rightExplosion.png"));
		this.verticalExplosion = new ImageIcon(getClass().getResource("verticalExplosion.png"));
		this.horizontalExplosion = new ImageIcon(getClass().getResource("horizontalExplosion.png"));
		this.centerExplosion = new ImageIcon(getClass().getResource("centerExplosion.png"));
		this.topExplosionJLabel = new JLabel(topExplosion);
		this.bottomExplosionJLabel = new JLabel(bottomExplosion);
		this.leftExplosionJLabel = new JLabel(leftExplosion);
		this.rightExplosionJLabel = new JLabel(rightExplosion);
		this.verticalExplosionJLabel = new JLabel(verticalExplosion);
		this.horizontalExplosionJLabel = new JLabel(horizontalExplosion);
		this.centerExplosionJLabel = new JLabel(centerExplosion);
		
	}
	
	public JLabel getTopExplosionJLabel() {
		return topExplosionJLabel;
	}

	public void setTopExplosionJLabel(JLabel topExplosionJLabel) {
		this.topExplosionJLabel = topExplosionJLabel;
	}

	public JLabel getBottomExplosionJLabel() {
		return bottomExplosionJLabel;
	}

	public void setBottomExplosionJLabel(JLabel bottomExplosionJLabel) {
		this.bottomExplosionJLabel = bottomExplosionJLabel;
	}

	public JLabel getLeftExplosionJLabel() {
		return leftExplosionJLabel;
	}

	public void setLeftExplosionJLabel(JLabel leftExplosionJLabel) {
		this.leftExplosionJLabel = leftExplosionJLabel;
	}

	public JLabel getRightExplosionJLabel() {
		return rightExplosionJLabel;
	}

	public void setRightExplosionJLabel(JLabel rightExplosionJLabel) {
		this.rightExplosionJLabel = rightExplosionJLabel;
	}

	public JLabel getVerticalExplosionJLabel() {
		return verticalExplosionJLabel;
	}

	public void setVerticalExplosionJLabel(JLabel verticalExplosionJLabel) {
		this.verticalExplosionJLabel = verticalExplosionJLabel;
	}

	public JLabel getHorizontalExplosionJLabel() {
		return horizontalExplosionJLabel;
	}

	public void setHorizontalExplosionJLabel(JLabel horizontalExplosionJLabel) {
		this.horizontalExplosionJLabel = horizontalExplosionJLabel;
	}

	public JLabel getCenterExplosionJLabel() {
		return centerExplosionJLabel;
	}

	public void setCenterExplosionJLabel(JLabel centerExplosionJLabel) {
		this.centerExplosionJLabel = centerExplosionJLabel;
	}

}
