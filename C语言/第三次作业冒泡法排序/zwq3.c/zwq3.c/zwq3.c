#include<stdio.h>//这是编译预处理指令
int main()       //定义主函数
{                //函数体开始
    int nyear;   //输入的年份
    int nmonth;  //输入的月份
    int nday;    //输入的日子
    int nmonthday;  //每个月的天数
    int nsumdays;//从输入的这一年元旦到输入的这一年这一天总天数
    int i;
    int sum=0;      //输入的这个月之前的天数之和
    printf("请输入年: ");
    scanf("%d",&nyear);

    printf("请输入月: ");
    scanf("%d",&nmonth);

    printf("请输入日: ");
    scanf("%d",&nday);
if((nyear%4==0&&nyear%100!=0)||(nyear%400==0))//判断平年还是闰年
{printf("这一年为闰年");printf("\n");}
else
{printf("这一年为平年");printf("\n");}

switch(nmonth)//选择结构，根据输入的年月确定天数
{
case 1:
case 3:
case 5:
case 7:
case 8:
case 10:
case 12:nmonthday=31;break;  //1,3,5,7,8,10,12这七个月的天数为31天
case 2:  //2月份的天数需要根据是否是闰年来判断//如果是闰年，二月份的天数为29天，如果为平年，二月份的天数为28天
        {if((nyear%4==0&&nyear%100!=0)||(nyear%400==0))
            nmonthday=29;
        else
            nmonthday=28;
        }break;
default:  //其余几个月为30天
    nmonthday=30;
    
}
for(i=1;i<nmonth;i++)
{    switch(i)
    {case 1:
    case 3:
    case 5:
    case 7:
    case 8:
    case 10:
    case 12: nmonthday=31;break;
    case 2:
        {if((nyear%4==0&&nyear%100!=0)||(nyear%400==0))
            nmonthday=29;
        else
            nmonthday=28;
        }break;
default:  //其余几个月(即4,6,9,11月)为30天。注意default后的语句可不用break，因为switch语句到此处就结束了。
    nmonthday=30;
    }//选择结构结束
sum=nmonthday+sum;
}//for循环结束
nsumdays=sum+nday;
printf("从这一年元旦到这一天有：");
printf("%d天",nsumdays);
return 0;
}