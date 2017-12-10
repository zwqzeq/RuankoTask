#define _CRT_SECURE_NO_WARNINGS//消除警告
#include<stdio.h>//预处理指令
#include<stdlib.h>//用到system函数的时候需要这一行
#include<string.h>//在使用字符串处理函数时要写这一句     
#define STU_MAX 50//最多允许输入50个学生信息
	typedef struct Student
   {	char num[16];//学号
		char name[32];//姓名
		char sex;//性别
		int age;//年龄
		float cscore;//c语言成绩
		float mscore;//数学成绩
		float escore;//英语成绩
   }Student;
int main()
{	    void print_menu();//菜单函数的声明
		void add(Student aStu[],int *nCount);//添加学生信息函数声明
		void show(Student aStu[],int nCount);//显示学生信息函数声明
	    void search(Student aStu[],int nCount);//查找学生信息函数声明
	    int select=-1;//变量select用于存放用户输入的数字，这一句是给变量赋初值
	    Student stu[STU_MAX];//学生信息（定义结构体数组）
		int count=0;
	    void print_star();//声明print_star函数
		void print_abcd();//声明print_abcd函数
	    printf("************************\n");
	    printf("学生信息管理系统\n");               //标题
	    printf("************************\n");
	    printf("\n");
	do
	{
		print_menu();//调用显示菜单的函数
	    scanf("%d",&select);
	    printf("您选择的菜单项是%d\n",select);//提示用户选择的结果
	    switch(select)
	  {
	    case 0:
		       printf("您选择了退出系统菜单\n");
		       break;
	    case 1:
			   print_star();//调用print_star函数
		       add(stu, &count);//调用添加学生信息的函数
		       print_star();//调用print_star函数
		       break;
	    case 2:
	  	       print_star();
	           show(stu,count);//调用显示学生信息的函数
		       print_star();
		       break;
	    case 3:
			   printf("您选择了查找学生信息菜单\n");
			   printf("\n");
			   print_star();
		       printf("请输入被查找学生的姓名：\n");
			   search(stu,count);//调用查找学生信息的函数
			   print_star();
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
   }    while(select!=0);//!=是关系运算符，!=0表示关系表达式为真
	    system("pause");
	    return 0;
}
void print_star()//定义 print_star函数输出横行星号，将信息框起来，使程序更美观
{
	   printf("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");//作用是输出一行星号
	        //输出星号后紧接着就输出换行
}
void print_abcd()   //定义print_abcd()函数，用于输出竖列星号，将信息框起来  使程序更美观 
{    
	   int i;
	   for(i=1;i<=5;i++)
	  {
		printf("*");
	    printf("                                                                                                 *");
		printf("\n");
	  }
}
void print_menu()//定义显示菜单的函数
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
}
void add(Student aStu[],int *nCount)//定义添加学生信息的函数
{   
	int index=*nCount;//学生信息添加到结构体数组的下标
	char ch='n';//y or n
	//对变量赋初始值，（m为男f为女）
	printf("\n**********添加学生信息***********\n");
	do
	{   
		printf("请输入学号：[不超过10个数]：");
		scanf("%s",aStu[index].num);
		strlen(aStu[index].num);
		if(strlen(aStu[index].num)>10)//对用户输入的数据进行判断
		   do
		    {
			  printf("输入错误，请重新输入：\n");
			  scanf("%s",aStu[index].num);
		    }while(strlen(aStu[index].num)>10);
		getchar();
		printf("请输入姓名：[32个字符以内]：");
		scanf("%s",aStu[index].name);
		strlen(aStu[index].name);
		if(strlen(aStu[index].name)>32)//对用户输入的数据进行判断
			do
			{
				printf("输入错误，请重新输入：\n");
				scanf("%s",aStu[index].name);
			}while(strlen(aStu[index].name)>32);
		getchar();
		printf("请输入性别：[m/f，m代表男，f代表女]：");
		scanf("%c",&aStu[index].sex);
		getchar();
		printf("请输入年龄：[0～150之间]：");
		scanf("%d",&aStu[index].age);
		getchar();
		if((aStu[index].age<0)||(aStu[index].age>150))//对用户输入的数据进行判断
		  do
			{   
				printf("输入错误，请重新输入！\n");
			    scanf("%d",&aStu[index].age);
			}while((aStu[index].age<0)||(aStu[index].age>150));
		printf("请输入c语言成绩：[0～150之间]：");
		scanf("%f",&aStu[index].cscore);
			if((aStu[index].cscore<0)||(aStu[index].cscore>150))//对用户输入的数据进行判断
		    do
			 {   
				 printf("输入错误，请重新输入！\n");
			     scanf("%f",&aStu[index].cscore);
			 }while((aStu[index].cscore<0)||(aStu[index].cscore>150));
		getchar();
		printf("请输入数学成绩：[0～150之间]：");
		scanf("%f",&aStu[index].mscore);
		if((aStu[index].mscore<0)||(aStu[index].mscore>150))//对用户输入的数据进行判断
			do
		       {
			      printf("输入错误，请重新输入！\n");//如果输入错误就提示用户重输
			      scanf("%f",&aStu[index].mscore);
		       }while((aStu[index].mscore<0)||(aStu[index].mscore>150));
		getchar();
        printf("请输入英语成绩：[0～150之间]：");
		scanf("%f",&aStu[index].escore);
		if((aStu[index].escore<0)||(aStu[index].escore>150))//对用户输入的数据进行判断
			do
			   {
					printf("输入错误，请重新输入！\n");
				    scanf("%f",&aStu[index].escore);
			   }while((aStu[index].escore<0)||(aStu[index].escore>150));
		getchar();
		index++;//一个学生信息输入完成，计数加一
		printf("\n学生信息添加成功！是否继续添加？[y/n]：");
		scanf("%c",&ch);
	}while(ch!='n');
	*nCount=index;//学生信息实际个数
}
void show(const Student aStu[],int nCount)//定义显示学生信息的函数
{
	int i=0;//循环变量
	printf("\n**********显示学生信息**********\n");
	printf("学号          姓名      性别      年龄      c语言成绩     数学成绩     英语成绩\n");
	for(i=0;i<nCount;i++)
	{
		printf("%-14s%-10s%-10c%-10d%-14f%-13f%-8f\n",
		       aStu[i].num,aStu[i].name,aStu[i].sex,aStu[i].age,aStu[i].cscore,aStu[i].mscore,aStu[i].escore);

	}
}

void search(Student aStu[],int nCount)//定义查找学生信息的函数
{
	char input_name[32];//被查找学生的姓名
	int i=0;//循环变量
	printf("\n******查找学生信息******\n");
	printf("请输入被查找学生的姓名：");
	scanf("%s",input_name);//以姓名为条件查找学生信息
	for(i=0;i<nCount;i++)
	{
		if(strcmp(aStu[i].name,input_name)==0)
		 break;//跳出for循环
	     
    }//i<nCount时，找到了该学生信息
	if(i<nCount)
	{
		printf("该学生信息如下：");
		printf("\n学号          姓名      性别      年龄      c语言成绩     数学成绩     英语成绩\n");
		printf("%-14s%-10s%-10c%-10d%-14f%-13f%-8f\n",
			   aStu[i].num,aStu[i].name,aStu[i].sex,aStu[i].age,aStu[i].cscore,aStu[i].mscore,aStu[i].escore);

	}
	else
	{
		printf("没有找到学生信息！\n");
	}
}

