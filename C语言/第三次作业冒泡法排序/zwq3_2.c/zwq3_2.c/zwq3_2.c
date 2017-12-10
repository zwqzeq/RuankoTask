#include<stdio.h>//这是编译预处理指令
int main()//定义主函数
{         //函数体开始
    int i,j,t;//定义变量
    int a[10];//定义一个整形数组,数组名为a,此数组有十个变量
printf("请输入10个数:\n");
i=0;            //变量的初值
while(i<10)
    {scanf("%d",&a[i]);
       i++;}   //while循环结构（while后不能加”；“）
for(j=0;j<9;j++)
    {for(i=0;i<9-j;i++)
       if(a[i]>a[i+1])
       {t=a[i];
        a[i]=a[i+1];
        a[i+1]=t;//赋值语句处理两个数之间的交换
       }
    }         //for循环的嵌套
printf("由小到大顺序输出这十个数为：\n");
  i=0;
  do
  {printf("%d",a[i]);
  printf(" ");
  i++;
  }
  while(i<10) ;//do...while循环结构（do...while语句中while后可以加”；“）
  printf("\n");
return 0;
}