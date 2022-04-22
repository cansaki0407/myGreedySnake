package com.xyy.GreedySnake;

import javax.swing.*;

/**
 * @author 皮皮瑶
 * @proname
 * @data 2022/4/22- 0:19
 * @游戏主函数 启动
 */
public class GameStarter {
	public static void main(String[] args) {
		//1.绘制游戏的背景框
		JFrame jFrame = new JFrame("xyy贪吃蛇1.0");
		jFrame.setBounds(500, 50, 1100, 900); //绘制游戏界面布局的初始大小
		jFrame.setResizable(false);//设置窗口的大小是否可以拉伸改变
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置游戏可关闭功能 关了游戏程序即停止

		//2.添加画板
		jFrame.add(new GamePanel());


		jFrame.setVisible(true);//设置界面是否显现

	}
}
