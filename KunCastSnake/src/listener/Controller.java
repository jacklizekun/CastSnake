package listener;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JLabel;

import view.Food;
import view.Ground;
import view.Snake;
import listener.GameListener;
import listener.SnakeListener;
import config.Global;
import view.Panel;

/**
 *  控制器
 * @version 1.0
 * @author 李泽坤
 * 
 */
public class Controller extends KeyAdapter implements SnakeListener {
	private Ground ground;
	private Snake snake;
	private Food food;
	//显示页面
	private Panel gamePanel;
	//提示信息
	private JLabel gameInfoLabel;
	private boolean playing;
	private int map;
	private Set<GameListener> listeners = new HashSet<GameListener>();

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() != KeyEvent.VK_Y && !playing)
			return;
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			if (snake.isPause()) {
				snake.changePause();
				for (GameListener l : listeners)
					l.gameContinue();
			}
			snake.changeDirection(Snake.UP);
			break;
		case KeyEvent.VK_DOWN:
			if (snake.isPause()) {
				snake.changePause();
				for (GameListener l : listeners)
					l.gameContinue();
			}
			snake.changeDirection(Snake.DOWN);
			break;
		case KeyEvent.VK_LEFT:
			if (snake.isPause()) {
				snake.changePause();
				for (GameListener l : listeners)
					l.gameContinue();
			}
			snake.changeDirection(Snake.LEFT);
			break;
		case KeyEvent.VK_RIGHT:
			if (snake.isPause()) {
				snake.changePause();
				for (GameListener l : listeners)
					l.gameContinue();
			}
			snake.changeDirection(Snake.RIGHT);
			break;
		case KeyEvent.VK_ENTER:
		case KeyEvent.VK_SPACE:
			snake.changePause();
			for (GameListener l : listeners)
				if (snake.isPause())
					l.gamePause();
				else
					l.gameContinue();
			break;
		case KeyEvent.VK_PAGE_UP:
			snake.speedUp();
			break;
		case KeyEvent.VK_PAGE_DOWN:
			snake.speedDown();
			break;
		case KeyEvent.VK_Y:
			if (!isPlaying())
				newGame();
			break;
		}

		if (gamePanel != null)
			gamePanel.redisplay(ground, snake, food);
		if (gameInfoLabel != null)
			gameInfoLabel.setText(getNewInfo());
	}

	public void snakeMoved() {
		if (food != null && food.isSnakeEatFood(snake)) {
			snake.eatFood();
			food.setLocation(ground == null ? food.getNew() : ground
					.getFreePoint());

		}
		else if (ground != null && ground.isSnakeEatRock(snake)) {
			stopGame();
		}
		if (snake.isEatBody())
			stopGame();
		if (gamePanel != null)
			gamePanel.redisplay(ground, snake, food);
		if (gameInfoLabel != null)
			gameInfoLabel.setText(getNewInfo());
	}

	public void newGame() {

		if (ground != null) {
			switch (map) {
			case 2:
				ground.clear();
				ground.generateRocks2();
				break;
			default:
				ground.init();
				break;
			}
		}
		playing = true;

		snake.reNew();
		for (GameListener l : listeners)
			l.gameStart();
	}

	public void stopGame() {
		if (playing) {
			playing = false;
			snake.dead();
			for (GameListener l : listeners)
				l.gameOver();
		}
	}
	public void pauseGame() {
		snake.setPause(true);
		for (GameListener l : listeners)
			l.gamePause();
	}
	public void continueGame() {
		snake.setPause(false);
		for (GameListener l : listeners)
			l.gameContinue();
	}

	public Controller(Snake snake, Food food, Ground ground,Panel gamePanel) {
		this.snake = snake;
		this.food = food;
		this.ground = ground;
		this.gamePanel = gamePanel;
		if (ground != null && food != null)
			food.setLocation(ground.getFreePoint());
		this.snake.addSnakeListener(this);
	}

	public Controller(Snake snake, Food food, Ground ground,
			Panel gamePanel, JLabel gameInfoLabel) {

		this(snake, food, ground, gamePanel);
		this.setGameInfoLabel(gameInfoLabel);

		if (gameInfoLabel != null)
			gameInfoLabel.setText(getNewInfo());
	}


	public String getNewInfo() {
		if (!snake.isLive())
			return "提示: 按 Y 开始新游戏 ";
		else
			return new StringBuffer().append("提示: ").append("速度 ").append(
					snake.getSpeed()).toString()
					+ " 毫秒/格";
	}

	public synchronized void addGameListener(GameListener l) {
		if (l != null)
			this.listeners.add(l);
	}

	public synchronized void removeGameListener(GameListener l) {
		if (l != null)
			this.listeners.remove(l);
	}

	public Snake getSnake() {
		return this.snake;
	}
	public Food getFood() {
		return this.food;
	}
	public Ground getGround() {
		return this.ground;
	}
	public void snakeEatFood() {
		System.out.println("吃到食物!");
	}

	public Panel getGamePanel() {
		return gamePanel;
	}
	public void setGamePanel(Panel gamePanel) {
		this.gamePanel = gamePanel;
	}

	public JLabel getGameInfoLabel() {
		return gameInfoLabel;
	}

	public void setGameInfoLabel(JLabel gameInfoLabel) {
		this.gameInfoLabel = gameInfoLabel;
		this.gameInfoLabel.setSize(Global.WIDTH * Global.CELL_WIDTH, 20);
		this.gameInfoLabel.setFont(new Font("宋体", Font.PLAIN, 12));
		gameInfoLabel.setText(this.getNewInfo());
	}

	public void setGround(Ground ground) {
		this.ground = ground;
	}

	public void setSnake(Snake snake) {
		this.snake = snake;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public int getMap() {
		return map;
	}

	public void setMap(int map) {
		this.map = map;
	}

	public boolean isPlaying() {
		return playing;
	}

	public void setPlaying(boolean playing) {
		this.playing = playing;
	}

	public boolean isPausingGame() {
		return snake.isPause();
	}

}
