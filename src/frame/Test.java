package frame;

import java.util.ArrayList;

public class Test {
public static void main(String[] args) {
	new Test();
}
public Test() {
	math();
}
public ArrayList<Integer> getIndexesOfReg(String example,String reg) {
	Integer index=example.indexOf(reg, 0);
	ArrayList<Integer> indexes=new ArrayList<>();
	while (index>-1) {
		indexes.add(index);
		index=example.indexOf(reg, index+1);
	}
	return indexes;
}
public void fileTest() {
	System.out.println("不是数字"+(Character.getType("a4".charAt(1))!=Character.DECIMAL_DIGIT_NUMBER));
	String name="1.2345678.9.abcd.ABCD";
	String ends = name.substring(name.lastIndexOf(".")+1);
	System.out.println(name.indexOf("."));
	System.out.println("ends是"+ends);
	System.out.println(getIndexesOfReg(name, "."));
	String[] parts=name.split(".");
	System.out.println("用.分割成的份数"+parts.length);
	for(String a:parts){
		System.out.println(a); 
	}
}
public void math() {
	for(int i=0;i<500;i++) { 
		Integer a=36*i;
		System.out.println("36×"+i+"="+a);
		String reg="\\d{1}"+"73"+"\\d{1}";
		if(a.toString().matches(reg)) {
			System.out.println(a+"符合条件-----------------");
		}
	}
}
}
