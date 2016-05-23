package com.test;

public class Test {
	public static void main(String[] args) {

		String s = "1D 9H 480M";
		String delimiter = "[a-zA-Z]" + "\\s";
		String[] temp = s.split(delimiter);
		String mnt = temp[2].replace("M", "");
		String d= null, h=null, m=null;
		for(int i=0;i<temp.length;i++){
				d = temp[0];
				h = temp[1];
		}
		
		Integer days = Integer.parseInt(d);
		Integer hours = Integer.parseInt(h);
		Integer minutes =  Integer.parseInt(mnt);
		
		if(minutes > 60){
					hours += minutes /60;
				minutes = minutes % 60;
				System.out.println("Inside Hours :-"+hours);
				
			
		}
		System.out.println("Hours before condition : " +hours);
		
		
		if(hours >8){
				days += hours / 8;
				hours =  hours %8;
				System.out.println("Days inside if "+days);
			
		}
		
		
		System.out.println("Days :-"+days+"Hours :-"+hours+"Minuets :-"+minutes);
		

	}
}
