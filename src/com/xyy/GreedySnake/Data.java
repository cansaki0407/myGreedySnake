package com.xyy.GreedySnake;

import javax.swing.*;
import java.net.URL;

/**
 * @author 皮皮瑶
 * @proname
 * @data 2022/4/22- 15:09
 */
public class Data {
	//URL : 定位图片的位置  	imageIcon : 图片
	//头部向右
	public static URL headRightUrl = Data.class.getResource("/resources/img/right.png");
	public static ImageIcon headRight = new ImageIcon(headRightUrl);
	//头部向左
	static URL headLeftUrl = Data.class.getResource("/resources/img/left.png");
	static ImageIcon headLeft = new ImageIcon(headLeftUrl);
	//头部向上
	static URL headUpUrl = Data.class.getResource("/resources/img/up.png");
	static ImageIcon headUp = new ImageIcon(headUpUrl);
	//头部向下
	static URL headDownUrl = Data.class.getResource("/resources/img/down.png");
	static ImageIcon headDown = new ImageIcon(headDownUrl);
	//身体
	static URL bodyUrl = Data.class.getResource("/resources/img/body.png");
	static ImageIcon body = new ImageIcon(bodyUrl);
	//食物
	static URL foodUrl = Data.class.getResource("/resources/img/food.png");
	static ImageIcon food = new ImageIcon(foodUrl);

}
