package junit.test;

import java.math.BigDecimal;

import org.junit.Test;

public class TestInt {
	
	@Test
	public void testDouble2(){
		
		//问题出在了BigDecimal的double型的构造器上
		//BigDecimal的double型构造器可能会返回一个不可预知的结果，所以这个构造器从来不用。
		//当我们需要对double类型进行计算时，我们选择使用String型的构造器
		BigDecimal a = new BigDecimal("0.09");
		BigDecimal b = new BigDecimal("0.01");
		
		System.out.println(a.add(b));
		
	}
	
	
	@Test
	public void testInt2(){
		//求1-20的阶乘
		BigDecimal a = new BigDecimal(1);
		
		for(int i=1 ; i<=100 ; i++){
			
			BigDecimal b = new BigDecimal(i);
			
			a = a.multiply(b);
			
		}
		
		System.out.println(a);
		
	}
	

	
	@Test
	public void testDouble(){
		
		double a = 0.09;
		double b = 0.01;
		
		System.out.println(a+b);
		
	}

	@Test
	public void testInt() {
		
		//在Java中为我们提供了BigDecimal类，这个类专门用来做精确计算的
		//我们可以使用BigDecimal对我们要计算的数，进行包装，然后在去调用BigDecimal的方法进行精确计算
		BigDecimal a = new BigDecimal(1);
		BigDecimal b = new BigDecimal(1);
		
		BigDecimal c = a.add(b);
		
		System.out.println(c);
		
		
		
	/*	//求 1 - 10的阶乘
		int a = 1;
		
		for(int i=1 ; i<=20 ; i++){
			a *= i;
		}
		
		System.out.println(a);*/
		
	}

}
