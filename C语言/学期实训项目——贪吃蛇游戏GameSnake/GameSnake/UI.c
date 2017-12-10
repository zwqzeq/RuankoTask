#include"UI.h"
#include<conio.h>
#include<stdio.h>
#include<stdlib.h>
#include"Game.h"
#include"Rank.h"

void MainMenu()//打印主菜单函数
{
	system("cls");//清屏
	printf("\t\t**************************************************\n");
	printf("\t\t*                  贪吃蛇游戏                    *\n");
	printf("\t\t*                                                *\n");
	printf("\t\t*                 1.开始游戏                     *\n");
	printf("\t\t*                 2.排行榜                       *\n");
	printf("\t\t*                 3.操作介绍                     *\n");
	printf("\t\t*                 4.退出                         *\n");
	printf("\t\t*                                                *\n");
	printf("\t\t**************************************************\n");
}

//打印开始游戏的主界面函数
void StartGame()
{   
	system("cls");//清屏
	printf("\t\t\t贪吃蛇游戏\n");//标题
	printf("分数：00 速度：1\n");
	printf("**************************************************************\n");
	printf("*                                                            *\n");
	printf("*                                                            *\n");
	printf("*                                                            *\n");
	printf("*                                                            *\n");
	printf("*                                                            *\n");
	printf("*                                                            *\n");
	printf("*                                                            *\n");
	printf("*                     按任意键开始游戏                       *\n");
	printf("*                                                            *\n");
	printf("*                                                            *\n");
	printf("*                                                            *\n");
	printf("*                                                            *\n");
	printf("*                                                            *\n");
	printf("*                                                            *\n");
	printf("*                                                            *\n");
	printf("*                                                            *\n");
	printf("*                                                            *\n");
	printf("*                                                            *\n");
	printf("*                                                            *\n");
	printf("*                                                            *\n");
	printf("**************************************************************\n");
    getch();//调用getch()函数让控制台在贪吃蛇游戏背景这幅画面上暂停下来。
	Location(22,10);
	printf("                   ");
	
	GamePlay();//开始游戏函数
}

//排行榜函数
void Ranking()
{
	int i = 0;
	Score aScore[N];
	memset(aScore,NULL,sizeof(Score) * N);/*memset()是清空函数*/
	system("cls");//清屏
	printf("\t\t\t\t排行榜\n");//标题
	printf("\t\t---------------------------------------------------\n");
	printf("\t\t|名次 玩家姓名\t\t\t\t分数\t  |\n");
	ReadScore(aScore);//读取更新后的排行榜信息，保存到aScore这个数组中
	for(i = 0;i < N;i++)
	{
		printf("\t\t|%02d   name：%-20s\tscore：%03d|\n",i + 1,strlen(aScore[i].aName) == 0?("null"):(aScore[i].aName),aScore[i].iScore);
	}
	printf("\t\t---------------------------------------------------\n");
	getch();
}

//操作说明
void Introduce()
{
	system("cls");//清屏
	printf("\t\t\t\t操作说明\n");//标题
	printf("\t\t------------------\n");
	printf("\t\t按键操作说明\n");
	printf("\t\t1、按a或A向左移动\n");
	printf("\t\t2、按d或D向右移动\n");
	printf("\t\t3、按w或W向上移动\n");
	printf("\t\t4、按s或S向下移动\n");

	printf("\t\t------------------\n");
	printf("\t\t游戏注意事项\n");
	printf("\t\t1、没有设置通关分数\n");
	printf("\t\t2、最高分没有上限\n");

	printf("\t\t------------------\n");
	printf("\t\t排行榜说明\n");
	printf("\t\t1、游戏只收录了分数排行榜前十名\n");
	printf("\t\t2、只有玩家分数最少高于第十名才能进入榜单\n");
	printf("\t\t3、当有玩家进入排行榜前十名，最后一名自动掉出榜单\n");

	printf("\t\t------------------\n");
	printf("\t\t游戏结束说明\n");
	printf("\t\t1、贪吃蛇撞上四周的墙壁时，游戏结束\n");
	printf("\t\t2、贪吃蛇撞上自身时，游戏结束\n");

	printf("\t\t按任意键返回主菜单\n");
	getch();
}

void SwitchCursor(int iFlag)
{
	CONSOLE_CURSOR_INFO cursor_info = {1,iFlag};//设置隐藏光标模式
	SetConsoleCursorInfo(GetStdHandle(STD_OUTPUT_HANDLE),&cursor_info);//隐藏光标函数
}

//位置
void Location(int iX,int iY)
{
	COORD pos;
	HANDLE hOut;
	pos.X=iX;
	pos.Y=iY;
	hOut = GetStdHandle(STD_OUTPUT_HANDLE);
	SetConsoleCursorPosition(hOut,pos);//坐标跳转

}

//画蛇
void DrawSnake(Snake* pSnake)
{   
	Body* pNode = pSnake->pHead;
	Location(pNode->iX * 2 + 1,pNode->iY + 3);
	printf("¤");

	while(pNode->next != NULL)
	{
		pNode = pNode->next;
		Location(pNode->iX * 2 + 1,pNode->iY + 3);
		printf("●");
	}

}
