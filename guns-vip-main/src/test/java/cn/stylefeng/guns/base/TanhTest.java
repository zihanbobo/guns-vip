package cn.stylefeng.guns.base;

import java.math.BigDecimal;

import org.junit.Ignore;
import org.junit.Test;

import cn.stylefeng.guns.core.CommonUtils;

public class TanhTest {

	@Ignore
	public void TestTanh() {
		System.out.println(caculate(BigDecimal.valueOf(0)));
		System.out.println(caculate(BigDecimal.valueOf(1)));
		System.out.println(caculate(BigDecimal.valueOf(10)));
		System.out.println(caculate(BigDecimal.valueOf(100)));
		System.out.println(caculate(BigDecimal.valueOf(500)));
		System.out.println(caculate(BigDecimal.valueOf(5000)));
		System.out.println(caculate(BigDecimal.valueOf(50000)));
		System.out.println(caculate(BigDecimal.valueOf(500000)));
		System.out.println("-----------------------------------");
		System.out.println(caculate(BigDecimal.valueOf(-1)));
		System.out.println(caculate(BigDecimal.valueOf(-10)));
		System.out.println(caculate(BigDecimal.valueOf(-100)));
		System.out.println(caculate(BigDecimal.valueOf(-500)));
		System.out.println(caculate(BigDecimal.valueOf(-5000)));
		System.out.println(caculate(BigDecimal.valueOf(-50000)));
		System.out.println(caculate(BigDecimal.valueOf(-500000)));
	}
	
	public BigDecimal caculate(BigDecimal value) {
		BigDecimal baseNumber = BigDecimal.valueOf(500d);
		return baseNumber.multiply(BigDecimal.ONE.add(CommonUtils.divide(value, baseNumber.add(value)))).setScale(0, BigDecimal.ROUND_HALF_UP);
	}
}
