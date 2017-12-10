#include<stdio.h>
#include"Rank.h"

//从文件读取玩家分数
void ReadScore(Score *aScore)
{
	FILE *fp = fopen("score.dat","rb");/*fopen()为打开文件的函数，第一个参数为文件名，第二个参数为打开模式（rb,wb,ab+等几种方式）
									   ，其中r只读，w只写，r+和w+可读写，a+以附加方式打开，后面加上一个b（binary即二进制）代表打开的是二进制文件*/
		if(fp == NULL)//如果为NULL，代表打开文件失败，反之打开成功
		{
			return ;
		}
		if(fread(aScore,sizeof(Score),10,fp)==0)/*fread()为读取文件的函数，第一个参数为读取出来的地址，第二个参数为一次性读取的字节长度，
												第三个参数是读取的次数，第四个参数是文件指针。返回的是读取出来的字节长度，
												若为0则读取失败，反之则读取成功*/
		{
			return ;
		}
		fclose(fp);/*fclose()为关闭文件的函数，只有一个参数，是文件指针，返回一个整形变量，若为0则文件成功关闭，若为EOF则关闭失败*/

		fp = NULL;
}

//将玩家分数写入文件
void WriteScore(Score *aScore)
{
	FILE *fp = fopen("score.dat","wb");//定义文件指针，以只写权限打开
	if(fp == NULL)
	{
		return ;
	}
	if(fwrite(aScore,sizeof(Score),N,fp) == 0)/*fwrite()为写入文件的函数，第一个参数是写入进去的地址，第二个参数为一次性写入的字节长度
											  ，第三个参数是写入的次数，第四个参数是文件指针。返回的是实际写入文件的字节长度，
											  若为0则为写入失败，反之写入成功*/
	{
		return ;
	}//写入文件
	fclose(fp);//关闭文件
	fp = NULL;
}
	
