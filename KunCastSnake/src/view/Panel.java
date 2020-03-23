package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import config.Global;

/**
 *   游戏的显示界面
 * 
 * @version 1.0
 * 
 * @author 李泽坤
 * 
 */
public class Panel extends JPanel {

	private Image oimg;
	private Graphics og;

	public static final Color DEFAULT_BACKGROUND_COLOR = new Color(0xcfcfcf);
	private Color backgroundColor = DEFAULT_BACKGROUND_COLOR;

	public Panel() {
		this.setSize(Global.WIDTH * Global.CELL_WIDTH, Global.HEIGHT* Global.CELL_HEIGHT);
		this.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		this.setFocusable(true);
	}
	public synchronized void redisplay(Ground ground, Snake snake, Food food) {
		if (og == null) {
			oimg = createImage(getSize().width, getSize().height);
			if (oimg != null)
				og = oimg.getGraphics();
		}
		if (og != null) {
			og.setColor(backgroundColor);
			og.fillRect(0, 0, Global.WIDTH * Global.CELL_WIDTH, Global.HEIGHT* Global.CELL_HEIGHT);
			if (ground != null)ground.drawMe(og);
			snake.drawMe(og);
			if (food != null)food.drawMe(og);
			this.paint(this.getGraphics());
		}
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(oimg, 0, 0, this);
	}
	public Color getBackgroundColor() {
		return backgroundColor;
	}
	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

}
