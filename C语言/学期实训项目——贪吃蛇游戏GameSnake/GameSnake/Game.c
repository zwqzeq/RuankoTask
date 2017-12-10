#include"Game.h"
#include"malloc.h"
#include<stdio.h>
#include<conio.h>
#include<time.h>
#include<Windows.h>
#include"UI.h"
#include"Rank.h"

void GamePlay()
{   
	int iScore = 0;//玩家分数变量初始化
	int iSpeed = 200;//游戏速度变量初始化
	int bRunning = TRUE;
	struct SNAKE* pSnake = (struct SNAKE *)malloc(sizeof(struct SNAKE));//开辟一段内存空间，用于存放蛇的属性（蛇的身体位置，蛇的运动方向，蛇的长度）
	Food* pFood = (Food*)malloc(sizeof(Food));//开辟一段内存空间，用于存放食物的位置
	struct SCORE aScore[N];//定义结构体数组，存放10个玩家的姓名和得分
	int iX;//记录位置
	int iY;//记录位置	
	int i = 0;
	int j = 0;
	memset(aScore,NULL,sizeof(Score)*N);//初始化分数结构体数组
	InitSnake(pSnake);//对蛇初始化
	DrawSnake(pSnake);//游戏开始时画蛇
	Sleep(iSpeed);
	MakeFood(pFood,pSnake);//生成食物
	while(bRunning)
	{
		KeyPress(pSnake);//获取按键信息
		Move(pSnake);            /*移动头结点*/
		iX = pSnake->pHead->iX;
		iY = pSnake->pHead->iY;
		if(iX < 0 || iY < 0|| iX > WIDTH -1 || iY > HEIGHT - 1 || CheckBody(iX,iY,pSnake))
		{//判断蛇的状态，如果蛇撞墙，游戏结束
			bRunning=FALSE;//提示游戏结束
			Location(22,10);
			printf("游戏结束\n");
		}
		else
		{
			if(iX == pFood->iX && iY ==pFood->iY )//如果蛇活着，判断是否吃到食物
			{
				MakeFood(pFood,pSnake);//如果吃到了就生成新的食物
				Location(5,1);
				printf("%03d",++iScore);
				if(iScore % 10 == 0)//每增加十分，速度加一
				{
					Location(14,1);
					iSpeed = iSpeed - 20;
					printf("%02d",(220 - iSpeed)/20);
				}
			}
			else
			{
				RemoveNode(pSnake);
			}
			DrawSnake(pSnake);//游戏运行时画蛇
		}
		Sleep(iSpeed);
	}
	free(pFood);//释放食物
	EndGame(pSnake);
	getch();//读取分数，把分数存储到数组中去
	ReadScore(aScore);
	for(i = 0;i < N;i++)//比较分数和排行榜上的分数
		{
			if(iScore > aScore[i].iScore)
			{
				for(j = N - 2;j >= i;j--)
				{
					strcpy(aScore[i + 1].aName,aScore[i].aName);
					aScore[i + 1].iScore =aScore[i].iScore;
				}
				system("cls");//清屏
				SwitchCursor(TRUE);//显示光标
				printf("请输入玩家姓名：\n");
				gets(aScore[i].aName);
				aScore[i].iScore = iScore;
				SwitchCursor(FALSE);
				break;
			}
	    }
	WriteScore(aScore);//写入文件，保存更新后的排行榜
}

/**
*对蛇初始化（蛇初始运动方向向右，蛇身长度为3）
*/
void InitSnake(Snake* pSnake)
{   
	int i = 0;
	pSnake->iLen = LENGTH;//起始长度为3节
	pSnake->eDir = RIGHT;//起始方向向右
	pSnake->pHead = NULL;//起始位置设为空，用下面的语句来控制蛇的位置

	for(i = 0;i<LENGTH;i++)
	{
		AddNode(i+3,3,pSnake);//node为节点
	}
}

/**
 *增加蛇的长度
 */
void AddNode(int iX,int iY,Snake* pSnake)
{
	//为蛇的身体位置变量开辟内存空间
	Body* pNode = (Body*)malloc(sizeof(Body));/*malloc()函数，作用:malloc向系统申请分配指定size个字节的内存空间。
											  返回类型是void*类型。void*表示未指定类型的指针。c,c++规定，void*类型
											  可以强制转换为任何其他类型的指针*/
	pNode->iX = iX;
	pNode->iY = iY;

	pNode->next = pSnake->pHead;
	pSnake->pHead = pNode;
}

/**
 *结束游戏
 */
void EndGame(Snake* pSnake)
{
	Snake* pNode;
	while(pSnake->pHead != NULL)
	{
		pNode = pSnake->pHead;
		pSnake->pHead = pSnake->pHead->next;
		free(pNode);
	}
	free(pSnake);//释放
}

/**
 *移动
 */
void Move(Snake* pSnake)
{
	int iX = pSnake->pHead->iX;
	int iY = pSnake->pHead->iY;
	switch(pSnake->eDir)
	{
	case UP:
		{
			AddNode(iX,iY - 1,pSnake);
			break;
		}
	case DOWN:
		{
			AddNode(iX,iY + 1,pSnake);
			break;
		}
	case LEFT:
		{
			AddNode(iX - 1,iY,pSnake);
			break;
		}
	case RIGHT:
		{
			AddNode(iX + 1,iY,pSnake);
			break;
		}
	}
}

/**
 *移动节点
 */
void RemoveNode(Snake* pSnake)
{
	Body* pNode = pSnake->pHead;
	Body* pTempNode;
	//遍历得到链表尾节点
	while(pNode->next != NULL)
	{
		pTempNode = pNode;
		pNode = pNode->next;
	}
	//擦除尾节点
	Location(pNode->iX * 2 + 1,pNode->iY + 3);
	printf("  ");
	//删除尾节点
	pTempNode->next = NULL;
	free(pNode);//释放
}

/*
 *监控键盘，接收键盘信息，和判断按键内容，移动蛇
 */
void KeyPress(struct SNAKE* pSnake)//形参pSnake指向蛇的属性类型的结构体变量
{
	int iInput = 0;
	if(kbhit())//kbhit()函数，作用：用于监控按键信号，其中kb即keyboard（键盘）
	{
		//疑问：为什么不用getchar（）函数呢？
		iInput = getch();//getch()函数，作用：用于接收用户从键盘输入的一个字符
		switch(iInput)//判断按键信息
		{
		case 'a':
		case 'A':
			{     //向左，因为贪吃蛇不能反向移动，所以加一个判断
				if(pSnake->eDir != RIGHT)
				{     //如果此时蛇的运动状态不是向右移动，那么按了向左之后就可以向左转向
					pSnake->eDir = LEFT;
				}
				break;
			}
		case 'd':
		case 'D':
			{
				if(pSnake->eDir != LEFT)
				{ //如果此时蛇的运动状态不是向左移动，那么按了向右之后就可以向右转向
					pSnake->eDir = RIGHT;
				}
				break;
			}
		case 'w':
		case 'W':
			{
				if(pSnake->eDir != DOWN)
				{ //如果此时蛇的运动状态不是向下移动，那么按了向上之后就可以向上转向
					pSnake->eDir = UP;
				}
				break;
			}
		case 's':
		case 'S':
			{
				if(pSnake->eDir != UP)
				{ //如果此时蛇的运动状态不是向上移动，那么按了向下之后就可以向下转向
					pSnake->eDir = DOWN;
				}
				break;
			}
		case ' '://空格
			{
				system("pause>>null");//游戏暂停
				break;
			}
		}
	}
				 
}

/**
 * 生成食物
 */
void MakeFood(Food* pFood,Snake* pSnake)
{
	
	int iX = rand() % WIDTH;/*rand函数(),作用：生成随机数，rand()%N(其中N是正整数)
						，如果是这种形式，就代表生成的随机范围在0~N之间*/
	int iY = rand() % HEIGHT;
	while(CheckBody(iX,iY,pSnake))/*遍历链表检查食物是否在蛇身上，当在身上*/
	{
		iX = rand() % WIDTH;
		iY = rand() % HEIGHT;
	
	} 
	pFood->iX = iX;
	pFood->iY = iY;

	Location(pFood->iX * 2 + 1,pFood->iY + 3);
	printf("①");
}

/**
*遍历链表，判断传入的横坐标是否在链表上
*/
int CheckBody(int iX,int iY,Snake* pSnake)
{
	Body* pNode = pSnake->pHead->next;
	while(pNode != NULL)
	{
		if(pNode->iX == iX && pNode->iY == iY)/*如果传入的横纵坐标与pNode相同则返回TRUE*/
		{
			return TRUE;
		}
		pNode = pNode->next;//pNode后移一位
	}
	return FALSE;/*否则返回FALSE*/
}



