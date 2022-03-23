package ltd.newbee.mall.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestCompareWithJunit {
	@Test
	public  void test1() {
		List<Integer> list1 = new ArrayList<Integer>();
		list1.add(1);
		list1.add(2);
		list1.add(3);
		list1.add(4);
		
		List<Integer> list2 = new ArrayList<Integer>();
		list2.add(1);
		list2.add(3);
		list2.add(3);
		list2.add(4);
		
		if ( list1.size() != list2.size() ) {
			System.out.println("list的size不同，两个list肯定不同了");
		}else {
			for( int i = 0; i< list1.size(); i++) {
				assertEquals(list1.get(i),list2.get(i));
			}
			
		}
	}

}
