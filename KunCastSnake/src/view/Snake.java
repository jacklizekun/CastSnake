package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import listener.SnakeListener;
import config.Global;

/**
  * 贪吃蛇
 * 
 * @version 1.0
 * @author 李泽坤
 * 
 */
public class Snake {
	public static final int UP = 1;
	public static final int DOWN = -1;
	public static final int LEFT = 2;
	public static final int RIGHT = -2;
	//蛇身体的各个结点左标
	private LinkedList<Point> body = new LinkedList<Point>();
	//原有方向
	private int oldDirection;
	//下一方向
	private int newDirection;
	private Point head;
	private Point tail;
	private int speed;
	private boolean live;
	private boolean pause;
	private Set<SnakeListener> listeners = new HashSet<SnakeListener>();
	//头颜色
	public static final Color DEFAULT_HEAD_COLOR = new Color(0xcc0033);
	private Color headColor = DEFAULT_HEAD_COLOR;
	//身颜色
	public static final Color DEFAULT_BODY_COLOR = new Color(0xcc0033);
	private Color bodyColor = DEFAULT_BODY_COLOR;

	public void move() {
		
		if (oldDirection + newDirection != 0) oldDirection = newDirection;
		tail = (head = takeTail()).getLocation();
		head.setLocation(getHead());
		switch (oldDirection) {
		case UP:
			head.y--;
			if (head.y < 0)head.y = Global.HEIGHT - 1;
			break;
		case DOWN:
			head.y++;
			if (head.y == Global.HEIGHT)head.y = 0;
			break;
		case LEFT:
			head.x--;
			if (head.x < 0)head.x = Global.WIDTH - 1;
			break;
		case RIGHT:
			head.x++;
			if (head.x == Global.WIDTH)head.x = 0;
			break;
		}
		body.addFirst(head);
	}

	private class SnakeDriver implements Runnable {

		public void run() {
			while (live) {
				if (!pause) {
					move();
					for (SnakeListener l : listeners)l.snakeMoved();
				}
				try {
					Thread.sleep(speed);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void eatFood() {
		body.addLast(tail.getLocation());
		for (SnakeListener l : listeners)l.snakeEatFood();
	}
	public void changeDirection(int direction) {
		this.newDirection = direction;
	}
	public Point getHead() {
		return body.getFirst();
	}
	public Point takeTail() {
		return body.removeLast();
	}
	public int getLength() {
		return body.size();
	}
	public void begin() {
		new Thread(new SnakeDriver()).start();
	}
	public void reNew() {
		init();
		begin();
	}
	public void init() {
		body.clear();
		//初始位置在中间
		int x = Global.WIDTH / 2 - Global.INIT_LENGTH / 2;
		int y = Global.HEIGHT / 2;
		for (int i = 0; i < Global.INIT_LENGTH; i++)
			this.body.addFirst(new Point(x++, y));
		//初始方向为右
		oldDirection = newDirection = RIGHT;
		speed = Global.SPEED;
		live = true;
		pause = false;
	}

	public boolean isEatBody() {
		for (int i = 1; i < body.size(); i++)
			if (getHead().equals(body.get(i)))return true;
		return false;
	}

	public void drawMe(Graphics g) {
		for (Point p : body) {
			g.setColor(bodyColor);
			drawBody(g, p.x * Global.CELL_WIDTH, p.y * Global.CELL_HEIGHT,Global.CELL_WIDTH, Global.CELL_HEIGHT);
		}
		g.setColor(headColor);
		drawHead(g, getHead().x * Global.CELL_WIDTH, getHead().y* Global.CELL_HEIGHT, Global.CELL_WIDTH, Global.CELL_HEIGHT);
	}

	public void drawHead(Graphics g, int x, int y, int width, int height) {
		g.fill3DRect(x, y, width, height, true);
	}
	public void drawBody(Graphics g, int x, int y, int width, int height) {
		g.fill3DRect(x, y, width, height, true);
	}

	public synchronized void addSnakeListener(SnakeListener l) {
		if (l == null)
			return;
		this.listeners.add(l);
	}
	public synchronized void removeSnakeListener(SnakeListener l) {
		if (l == null)
			return;
		this.listeners.remove(l);
	}
	
	public Color getHeadColor() {
		return headColor;
	}
	public void setHeadColor(Color headColor) {
		this.headColor = headColor;
	}
	public Color getBodyColor() {
		return bodyColor;
	}
	public void setBodyColor(Color bodyColor) {
		this.bodyColor = bodyColor;
	}
	public void speedUp() {
		if (speed > Global.SPEED_STEP)
			speed -= Global.SPEED_STEP;
	}
	public void speedDown() {
		speed += Global.SPEED_STEP;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public boolean isLive() {
		return live;
	}
	public void setLive(boolean live) {
		this.live = live;
	}
	public void dead() {
		this.live = false;
	}
	public boolean isPause() {
		return pause;
	}
	public void setPause(boolean pause) {
		this.pause = pause;
	}
	public void changePause() {
		pause = !pause;
	}

}