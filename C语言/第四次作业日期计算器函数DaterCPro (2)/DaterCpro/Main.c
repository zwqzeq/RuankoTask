#include<stdio.h>
int main()
{
	int isLeapyear(int nyear);//声明isLeapyear函数
    int totaldays(int nyear,int nmonth,int nday);//声明totaldays函数
	int nyear;
	int nmonth;
	int nday;
	int nsumdays;                       //总天数
	printf("请输入年：");
	scanf_s("%d",&nyear);
	printf("请输入月：");
	scanf_s("%d",&nmonth);
	printf("请输入日：");
	scanf_s("%d",&nday);
if(1==isLeapyear(nyear))//调用isLeapyear函数
{
	printf("该年为闰年\n");
}

else
{
	printf("该年为平年\n");
}
nsumdays=totaldays(nyear,nmonth,nday)+nday;//调用totaldays函数
printf("从该年元旦到该天总共有%d天",nsumdays);
printf("\n");
return 0;
}


//定义isLeapyear函数	判断平年闰年
int isLeapyear(int nyear)
{	
	if(nyear%4==0&&nyear%100!=0||nyear%400==0){//判断是否闰年
				return 1;
	}
	else{
			    return 0;
		}	
}

//定义totaldays函数计算总天数
int totaldays(int nyear,int nmonth,int nday)
{
	int i;
	int nmonthdays=0;
	int t=0;
	for(i=1;i<nmonth;i++)//循环语句计算输入的这个月之前的总天数
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
		t+=nmonthdays;//t为输入这个月之前总天数
	}
    return t;
}