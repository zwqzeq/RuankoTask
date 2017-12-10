#ifndef UI_H//防止头文件重定义
#define UI_H//防止头文件重定义
#include<stdio.h>
#include<windows.h>
#include<conio.h>
#include"Game.h"

//函数声明部分
void MainMenu();//主菜单
void StartGame();//开始游戏界面
void Ranking();//排行榜
void Introduce();//操作说明
void SwitchCursor(int iFlag);//隐藏光标
void Location(int iX,int iY);//蛇的位置
void DrawSnake(Snake* pSnake);//画蛇
#endif//防止头文件重定义