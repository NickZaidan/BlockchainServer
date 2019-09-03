import java.util.ArrayList;
import java.util.regex.Pattern;


public class Blockchain {
    private Block head;
    private ArrayList<Transaction> pool;
    private int length;

    private final int poolLimit = 3;

    public Blockchain() {
        pool = new ArrayList<>();
        length = 0;
    }

    // getters and setters
    public Block getHead() { return head; }
    public ArrayList<Transaction> getPool() { return pool; }
    public int getLength() { return length; }
    public void setHead(Block head) { this.head = head; }
    public void setPool(ArrayList<Transaction> pool) { this.pool = pool; }
    public void setLength(int length) { this.length = length; }

    // add a transaction
    public int addTransaction(String txString) {
        // TODO: implement you code here.


		if(!stringVerifier(txString)){ //See if string is valid which I would be able to do better if Transactions had a constructor and then I could reuse the method I already wrote
			return 0;
		}

		//Annoying code that I need to add since Transaction doesn't have a constructor and I don't know if I'm allowed to add one
		Transaction t = new Transaction();
		String[] s = stringSplitter(txString);
		t.setSender(s[1]);
		t.setContent(s[2]);
		pool.add(t);
		//End of annoying code


		if(pool.size() == poolLimit){ //If we have reached the most amount of transactions
			if(head != null){ //If this is just a new block
				Block block = new Block();
				ArrayList<Transaction> temp = copyingPool();
				block.setTransactions(temp);
				block.setPreviousBlock(head);
				block.setPreviousHash(head.getCurrentHash());
				block.calculateHash();
				head = block;
				pool.clear();
				int x = getLength() + 1;
				setLength(x);
				return 2;
			}

			else{ //If this is the first block (Gensis block)
				head = new Block();
				byte[] b = new byte[32];
				ArrayList<Transaction> temp = copyingPool();
				head.setPreviousHash(b);
				head.setPreviousBlock(null);
				head.setTransactions(temp);
				head.calculateHash();
				pool.clear();
				int x = getLength() + 1;
				setLength(x);
				return 2;
			}
		}
		return 1;
    }

    public String toString() {
        String cutOffRule = new String(new char[81]).replace("\0", "-") + "\n";
        String poolString = "";
        for (Transaction tx : pool) {
            poolString += tx.toString();
        }

        String blockString = "";
        Block bl = head;
        while (bl != null) {
            blockString += bl.toString();
            bl = bl.getPreviousBlock();
        }

        return "Pool:\n"
                + cutOffRule
                + poolString
                + cutOffRule
                + blockString;
    }

    // implement helper functions here if you need any

	public boolean stringVerifier(String txString){

		//Ensuring only 2 delimters exist "|"
		int delimiterCounter = 0;
		for(int i = 0; i < txString.length(); i++){ //Go through the string and count every encounter with the delimiters
			char c = txString.charAt(i);
			if(c == '|'){
				delimiterCounter++;
			}
		}

		if(delimiterCounter != 2){
			return false;
		}

		//Split the string into 3 substrings for each component based around the delimters
		String[] statements = stringSplitter(txString);
		if(statements.length != 3){ //If for some reason the first part didn't catch an error, this ensures only 3 things are being read
			return false;
		}
		if(!statements[0].equals("tx")){ //Ensuring the first part is equal to tx
			return false;
		}

		if(statements[0].length() + statements[1].length() + statements[2].length() > 70){ //Ensure length is bound by 70
			return false;
		}

		if(statements[1].length() != 8){ //Ensure size is bound to 8
			return false;
		}

		if(!Pattern.matches("[a-z]{4}[0-9]{4}", statements[1])){ //Regex statement checking format of unikey
			return false;
		}

		return true;
	}

	//This method isn't really needed, but I didn't feel like copying and pasting the same code twice
	public String[] stringSplitter(String txString){
		String[] statements = txString.split("[\\|]+", 0);
		return statements;
	}

	//This function is for copying the transaction list from pool
	public ArrayList<Transaction> copyingPool(){
		ArrayList<Transaction> theReturn = new ArrayList<Transaction>();
		for(int i = 0; i < pool.size(); i++){
			theReturn.add(pool.get(i));
		}

		return theReturn;

	}
}
