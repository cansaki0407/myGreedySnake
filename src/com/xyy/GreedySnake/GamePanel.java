package com.xyy.GreedySnake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

/**
 * @author 皮皮瑶
 * @proname
 * @data 2022/4/22- 12:31
 */
public class GamePanel extends JPanel implements ActionListener,KeyListener {

	int[] snakeX = new int[500];	//可以储存的蛇X坐标上限
	int[] snakeY = new int[500	];	//可以储存的蛇Y坐标上限
	static int firstLength = 3;//蛇的初始长度（不可变）
	int length; //蛇的长度(可变)
	String direction;//蛇头的移动方向
	boolean isRun = false;//游戏运行状态 正在运行true 暂停运行false
	//定义一个食物随机出现位置（坐标位置使用随机Random函数组合）
	int foodX;
	int foodY;
	Random random = new Random();
	boolean isFail; //游戏是否失败 true 失败了 false 未失败
	int score; //设置关卡累计积分
	int Checkpoints ;//关卡数
	//设置围墙给第二关使用
	int wallX1 = 50;
	int wallX2 = 1050;
	int wallY1 = 100;
	int wallY2 = 850;


	Timer timer = new Timer(50,this);

	GamePanel(){
		init();
		//获取键盘的监听事件
		timer.start();
		this.setFocusable(true);
		this.addKeyListener(this);
	}

	void init(){
		snakeX[0] = 150;snakeY[0] = 125;//蛇头的初始坐标位置
		snakeX[1] = 125;snakeY[1] = 125;//蛇身第一节的初始位置
		snakeX[2] = 100;snakeY[2] = 125;//蛇身第二节的初始位置
		length = firstLength;//蛇的初始长度
		direction = "R";
		foodX = 25 + 25 * random.nextInt(40);
		foodY = 100 + 25 * random.nextInt(30);
		isFail = false;
	}



	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.white);
		g.fillRect(50, 100, 1000, 750);  // 设置矩形游戏区域

		g.setFont(new Font("黑体",Font.BOLD,50));
		g.drawString("pp贪吃蛇v1.0内测版", 50, 60);

		//画一条静态的蛇
		//蛇头
		if (direction.equals("R")){
			Data.headRight.paintIcon(this, g, snakeX[0], snakeY[0]);
		}else if(direction.equals("L")){
			Data.headLeft.paintIcon(this, g, snakeX[0], snakeY[0]);
		}else if(direction.equals("U")){
			Data.headUp.paintIcon(this, g, snakeX[0], snakeY[0]);
		}else if(direction.equals("D")){
			Data.headDown.paintIcon(this, g, snakeX[0], snakeY[0]);
		}



		//蛇身
		for (int i = 1; i < length;i++){
			Data.body.paintIcon(this, g, snakeX[i],snakeY[i] );
		}

		//设置游戏开始暂停功能
		if(!isRun){
			//画文字
			g.setColor(Color.yellow);
			g.setFont(new Font("宋体",Font.BOLD,60));
			g.drawString("按空格键开始游戏", 300, 400);
			//画关卡选择提示栏
			g.setColor(Color.green);
			g.setFont(new Font("黑体",Font.BOLD,30));
			g.drawString("按键1：选择第一关（无围墙）",300 , 450);
			g.drawString("按键2：选择第二关（有围墙）",300 , 500);
		}

		//设置游戏结束功能（蛇发生碰撞除食物之外的障碍）
		if(isFail){
			//画文字
			g.setColor(Color.red);
			g.setFont(new Font("楷体",Font.BOLD,50));
			g.drawString("游戏失败了，请按空格键重新开始", 150, 400);
		}

		//画食物
		Data.food.paintIcon(this, g, foodX, foodY);

		//画积分栏
		g.setColor(Color.pink);
		g.setFont(new Font("黑体",Font.BOLD,30));
		g.drawString("积分："+score, 800, 50);


	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(isRun && !isFail){
			for (int i = length - 1;i > 0;i--){
				snakeX[i] = snakeX[i-1];
				snakeY[i] = snakeY[i-1];
			}
			if (direction.equals("R")){
				snakeX[0] = snakeX[0] + 25;
				if (snakeX[0] == 1050){
					snakeX[0] = 50;
				}
			}else if (direction.equals("L")){
				snakeX[0] = snakeX[0] - 25;
				if (snakeX[0] == 25){
					snakeX[0] = 1025;
				}
			}else if (direction.equals("U")){
				snakeY[0] = snakeY[0] - 25;
				if (snakeY[0] == 75){
					snakeY[0] = 825;
				}
			}else if (direction.equals("D")){
				snakeY[0] = snakeY[0] + 25;
				if (snakeY[0] == 850){
					snakeY[0] = 100;
				}
			}
			//蛇吃到食物(蛇头坐标是否与食物重合)
			if(snakeX[0] == foodX && snakeY[0] == foodY){
				foodX = 25 + 25 * random.nextInt(40);
				foodY = 100 + 25 * random.nextInt(30);
				length++;
				score = (length-firstLength)*10;
			}
			//碰撞到自己的身体，游戏结束
			for (int i = 1;i < length ;i++ ){
				if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]){
					isFail = !isFail;
				}
			}

			//碰到围墙，游戏结束
			if(Checkpoints == 2){
				if(snakeX[0] == wallX1 || snakeX[0] == wallX2 || snakeY[0] == wallY1 || snakeY[0] == wallY2){
					isFail = !isFail;
				}
			}
			repaint();
		}

	}



	@Override
	public void keyPressed(KeyEvent e) {
		//获取键盘按下去的是哪个按键
		int keyCode = e.getKeyCode();

		if(keyCode == KeyEvent.VK_SPACE){
			if(isFail == true){
				isFail = !isFail;
				init();
			}
			isRun = !isRun;
			repaint();
		}

		if (keyCode == KeyEvent.VK_1){
			Checkpoints = 1;
			isRun = !isRun;
			repaint();
		}

		if(keyCode == KeyEvent.VK_2){
			Checkpoints = 2;
			isRun = !isRun;
			repaint();
		}

		if (keyCode == KeyEvent.VK_RIGHT){
			direction = "R";
		}else if(keyCode == KeyEvent.VK_LEFT){
			direction = "L";
		}else if(keyCode == KeyEvent.VK_UP){
			direction = "U";
		}else if(keyCode == KeyEvent.VK_DOWN){
			direction = "D";
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}
