#include<stdio.h>
int main()
{
	int isLeapyear(int nyear);//����isLeapyear����
    int totaldays(int nyear,int nmonth,int nday);//����totaldays����
	int nyear;
	int nmonth;
	int nday;
	int nsumdays;                       //������
	printf("�������꣺");
	scanf_s("%d",&nyear);
	printf("�������£�");
	scanf_s("%d",&nmonth);
	printf("�������գ�");
	scanf_s("%d",&nday);
if(1==isLeapyear(nyear))//����isLeapyear����
{
	printf("����Ϊ����\n");
}

else
{
	printf("����Ϊƽ��\n");
}
nsumdays=totaldays(nyear,nmonth,nday)+nday;//����totaldays����
printf("�Ӹ���Ԫ���������ܹ���%d��",nsumdays);
printf("\n");
return 0;
}


//����isLeapyear����	�ж�ƽ������
int isLeapyear(int nyear)
{	
	if(nyear%4==0&&nyear%100!=0||nyear%400==0){//�ж��Ƿ�����
				return 1;
	}
	else{
			    return 0;
		}	
}

//����totaldays��������������
int totaldays(int nyear,int nmonth,int nday)
{
	int i;
	int nmonthdays=0;
	int t=0;
	for(i=1;i<nmonth;i++)//ѭ������������������֮ǰ��������
	{
		switch(i)
	  {		
		    case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12: nmonthdays=31;break;
			case 2:{ if(1==isLeapyear(nyear))
				       nmonthdays=29;
				   else
				       nmonthdays=28;
				    } break;
			case 4:
			case 6:
			case 9:
			case 11:nmonthdays=30;break;
		}
		t+=nmonthdays;//tΪ���������֮ǰ������
	}
    return t;
}