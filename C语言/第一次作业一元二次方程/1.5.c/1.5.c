#include<stdio.h>
#include<math.h>
int main()
{double a,b,c,delta,p,q,x1,x2;
scanf("%lf,%lf,%lf",&a,&b,&c);
delta=b*b-4*a*c;
p=-b/(2.0*a);
q=sqrt(delta)/(2.0*a);
x1=p+q;x2=p-q;
if (delta>=0)
	printf("方程有两个根分别为：x1=%7.2f\n,x2=%7.2f\n",x1,x2);
else
	printf("方程无实根");
return 0;
}
