#ifndef GAME_H//防止重复定义
#define GAME_H
#include<stdio.h>//预处理指令，调用系统提供的输入输出函数（scanf,printf等）时要这一句

#define LENGTH 3  //定义LENGTH代表3
#define WIDTH 30   //宽度
#define HEIGHT 20   //高度

//使用枚举类型，因为蛇的运动方向只有四种可能
typedef enum DIRECTION//蛇的行动方向
{
	UP,//向上
	DOWN,//向下
	LEFT,//向左
	RIGHT//向右
}eDir;                                   //蛇的运动方向

//定义蛇身结构体
typedef struct BODY
{
	int iX;//横坐标
	int iY;//纵坐标
	struct BODY *next;
}Body;                                    //蛇的位置

//定义蛇的属性结构体
typedef struct SNAKE
{
	int iLen;//蛇的长度
	int eDir;//运动方向
	Body *pHead;//蛇的身体位置
}Snake;                                   //关于蛇的以上介绍的属性

//定义食物结构体
typedef struct FOOD
{
	int iX;//食物的横坐标
	int iY;//食物的纵坐标
}Food;                                  //食物的位置

//函数声明部分
void GamePlay();//开始游戏的函数声明
void InitSnake(Snake* pSnake);
void AddNode(int iX,int iY,Snake* pSnake);//增加蛇长度函数声明
void EndGame(Snake* pSnake);//结束游戏的函数声明
void Move(Snake* pSnake);
void RemoveNode(Snake* pSnake);
void KeyPress(Snake* pSnake);//获取按键信息的函数声明
void MakeFood(Food* pFood,Snake* pSnake);//生成食物的函数声明
int CheckBody(int iX,int iY,Snake* pSnake);

#endif