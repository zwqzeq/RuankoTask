#include<stdio.h>
int main()
{
 int isLeapYear(int nYear);          //��������
 int totalDays(int nDay,int nMonth); //��������
 int nYear,nMonth,nDay,year,nDays;
 printf("�밴����ʾ����������,��,��\n");
 printf("���������:");
 scanf_s("%d",&nYear);         //�������
 if(nYear>=2017) printf("������ݴ���,����������");
 printf("\n");
 printf("�������·�:");
 scanf_s("%d",&nMonth);         //�����·�
 printf("\n");
 if(nMonth>=13||nMonth<=0) printf("������·ݴ�������������");
 printf("����������:");    
 scanf_s("%d",&nDay);           //��������
 if(nDay>=32||nDay<=0) printf("����������������������");
 printf("\n");
 isLeapYear(year);
 if(year==1) printf("����Ϊ����\n");
 else printf("����Ϊƽ��\n");
 totalDays(nDay,nMonth);
 return 0;
}

int isLeapYear(int nYear)
{
 int year=0;
 if(nYear%4==0&&nYear%100!=0||nYear%400==0)   //�ж��Ƿ�Ϊ����
 {
  year=1;
 }
 return year;
 }

int totalDays(int nDay,int nMonth)

 {
  int isLeapYear(int nYear);
  int i,days,year,nDays,j=0;
  for(i=1;i<nMonth;i++)
  {
   switch(i)
   {
    case 1:case 3:case 5:case 7:case 8:case 10:case 12:
    days=31;break;                                      //1 3 5 7 8 10 12 ��Ϊ31��
    case 4:case 6:case 9:case 11:
    days=30;break;                                      //4 6 9 11 ��Ϊ30��
    case 2:
	isLeapYear(year);
    if(year==1) days=29;else days=28;break;              //����2��Ϊ29�죬ƽ��2��Ϊ28��
   }
   j=days+j;
  nDays=j+nDay;
                                      //����������
}
  printf("����Ϊ�����Ԫ����ʼ�ĵ�%d��",nDays);
  return nDays;
 }