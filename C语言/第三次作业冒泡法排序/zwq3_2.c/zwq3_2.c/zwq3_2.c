#include<stdio.h>//���Ǳ���Ԥ����ָ��
int main()//����������
{         //�����忪ʼ
    int i,j,t;//�������
    int a[10];//����һ����������,������Ϊa,��������ʮ������
printf("������10����:\n");
i=0;            //�����ĳ�ֵ
while(i<10)
    {scanf("%d",&a[i]);
       i++;}   //whileѭ���ṹ��while���ܼӡ�������
for(j=0;j<9;j++)
    {for(i=0;i<9-j;i++)
       if(a[i]>a[i+1])
       {t=a[i];
        a[i]=a[i+1];
        a[i+1]=t;//��ֵ��䴦��������֮��Ľ���
       }
    }         //forѭ����Ƕ��
printf("��С����˳�������ʮ����Ϊ��\n");
  i=0;
  do
  {printf("%d",a[i]);
  printf(" ");
  i++;
  }
  while(i<10) ;//do...whileѭ���ṹ��do...while�����while����Լӡ�������
  printf("\n");
return 0;
}