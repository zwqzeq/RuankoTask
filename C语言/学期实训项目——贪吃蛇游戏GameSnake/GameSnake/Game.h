#ifndef GAME_H//��ֹ�ظ�����
#define GAME_H
#include<stdio.h>//Ԥ����ָ�����ϵͳ�ṩ���������������scanf,printf�ȣ�ʱҪ��һ��

#define LENGTH 3  //����LENGTH����3
#define WIDTH 30   //���
#define HEIGHT 20   //�߶�

//ʹ��ö�����ͣ���Ϊ�ߵ��˶�����ֻ�����ֿ���
typedef enum DIRECTION//�ߵ��ж�����
{
	UP,//����
	DOWN,//����
	LEFT,//����
	RIGHT//����
}eDir;                                   //�ߵ��˶�����

//��������ṹ��
typedef struct BODY
{
	int iX;//������
	int iY;//������
	struct BODY *next;
}Body;                                    //�ߵ�λ��

//�����ߵ����Խṹ��
typedef struct SNAKE
{
	int iLen;//�ߵĳ���
	int eDir;//�˶�����
	Body *pHead;//�ߵ�����λ��
}Snake;                                   //�����ߵ����Ͻ��ܵ�����

//����ʳ��ṹ��
typedef struct FOOD
{
	int iX;//ʳ��ĺ�����
	int iY;//ʳ���������
}Food;                                  //ʳ���λ��

//������������
void GamePlay();//��ʼ��Ϸ�ĺ�������
void InitSnake(Snake* pSnake);
void AddNode(int iX,int iY,Snake* pSnake);//�����߳��Ⱥ�������
void EndGame(Snake* pSnake);//������Ϸ�ĺ�������
void Move(Snake* pSnake);
void RemoveNode(Snake* pSnake);
void KeyPress(Snake* pSnake);//��ȡ������Ϣ�ĺ�������
void MakeFood(Food* pFood,Snake* pSnake);//����ʳ��ĺ�������
int CheckBody(int iX,int iY,Snake* pSnake);

#endif