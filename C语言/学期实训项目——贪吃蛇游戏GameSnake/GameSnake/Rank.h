#define N 10//���а��ϵ��������

//��������Ľṹ��
typedef struct SCORE
{
	char aName[20];//�������
	int iScore;//��ҵ÷�
}Score;

void ReadScore(Score *aScore);       //���ļ��ж�ȡ����
void WriteScore(Score *aScore);      //���ļ���д����
