#define _CRT_SECURE_NO_WARNINGS//��������
#include<stdio.h>//Ԥ����ָ��
#include<stdlib.h>//�õ�system������ʱ����Ҫ��һ��
#include<string.h>//��ʹ���ַ���������ʱҪд��һ��     
#define STU_MAX 50//�����������50��ѧ����Ϣ
	typedef struct Student
   {	char num[16];//ѧ��
		char name[32];//����
		char sex;//�Ա�
		int age;//����
		float cscore;//c���Գɼ�
		float mscore;//��ѧ�ɼ�
		float escore;//Ӣ��ɼ�
   }Student;
int main()
{	    void print_menu();//�˵�����������
		void add(Student aStu[],int *nCount);//���ѧ����Ϣ��������
		void show(Student aStu[],int nCount);//��ʾѧ����Ϣ��������
	    void search(Student aStu[],int nCount);//����ѧ����Ϣ��������
	    int select=-1;//����select���ڴ���û���������֣���һ���Ǹ���������ֵ
	    Student stu[STU_MAX];//ѧ����Ϣ������ṹ�����飩
		int count=0;
	    void print_star();//����print_star����
		void print_abcd();//����print_abcd����
	    printf("************************\n");
	    printf("ѧ����Ϣ����ϵͳ\n");               //����
	    printf("************************\n");
	    printf("\n");
	do
	{
		print_menu();//������ʾ�˵��ĺ���
	    scanf("%d",&select);
	    printf("��ѡ��Ĳ˵�����%d\n",select);//��ʾ�û�ѡ��Ľ��
	    switch(select)
	  {
	    case 0:
		       printf("��ѡ�����˳�ϵͳ�˵�\n");
		       break;
	    case 1:
			   print_star();//����print_star����
		       add(stu, &count);//�������ѧ����Ϣ�ĺ���
		       print_star();//����print_star����
		       break;
	    case 2:
	  	       print_star();
	           show(stu,count);//������ʾѧ����Ϣ�ĺ���
		       print_star();
		       break;
	    case 3:
			   printf("��ѡ���˲���ѧ����Ϣ�˵�\n");
			   printf("\n");
			   print_star();
		       printf("�����뱻����ѧ����������\n");
			   search(stu,count);//���ò���ѧ����Ϣ�ĺ���
			   print_star();
			   break;
	    case 4:
		       printf("��ѡ���˱���ѧ����Ϣ�˵�\n");
		       break;
	    case 5:
		       printf("��ѡ���˶�ȡѧ����Ϣ�˵�\n");
		       break;
	    default:
		       printf("�������(0��5)!\n");
		       break;
	   }
   }    while(select!=0);//!=�ǹ�ϵ�������!=0��ʾ��ϵ���ʽΪ��
	    system("pause");
	    return 0;
}
void print_star()//���� print_star������������Ǻţ�����Ϣ��������ʹ���������
{
	   printf("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");//���������һ���Ǻ�
	        //����Ǻź�����ž��������
}
void print_abcd()   //����print_abcd()������������������Ǻţ�����Ϣ������  ʹ��������� 
{    
	   int i;
	   for(i=1;i<=5;i++)
	  {
		printf("*");
	    printf("                                                                                                 *");
		printf("\n");
	  }
}
void print_menu()//������ʾ�˵��ĺ���
{  
	printf("**********�˵�**********\n");       //�˵�
	printf("1 ���ѧ����Ϣ\n");
	printf("2 ��ʾѧ����Ϣ\n");
	printf("3 ����ѧ����Ϣ\n");
	printf("4 ����ѧ����Ϣ\n");
	printf("5 ��ȡѧ����Ϣ\n");
	printf("0 �˳�ϵͳ\n");
	printf("************************\n");
	printf("����������ѡ��(0��5):");//��ʾ�û��������֣�ѡ��˵�
}
void add(Student aStu[],int *nCount)//�������ѧ����Ϣ�ĺ���
{   
	int index=*nCount;//ѧ����Ϣ��ӵ��ṹ��������±�
	char ch='n';//y or n
	//�Ա�������ʼֵ����mΪ��fΪŮ��
	printf("\n**********���ѧ����Ϣ***********\n");
	do
	{   
		printf("������ѧ�ţ�[������10����]��");
		scanf("%s",aStu[index].num);
		strlen(aStu[index].num);
		if(strlen(aStu[index].num)>10)//���û���������ݽ����ж�
		   do
		    {
			  printf("����������������룺\n");
			  scanf("%s",aStu[index].num);
		    }while(strlen(aStu[index].num)>10);
		getchar();
		printf("������������[32���ַ�����]��");
		scanf("%s",aStu[index].name);
		strlen(aStu[index].name);
		if(strlen(aStu[index].name)>32)//���û���������ݽ����ж�
			do
			{
				printf("����������������룺\n");
				scanf("%s",aStu[index].name);
			}while(strlen(aStu[index].name)>32);
		getchar();
		printf("�������Ա�[m/f��m�����У�f����Ů]��");
		scanf("%c",&aStu[index].sex);
		getchar();
		printf("���������䣺[0��150֮��]��");
		scanf("%d",&aStu[index].age);
		getchar();
		if((aStu[index].age<0)||(aStu[index].age>150))//���û���������ݽ����ж�
		  do
			{   
				printf("����������������룡\n");
			    scanf("%d",&aStu[index].age);
			}while((aStu[index].age<0)||(aStu[index].age>150));
		printf("������c���Գɼ���[0��150֮��]��");
		scanf("%f",&aStu[index].cscore);
			if((aStu[index].cscore<0)||(aStu[index].cscore>150))//���û���������ݽ����ж�
		    do
			 {   
				 printf("����������������룡\n");
			     scanf("%f",&aStu[index].cscore);
			 }while((aStu[index].cscore<0)||(aStu[index].cscore>150));
		getchar();
		printf("��������ѧ�ɼ���[0��150֮��]��");
		scanf("%f",&aStu[index].mscore);
		if((aStu[index].mscore<0)||(aStu[index].mscore>150))//���û���������ݽ����ж�
			do
		       {
			      printf("����������������룡\n");//�������������ʾ�û�����
			      scanf("%f",&aStu[index].mscore);
		       }while((aStu[index].mscore<0)||(aStu[index].mscore>150));
		getchar();
        printf("������Ӣ��ɼ���[0��150֮��]��");
		scanf("%f",&aStu[index].escore);
		if((aStu[index].escore<0)||(aStu[index].escore>150))//���û���������ݽ����ж�
			do
			   {
					printf("����������������룡\n");
				    scanf("%f",&aStu[index].escore);
			   }while((aStu[index].escore<0)||(aStu[index].escore>150));
		getchar();
		index++;//һ��ѧ����Ϣ������ɣ�������һ
		printf("\nѧ����Ϣ��ӳɹ����Ƿ������ӣ�[y/n]��");
		scanf("%c",&ch);
	}while(ch!='n');
	*nCount=index;//ѧ����Ϣʵ�ʸ���
}
void show(const Student aStu[],int nCount)//������ʾѧ����Ϣ�ĺ���
{
	int i=0;//ѭ������
	printf("\n**********��ʾѧ����Ϣ**********\n");
	printf("ѧ��          ����      �Ա�      ����      c���Գɼ�     ��ѧ�ɼ�     Ӣ��ɼ�\n");
	for(i=0;i<nCount;i++)
	{
		printf("%-14s%-10s%-10c%-10d%-14f%-13f%-8f\n",
		       aStu[i].num,aStu[i].name,aStu[i].sex,aStu[i].age,aStu[i].cscore,aStu[i].mscore,aStu[i].escore);

	}
}

void search(Student aStu[],int nCount)//�������ѧ����Ϣ�ĺ���
{
	char input_name[32];//������ѧ��������
	int i=0;//ѭ������
	printf("\n******����ѧ����Ϣ******\n");
	printf("�����뱻����ѧ����������");
	scanf("%s",input_name);//������Ϊ��������ѧ����Ϣ
	for(i=0;i<nCount;i++)
	{
		if(strcmp(aStu[i].name,input_name)==0)
		 break;//����forѭ��
	     
    }//i<nCountʱ���ҵ��˸�ѧ����Ϣ
	if(i<nCount)
	{
		printf("��ѧ����Ϣ���£�");
		printf("\nѧ��          ����      �Ա�      ����      c���Գɼ�     ��ѧ�ɼ�     Ӣ��ɼ�\n");
		printf("%-14s%-10s%-10c%-10d%-14f%-13f%-8f\n",
			   aStu[i].num,aStu[i].name,aStu[i].sex,aStu[i].age,aStu[i].cscore,aStu[i].mscore,aStu[i].escore);

	}
	else
	{
		printf("û���ҵ�ѧ����Ϣ��\n");
	}
}

