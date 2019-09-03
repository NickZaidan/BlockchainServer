import java.net.*;
import java.io.*;


public class BlockchainServer implements Runnable{

	private Blockchain blockchain;
	static BlockchainServer s;
	static Socket csocket;

	public BlockchainServer(){
		blockchain = new Blockchain();
	}

	public void setSocket(Socket csocket){
		this.csocket = csocket;
	}

	public void setBlockchain(Blockchain blockchain){
		this.blockchain = blockchain;
	}

	public Blockchain getBlockchain(){
		return this.blockchain;
	}

	public static void main(String[] args){
		if(args.length != 1){
			return;
		}

		s = new BlockchainServer();

		try{

			int x = Integer.parseInt(args[0]); //Set port number
			ServerSocket socket = new ServerSocket(x); //Creation of server socket
			while(true){
				Socket client = socket.accept(); //Accept incoming connection
				s.setSocket(client); //Set to the client of the instance
				new Thread(s).start(); //Launch thread
			}
		}

		catch(IOException e){
			System.out.println(e);
		}
	}

	public void run(){
		try{
			s.serverHandler(csocket.getInputStream(), csocket.getOutputStream()); //Launch method to run the serverHandler
			csocket.close();
		}
		catch(IOException e){
			System.out.println(e);
		}


	}
	public void serverHandler(InputStream clientInputStream, OutputStream clientOutputStream) {

		BufferedReader inputReader = new BufferedReader(new InputStreamReader(clientInputStream)); //THIS IS READING USER INPUT FROM CLIENT
		PrintWriter outWriter = new PrintWriter(clientOutputStream, true); //THIS IS FOR SENDING OUTPUT TO CLIENT

		try{
			String str = "";
			while((str = inputReader.readLine()) != null){
				if(str.equals("pb")){
					outWriter.print(getBlockchain().toString() + "\n"); //If pb was sent
					outWriter.flush();
				}

				else if(str.charAt(0) == 't' && str.charAt(1) == 'x'){
					int x = getBlockchain().addTransaction(str);
					if(x == 0){
						outWriter.print("Rejected\n\n");
						outWriter.flush();
					}
					else{
						outWriter.println("Accepted\n");
						outWriter.flush();
					}
				}
				else if(str.equals("cc")){
					break;
				}
				else{
					outWriter.print("Error\n\n");
					outWriter.flush();
				}
			}
			/*
			while(true){
				String str = inputReader.readLine(); //Read line that was sent from client

				if(str != null){

					if(str.equals("pb")){
						outWriter.print(getBlockchain().toString() + "\n"); //If pb was sent
						outWriter.flush();
					}

					else if(str.charAt(0) == 't' && str.charAt(1) == 'x'){
						int x = getBlockchain().addTransaction(str);
						if(x == 0){
							outWriter.print("Rejected\n\n");
							outWriter.flush();
						}
						else{
							outWriter.println("Accepted\n\n");
							outWriter.flush();
						}
					}
					else if(str.equals("cc")){
						break;
					}
					else{
						outWriter.print("Error\n\n");
						outWriter.flush();
					}
				}
			}
			*/
			//Close the datastreams
			outWriter.close();
			inputReader.close();

		}
		catch(IOException e){
			System.out.println(e);
		}

    }

}
