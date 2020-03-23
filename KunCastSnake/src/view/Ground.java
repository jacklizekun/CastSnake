package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

import config.Global;


/**
 * 背景(含边界和网格)
 * @version 1.0
 * 
 * @author 李泽坤
 * 
 */
public class Ground {
	public Ground() {
		init();
	}
	//边界二维数组
	private boolean rocks[][] = new boolean[Global.WIDTH][Global.HEIGHT];
	//食物坐标
	private Point freePoint = new Point();
	//默认边界的 颜色
	public static final Color DEFAULT_ROCK_COLOR = new Color(0x666666);
	private Color rockColor = DEFAULT_ROCK_COLOR;
	//默认面板内网格的颜色
	public static final Color DEFAULT_GRIDDING_COLOR = Color.LIGHT_GRAY;
	private Color griddingColor = DEFAULT_GRIDDING_COLOR;

	private Random random = new Random();
	//是否画出网格
	private boolean drawGridding = false;

	//画出游戏页面
	public void init() {
		clear();
		generateRocks();
	}
	//清空，只画出边界
	public void clear() {
		for (int x = 0; x < Global.WIDTH; x++)
			for (int y = 0; y < Global.HEIGHT; y++)
				rocks[x][y] = false;
	}
	
	public void generateRocks() {
		for (int x = 0; x < Global.WIDTH; x++)
			rocks[x][0] = rocks[x][Global.HEIGHT - 1] = true;
		for (int y = 0; y < Global.HEIGHT; y++)
			rocks[0][y] = rocks[Global.WIDTH - 1][y] = true;
	}

	public void generateRocks2() {

		for (int y = 0; y < 6; y++) {
			rocks[0][y] = true;
			rocks[Global.WIDTH - 1][y] = true;
			rocks[0][Global.HEIGHT - 1 - y] = true;
			rocks[Global.WIDTH - 1][Global.HEIGHT - 1 - y] = true;
		}
		for (int y = 6; y < Global.HEIGHT - 6; y++) {
			rocks[6][y] = true;
			rocks[Global.WIDTH - 7][y] = true;
		}
	}
	//添加
	public void addRock(int x, int y) {
		rocks[x][y] = true;
	}

	public boolean isSnakeEatRock(Snake snake) {
		return rocks[snake.getHead().x][snake.getHead().y];
	}

	//食物坐标
	public Point getFreePoint() {
		do {
			freePoint.x = random.nextInt(Global.WIDTH);
			freePoint.y = random.nextInt(Global.HEIGHT);
		} while (rocks[freePoint.x][freePoint.y]);
		return freePoint;
	}
	public Color getRockColor() {return rockColor;}
	public void setRockColor(Color rockColor) {this.rockColor = rockColor;}
	public void drawMe(Graphics g) {
		for (int x = 0; x < Global.WIDTH; x++)
			for (int y = 0; y < Global.HEIGHT; y++) {
				if (rocks[x][y]) {
					g.setColor(rockColor);
					drawRock(g, x * Global.CELL_WIDTH, y * Global.CELL_HEIGHT,Global.CELL_WIDTH, Global.CELL_HEIGHT);
				} else if (drawGridding) {
					g.setColor(griddingColor);
					drawGridding(g, x * Global.CELL_WIDTH, y* Global.CELL_HEIGHT, Global.CELL_WIDTH,Global.CELL_HEIGHT);
				}
			}
	}

	public void drawRock(Graphics g, int x, int y, int width, int height) {g.fill3DRect(x, y, width, height, true);}
	public void drawGridding(Graphics g, int x, int y, int width, int height) {g.drawRect(x, y, width, height);}
	public Color getGriddingColor() {return griddingColor;}
	public void setGriddingColor(Color griddingColor) {this.griddingColor = griddingColor;}
	public boolean isDrawGridding() {return drawGridding;}
	public void setDrawGridding(boolean drawGridding) {this.drawGridding = drawGridding;}

}
