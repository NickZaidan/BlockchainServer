import java.util.ArrayList;
import java.util.Scanner;

public class TestingStuff{
/*
	public static void main(String[] args){
		Blockchain b = new Blockchain();
		Scanner s = new Scanner(System.in);
		while(true){
			String input = s.nextLine();
			if(input.equals("q")){
				System.out.println("Quitting");
				break;
			}
			else if(input.equals("pb")){
				System.out.println(b.toString());
			}

			else if(input.equals("size")){
				System.out.println(b.getLength());
			}
			else{
				b.addTransaction(input);
			}
		}
	}

*/
	public static void main(String[] args){
		Blockchain b = new Blockchain();
		b.addTransaction("tx|test0000|1");
		b.addTransaction("tx|test0000|2");
		b.addTransaction("tx|test0000|3");
		b.addTransaction("tx|test0000|4");
		b.addTransaction("tx|test0000|5");
		b.addTransaction("tx|test0000|6");
		b.addTransaction("tx|test0000|7");
		b.addTransaction("tx|test0011|8");
		b.addTransaction("tx|test0011|9");
		b.addTransaction("tx|test0011|10");
		b.addTransaction("tx|test0011|11");
		b.addTransaction("tx|test0011|12");
		b.addTransaction("tx|test0011|13");
		b.addTransaction("tx|test0011|14");
		b.addTransaction("tx|test0011|15");
		b.addTransaction("tx|test0011|16");
		b.addTransaction("tx|test0011|17");
		b.addTransaction("tx|test0011|18");
		b.addTransaction("tx|test0011|19");
		b.addTransaction("tx|test0011|20");
		b.addTransaction("tx|test0011|21");
		b.addTransaction("tx|test0011|22");
		b.addTransaction("tx|test0011|23");
		b.addTransaction("tx|test0011|24");
		b.addTransaction("tx|test0011|25");
		b.addTransaction("tx|test0011|26");
		b.addTransaction("tx|test0011|27");
		b.addTransaction("tx|test0011|28");
		b.addTransaction("tx|test0011|29");
		b.addTransaction("tx|test0011|30");
		b.addTransaction("tx|test0011|31");
		b.addTransaction("tx|test0011|32");
		b.addTransaction("tx|test0011|33");
		b.addTransaction("tx|test0011|34");
		b.addTransaction("tx|test0011|35");
		b.addTransaction("tx|test0011|36");

		Block current = b.getHead();
		int counter = 0;
		while(current != null){
			counter++;
			System.out.println("Block #" + counter);
			System.out.println(current.getPreviousHash());
			System.out.printf("\n\n\n\n\n");
			System.out.println(b.toString());
			current = current.getPreviousBlock();

		}

	}
	
}





/*
b.addTransaction("tx|test0011|a");
b.addTransaction("tx|test0011|ab");
b.addTransaction("tx|test0011|abc");
b.addTransaction("tx|test0011|abcd");
b.addTransaction("tx|test0011|abcde");
b.addTransaction("tx|test0011|abcdef");
b.addTransaction("tx|test0011|abcdefg");
b.addTransaction("tx|test0011|abcdefgh");
b.addTransaction("tx|test0011|abcdefghi");
b.addTransaction("tx|test0011|abcdefghij");

Block c = b.getHead();
System.out.println(b.getHead());
*/
