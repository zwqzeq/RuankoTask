#include<stdio.h>//���Ǳ���Ԥ����ָ��
int main()       //����������
{                //�����忪ʼ
    int nyear;   //��������
    int nmonth;  //������·�
    int nday;    //���������
    int nmonthday;  //ÿ���µ�����
    int nsumdays;//���������һ��Ԫ�����������һ����һ��������
    int i;
    int sum=0;      //����������֮ǰ������֮��
    printf("��������: ");
    scanf("%d",&nyear);

    printf("��������: ");
    scanf("%d",&nmonth);

    printf("��������: ");
    scanf("%d",&nday);
if((nyear%4==0&&nyear%100!=0)||(nyear%400==0))//�ж�ƽ�껹������
{printf("��һ��Ϊ����");printf("\n");}
else
{printf("��һ��Ϊƽ��");printf("\n");}

switch(nmonth)//ѡ��ṹ���������������ȷ������
{
case 1:
case 3:
case 5:
case 7:
case 8:
case 10:
case 12:nmonthday=31;break;  //1,3,5,7,8,10,12���߸��µ�����Ϊ31��
case 2:  //2�·ݵ�������Ҫ�����Ƿ����������ж�//��������꣬���·ݵ�����Ϊ29�죬���Ϊƽ�꣬���·ݵ�����Ϊ28��
        {if((nyear%4==0&&nyear%100!=0)||(nyear%400==0))
            nmonthday=29;
        else
            nmonthday=28;
        }break;
default:  //���༸����Ϊ30��
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
default:  //���༸����(��4,6,9,11��)Ϊ30�졣ע��default������ɲ���break����Ϊswitch��䵽�˴��ͽ����ˡ�
    nmonthday=30;
    }//ѡ��ṹ����
sum=nmonthday+sum;
}//forѭ������
nsumdays=sum+nday;
printf("����һ��Ԫ������һ���У�");
printf("%d��",nsumdays);
return 0;
}