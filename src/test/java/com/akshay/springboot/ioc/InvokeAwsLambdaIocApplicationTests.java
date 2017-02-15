package com.akshay.springboot.ioc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


@ActiveProfiles("local")
//@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@RunWith(SpringRunner.class)
public class InvokeAwsLambdaIocApplicationTests {

	@Test
	public void contextLoads() {
	}

}
