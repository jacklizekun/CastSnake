package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *	 配置文件
 * @version 1.0
 * 
 * @author 李泽坤
 * 
 */
public class Global {
	public static int defaultCellSize =  20;
	//单位格子的宽度
	public static final int CELL_WIDTH=defaultCellSize;
	//单位格子的高度
	public static final int CELL_HEIGHT=defaultCellSize;
	//宽（单位：格子数）
	public static final int WIDTH=35;
	//高（单位：格子数）
	public static final int HEIGHT=20;
	//像素宽度，即容器的宽
	public static final int CANVAS_WIDTH = WIDTH * CELL_WIDTH;
	//像素高度，即容器的高
	public static final int CANVAS_HEIGHT = HEIGHT * CELL_HEIGHT;
	//初始长度
	public static final int INIT_LENGTH=2;
	//速度
	public static final int SPEED=200;
	//速度增幅
	public static final int SPEED_STEP=25;
	//标题文字
	public static final String TITLE_LABEL_TEXT="坤坤提示您：";
	//说明文字
	public static final String INFO_LABEL_TEXT="方向键控制方向, 回车键暂停/继续\nPAGE UP, PAGE DOWN 加速或减速\n";

	
}
