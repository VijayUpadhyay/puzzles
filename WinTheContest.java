import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class WinTheContest {

	public static void main(String[] args) {
		//System.out.println("Please enter the number of contestents");
		Scanner sc = new Scanner(System.in);
		int students = sc.nextInt();
		List<Integer> studentNumber = new ArrayList<Integer>();
		List<BigInteger> productNumber = new ArrayList<BigInteger>();
		List<BigInteger> countAndSum = new ArrayList<BigInteger>();
		for(int i= 0;i<students;i++){
			//System.out.println("Please enter the number for student "+(i+1));
			Scanner studentData = new Scanner(System.in);
			if(studentData.hasNextInt()){
				studentNumber.add(studentData.nextInt());
			}else
				studentNumber.add(0);
				
		}
		
		for(int i=0;i<students;i++){
			productNumber.add(new BigInteger(findProduct(studentNumber.get(i)).toString()));
		}
			

		for(int i=0;i<students;i++){
			countAndSum.add(operations(productNumber.get(i)));
		}
		decideWinner(countAndSum);
	}
	public static BigInteger findProduct(Integer number){
		BigInteger product = new BigInteger("1");
		for(int i=1;i<=number;i++){
			product = product.multiply(new BigInteger(""+i));
		}
		return product;
	}
	
	private static BigInteger operations(BigInteger product){
		BigInteger count = new BigInteger("0");
		BigInteger sum = new BigInteger("0");
		BigInteger zero = new BigInteger("0");
		BigInteger ten = new BigInteger("10");
		BigInteger result = new BigInteger("0");
		while(product.compareTo(zero) == 1){
			BigInteger x = product.mod(ten);
			
			if(x.compareTo(zero) == 0)
				count = count.add(BigInteger.ONE);
			
			sum = sum.add(x);
			product = product.divide(ten);
		}
		result = sum.subtract(count);
		return result;
	}
	
	public static void decideWinner(List<BigInteger> countAndSum){
		BigInteger max = countAndSum.get(0),min = countAndSum.get(0);
		int maxIndex = 0,minIndex=0;
		int i = 0;
		
		for(BigInteger num : countAndSum){
			if(num.compareTo(max) == 1){
				maxIndex = i;
				max = num;
			}
			
			if(num.compareTo(min) == -1){
				minIndex = i;
				min = num;
			}
			i++;
		}
		
		
		System.out.println((maxIndex+1)+" "+max);
		System.out.println((minIndex+1)+" "+min);
	}

}