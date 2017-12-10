#define N 10//排行榜上的玩家总数

//定义分数的结构体
typedef struct SCORE
{
	char aName[20];//玩家姓名
	int iScore;//玩家得分
}Score;

void ReadScore(Score *aScore);       //从文件中读取分数
void WriteScore(Score *aScore);      //向文件中写分数
