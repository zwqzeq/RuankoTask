#ifndef UI_H//��ֹͷ�ļ��ض���
#define UI_H//��ֹͷ�ļ��ض���
#include<stdio.h>
#include<windows.h>
#include<conio.h>
#include"Game.h"

//������������
void MainMenu();//���˵�
void StartGame();//��ʼ��Ϸ����
void Ranking();//���а�
void Introduce();//����˵��
void SwitchCursor(int iFlag);//���ع��
void Location(int iX,int iY);//�ߵ�λ��
void DrawSnake(Snake* pSnake);//����
#endif//��ֹͷ�ļ��ض���