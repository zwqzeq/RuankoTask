#define _CRT_SECURE_NO_WARNINGS//��������
#include<stdio.h>//Ԥ����ָ��
#include<stdlib.h>//
#include<string.h>//��ʹ���ַ���������ʱҪд��һ��     

int main()
{   int select=-1;//����select���ڴ���û���������֣���һ���Ǹ���������ֵ
   void print_star();//����print_star����
	void print_abcd();//����print_abcd����
		char num[11];//ѧ��
		char name[32];//����
		char sex[1];//�Ա�
		int age;//����
		float cscore;//c���Գɼ�
		float mscore;//��ѧ�ɼ�
		float escore;//Ӣ��ɼ�
void prin_abcd();//����print_abcd����
void prin_star();//����print_star����
	printf("************************\n");
	printf("ѧ����Ϣ����ϵͳ\n");               //����
	printf("************************\n");
	printf("\n");
	do
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
	scanf("%d",&select);
	printf("��ѡ��Ĳ˵�����%d\n",select);//��ʾ�û�ѡ��Ľ��
	switch(select)
	{
	case 0:
		printf("��ѡ�����˳�ϵͳ�˵�\n");
		break;
	case 1:
		printf("��ѡ�������ѧ����Ϣ�˵�\n");
		printf("\n");
	    print_star();
		printf("����������\n");
		scanf("%s",name);
		printf("����������\n");
		scanf("%d",&age);
		if(age<0||age>150)
	      {
		        printf("    ������0��150֮�䣬����������\n");
	            scanf("%d",&age);
	       }
		printf("�������Ա�\n");
		scanf("%s",sex);
		printf("������ѧ�ţ�\n");
		scanf("%s",num);
		printf("������c���Գɼ���\n");
		scanf("%f",&cscore);
		printf("��������ѧ�ɼ���\n");
		scanf("%f",&mscore);
		printf("������Ӣ��ɼ���\n");
		scanf("%f",&escore);
		print_star();
		break;
	case 2:
		printf("��ѡ������ʾѧ����Ϣ�˵�\n");
		printf("\n");
		print_star();//���ú���
	    print_abcd();//���ú���
		printf("                           ##########ѧ����Ϣ#########\n");
		printf("������      ���䣺    �Ա�    ѧ�ţ�              c���Գɼ���      ��ѧ�ɼ���       Ӣ��ɼ���\n");
		printf("%s\t%8d\t%s\t%s\t\t%5f\t%5f\t%5f\n",name,age,sex,num,cscore,mscore,escore);
		print_star();//���ú���
		break;
	case 3:
		printf("��ѡ���˲���ѧ����Ϣ�˵�\n");
		printf("\n");
		printf("�����뱻����ѧ����������\n");
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
	}while(select!=0);//!=�ǹ�ϵ�������!=0��ʾ��ϵ���ʽΪ��
	system("pause");
	return 0;
}
void print_star()//���� print_star������������Ǻţ�����Ϣ��������ʹ���������
{
	    printf("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");//���������һ���Ǻ�
	        //����Ǻź�����ž��������
}
void print_abcd()   //����print_abcd()������������������Ǻţ�����Ϣ������  ʹ��������� 
{   int i;
	for(i=1;i<=5;i++)
	  {
		printf("*");
	    printf("                                                                                                 *");
		printf("\n");
	  }
}
