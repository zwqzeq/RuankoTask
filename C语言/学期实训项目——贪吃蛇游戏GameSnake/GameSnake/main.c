#include<stdio.h>
#include<stdlib.h>
#include "UI.h"

int main()
{  
    int select=0;//iKey������������û���������֣��˴��ǶԱ�����ʼ��
	do
	{   
			SwitchCursor(0);
			MainMenu();                       //���˵�
			select = getch();
			switch(select)
			{
			case '1':
				{ 
					system("color 8F");//�ı���ɫ�ĺ���
					system("pause");
					StartGame();
					break;
				}
			case '2':
				{   
					system("color B0");//�ı���ɫ�ĺ���
					system("pause");
					Ranking();
					break;
				}
			case '3':
				{
					system("color E9");//�ı���ɫ�ĺ���
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

 