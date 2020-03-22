package game;

import java.awt.Image;

/**
 * @author 李泽坤
 * @version 2.0
 */
public class Cell {
	private int row;	//行
	private int col;	//列
	private Image image;//单元格图
	
	public Cell() {
	}
	public Cell(int row, int col, Image image) {
		super();
		this.row = row;
		this.col = col;
		this.image = image;
	}



	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public void moveRight(){
		col++;
	}
	
	public void moveLeft(){
		col--;
	}
	
	public void moveDown(){
		row++;
	}
	public void moveUp(){
		row--;
	}

	public String toString() {	
		return row+","+col;
	}
	
}








