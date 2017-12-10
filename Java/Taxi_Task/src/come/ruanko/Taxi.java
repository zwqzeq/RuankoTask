package come.ruanko;

import java.math.BigDecimal;
import java.util.Scanner;

public class Taxi {
	static double price(double mileage,int time){
     int start_price=0;                  //起步价
     double price1=0;                    //3~15公里每公里价格
     double price2=0;                    //15公里以上每公里价格
     if(time==1){
     start_price=10;                     //白天起步价
     price1=2;                           //白天3~15公里每公里价格
     price2=3;                           //白天15公里以上每公里价格
     }
     if(time==2){
    	 start_price=11;                 //夜晚起步价
    	 price1=2*(1+0.2);               //夜晚3~15公里每公里价格
    	 price2=3*(1+0.2);               //夜晚15公里以上每公里价格
     }
    if(mileage<3){                             //3公里以内
    	return start_price;
    }	else if(mileage>=3&&mileage<15){       //3~15公里
    		return start_price+(mileage-3)*price1;
    }	else {                                 //大于15公里
			return start_price+(15-3)*price1+(mileage-15)*price2;
		}
    
	
     }
	public static void main(String[] args) {
		System.out.println("请输入乘车里程数:");
		@SuppressWarnings("resource")
		Scanner scan=new Scanner(System.in);
		double mileage=scan.nextDouble();
		System.out.println("请输入乘车时间段(1-白天，2-夜晚):");
		int time=scan.nextInt();
		System.out.println("里程数："+mileage+" 时间: "+time);		
		@SuppressWarnings("unused")
		double price=price(mileage, time);
		double price5 = 0;
		
		BigDecimal bg=new BigDecimal(price5);
		double df=bg.setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue();
		
		System.out.println("乘车价格为: "+df);
	}
			

	}


