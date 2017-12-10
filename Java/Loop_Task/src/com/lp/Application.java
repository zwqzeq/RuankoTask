package com.lp;                                                 //包名为com.lp
/**
 * 100元钱买100只鸡，小鸡一只0.5，公鸡一只1，母鸡一只2元，求小鸡公鸡母鸡个数有几种可能？
 * @author asus
 *
 */
public class Application {                                      //类名为Application

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		int count=0;                                        //变量count为所有买鸡的可能情况个数                    
		System.out.println("百鸡百钱问题");
		for(int chick=0;chick<=100;chick++){               //变量chick为小鸡数(因为总共要买一百只鸡，所以小鸡数最多买100只，循环条件最大为100，而不能为200)
			for(int cock=0;cock<=100;cock++){              //变量cock为公鸡数
				for(int hen=0;hen<=50;hen++){              //变量hen为母鸡数
					if((chick+cock+hen==100)&&(0.5*chick+cock+2*hen==100)){//如果三种鸡总和为100，并且买鸡的钱数为100
						System.out.println("小鸡"+chick+"只,公鸡"+cock+"只,母鸡"+hen+"只");//输出买鸡的个数情况
						count=count+1;//买鸡的可能情况加一
					}
				}
			}
		}
		System.out.println("共有"+count+"种方法购买");
	}

}
