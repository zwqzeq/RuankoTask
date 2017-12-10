#include<stdio.h>
int main()
{
 int isLeapYear(int nYear);          //声明函数
 int totalDays(int nDay,int nMonth); //声明函数
 int nYear,nMonth,nDay,year,nDays;
 printf("请按照提示依次输入年,月,日\n");
 printf("请输入年份:");
 scanf_s("%d",&nYear);         //输入年份
 if(nYear>=2017) printf("输入年份错误,请重新输入");
 printf("\n");
 printf("请输入月份:");
 scanf_s("%d",&nMonth);         //输入月份
 printf("\n");
 if(nMonth>=13||nMonth<=0) printf("输入的月份错误，请重新输入");
 printf("请输入天数:");    
 scanf_s("%d",&nDay);           //输入天数
 if(nDay>=32||nDay<=0) printf("输入天数错误，请重新输入");
 printf("\n");
 isLeapYear(year);
 if(year==1) printf("该年为闰年\n");
 else printf("该年为平年\n");
 totalDays(nDay,nMonth);
 return 0;
}

int isLeapYear(int nYear)
{
 int year=0;
 if(nYear%4==0&&nYear%100!=0||nYear%400==0)   //判断是否为闰年
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
    days=31;break;                                      //1 3 5 7 8 10 12 月为31天
    case 4:case 6:case 9:case 11:
    days=30;break;                                      //4 6 9 11 月为30天
    case 2:
	isLeapYear(year);
    if(year==1) days=29;else days=28;break;              //闰年2月为29天，平年2月为28天
   }
   j=days+j;
  nDays=j+nDay;
                                      //计算总天数
}
  printf("该天为该年从元旦开始的第%d天",nDays);
  return nDays;
 }