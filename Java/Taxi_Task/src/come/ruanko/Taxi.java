package come.ruanko;

import java.math.BigDecimal;
import java.util.Scanner;

public class Taxi {
	static double price(double mileage,int time){
     int start_price=0;                  //�𲽼�
     double price1=0;                    //3~15����ÿ����۸�
     double price2=0;                    //15��������ÿ����۸�
     if(time==1){
     start_price=10;                     //�����𲽼�
     price1=2;                           //����3~15����ÿ����۸�
     price2=3;                           //����15��������ÿ����۸�
     }
     if(time==2){
    	 start_price=11;                 //ҹ���𲽼�
    	 price1=2*(1+0.2);               //ҹ��3~15����ÿ����۸�
    	 price2=3*(1+0.2);               //ҹ��15��������ÿ����۸�
     }
    if(mileage<3){                             //3��������
    	return start_price;
    }	else if(mileage>=3&&mileage<15){       //3~15����
    		return start_price+(mileage-3)*price1;
    }	else {                                 //����15����
			return start_price+(15-3)*price1+(mileage-15)*price2;
		}
    
	
     }
	public static void main(String[] args) {
		System.out.println("������˳������:");
		@SuppressWarnings("resource")
		Scanner scan=new Scanner(System.in);
		double mileage=scan.nextDouble();
		System.out.println("������˳�ʱ���(1-���죬2-ҹ��):");
		int time=scan.nextInt();
		System.out.println("�������"+mileage+" ʱ��: "+time);		
		@SuppressWarnings("unused")
		double price=price(mileage, time);
		double price5 = 0;
		
		BigDecimal bg=new BigDecimal(price5);
		double df=bg.setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue();
		
		System.out.println("�˳��۸�Ϊ: "+df);
	}
			

	}


