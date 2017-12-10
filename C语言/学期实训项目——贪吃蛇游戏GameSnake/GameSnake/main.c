#include<stdio.h>
#include<stdlib.h>
#include "UI.h"

int main()
{  
    int select=0;//iKey变量用来存放用户输入的数字，此处是对变量初始化
	do
	{   
			SwitchCursor(0);
			MainMenu();                       //主菜单
			select = getch();
			switch(select)
			{
			case '1':
				{ 
					system("color 8F");//改变颜色的函数
					system("pause");
					StartGame();
					break;
				}
			case '2':
				{   
					system("color B0");//改变颜色的函数
					system("pause");
					Ranking();
					break;
				}
			case '3':
				{
					system("color E9");//改变颜色的函数
					system("pause");
					Introduce();
					break;
				}
			default :
				{   system("color A1");
					system("pause");
					select=0;
					break;
				}
			
			}
	}while(select);
	return 0;
}

 