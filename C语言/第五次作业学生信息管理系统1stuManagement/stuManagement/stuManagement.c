#define _CRT_SECURE_NO_WARNINGS//消除警告
#include<stdio.h>//预处理指令
#include<stdlib.h>//
#include<string.h>//在使用字符串处理函数时要写这一句     

int main()
{   int select=-1;//变量select用于存放用户输入的数字，这一句是给变量赋初值
   void print_star();//声明print_star函数
	void print_abcd();//声明print_abcd函数
		char num[11];//学号
		char name[32];//姓名
		char sex[1];//性别
		int age;//年龄
		float cscore;//c语言成绩
		float mscore;//数学成绩
		float escore;//英语成绩
void prin_abcd();//调用print_abcd函数
void prin_star();//调用print_star函数
	printf("************************\n");
	printf("学生信息管理系统\n");               //标题
	printf("************************\n");
	printf("\n");
	do
	{
	printf("**********菜单**********\n");       //菜单
	printf("1 添加学生信息\n");
	printf("2 显示学生信息\n");
	printf("3 查找学生信息\n");
	printf("4 保存学生信息\n");
	printf("5 读取学生信息\n");
	printf("0 退出系统\n");
	printf("************************\n");
	printf("请输入您的选择(0～5):");//提示用户输入数字，选择菜单
	scanf("%d",&select);
	printf("您选择的菜单项是%d\n",select);//提示用户选择的结果
	switch(select)
	{
	case 0:
		printf("您选择了退出系统菜单\n");
		break;
	case 1:
		printf("您选择了添加学生信息菜单\n");
		printf("\n");
	    print_star();
		printf("请输入姓名\n");
		scanf("%s",name);
		printf("请输入年龄\n");
		scanf("%d",&age);
		if(age<0||age>150)
	      {
		        printf("    年龄在0～150之间，请重新输入\n");
	            scanf("%d",&age);
	       }
		printf("请输入性别：\n");
		scanf("%s",sex);
		printf("请输入学号：\n");
		scanf("%s",num);
		printf("请输入c语言成绩：\n");
		scanf("%f",&cscore);
		printf("请输入数学成绩：\n");
		scanf("%f",&mscore);
		printf("请输入英语成绩：\n");
		scanf("%f",&escore);
		print_star();
		break;
	case 2:
		printf("您选择了显示学生信息菜单\n");
		printf("\n");
		print_star();//调用函数
	    print_abcd();//调用函数
		printf("                           ##########学生信息#########\n");
		printf("姓名：      年龄：    性别：    学号：              c语言成绩：      数学成绩：       英语成绩：\n");
		printf("%s\t%8d\t%s\t%s\t\t%5f\t%5f\t%5f\n",name,age,sex,num,cscore,mscore,escore);
		print_star();//调用函数
		break;
	case 3:
		printf("您选择了查找学生信息菜单\n");
		printf("\n");
		printf("请输入被查找学生的姓名：\n");
		break;
	case 4:
		printf("您选择了保存学生信息菜单\n");
		break;
	case 5:
		printf("您选择了读取学生信息菜单\n");
		break;
	default:
		printf("输入错误(0～5)!\n");
		break;
	 }
	}while(select!=0);//!=是关系运算符，!=0表示关系表达式为真
	system("pause");
	return 0;
}
void print_star()//定义 print_star函数输出横行星号，将信息框起来，使程序更美观
{
	    printf("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");//作用是输出一行星号
	        //输出星号后紧接着就输出换行
}
void print_abcd()   //定义print_abcd()函数，用于输出竖列星号，将信息框起来  使程序更美观 
{   int i;
	for(i=1;i<=5;i++)
	  {
		printf("*");
	    printf("                                                                                                 *");
		printf("\n");
	  }
}
