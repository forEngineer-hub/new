package ltd.newbee.mall.controller.mall;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@FunctionalInterface
interface Cab{
	void bookCab(String n);
}
public class TestDateFormat {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		 String strDate = sdf.format(new Date());
		 System.out.println(strDate);
		 System.out.println(new Date());

	    
	    Cab cab = (n) -> { 
		 System.out.println(n); 
	    };
	    
	    cab.bookCab("nihao");
		
	    ArrayList<Integer> numbers = new ArrayList<Integer>();
	    numbers.add(5);
	    numbers.add(9);
	    numbers.add(8);
	    numbers.add(1);
	    numbers.forEach( (n) -> { System.out.println(n); } );
	}

}
