package view;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

import config.Global;

/**
 *  食物
 * @version 1.0
 * @author 李泽坤
 */
public class Food extends Point {
	private Color color = new Color(0xcc0033);
	private Random random = new Random();
	public Food() {
		super();
	}
	public Food(Point p) {
		super(p);
	}
	public Point getNew() {
		Point p = new Point();
		p.x = random.nextInt(Global.WIDTH);
		p.y = random.nextInt(Global.HEIGHT);
		return p;
	}
	public boolean isSnakeEatFood(Snake snake) {
		return this.equals(snake.getHead());
	}
	public void drawMe(Graphics g) {
		g.setColor(color);
		drawFood(g, x * Global.CELL_WIDTH, y * Global.CELL_HEIGHT,Global.CELL_WIDTH, Global.CELL_HEIGHT);
	}
	public void drawFood(Graphics g, int x, int y, int width, int height) {
		g.fill3DRect(x, y, width, height, true);
	}
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
