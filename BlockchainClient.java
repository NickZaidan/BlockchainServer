import java.net.*;
import java.io.*;
import java.util.Scanner;

public class BlockchainClient{
	public static void main(String[] args){
		if(args.length != 2){
			return;
		}

		BlockchainClient c = new BlockchainClient();
		try {
			String serverName = args[0];
			int x = Integer.parseInt(args[1]);
			Socket kkSocket = new Socket("", x);
			c.clientHandler(kkSocket.getInputStream(), kkSocket.getOutputStream());
			kkSocket.close();
		}
		catch (IOException ioe) {
			System.err.println(ioe);
		}
	}

	public void clientHandler(InputStream serverInputStream, OutputStream serverOutputStream){
		BufferedReader inputReader = new BufferedReader(new InputStreamReader(serverInputStream));
		PrintWriter pr = new PrintWriter(serverOutputStream);
		Scanner kbd = new Scanner(System.in);
		while(kbd.hasNextLine()){
			String input = kbd.nextLine();
			if(input.equals("cc")){
				pr.print("cc" + "\n");
				pr.flush();
				break;
			}
			String output = "";
			if(input.equals("")){
				System.out.print("Error\n\n");
				continue;
			}
			try{

				pr.print(input + "\n");
				pr.flush();
				while((output = inputReader.readLine()) != null){
					System.out.println(output);
					if(!inputReader.ready()){
						break;
					}
				}




			}
			catch(IOException e){
				System.out.println(e);
			}
		}
		try{
			inputReader.close();
		}
		catch(IOException e){

		}
		pr.close();

		/*
		while(true){
			while(!kbd.hasNextLine()){
				try{
					Thread.sleep(1);
				} catch(InterruptedException e){
					System.out.println(e);
				}

			}

			String line = kbd.nextLine();
			if(line.equals("cc")){
				break;
			}
			pr.println(line);
			pr.flush();


		}
		pr.close();
		*/
	}

}
