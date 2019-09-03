import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;

public class Block {
    private Block previousBlock;
    private byte[] previousHash;
	private byte[] currentHash;
    private ArrayList<Transaction> transactions;

    public Block() { transactions = new ArrayList<>(); }

    // getters and setters
    public Block getPreviousBlock() { return previousBlock; }
    public byte[] getPreviousHash() { return previousHash; }
    public ArrayList<Transaction> getTransactions() { return transactions; }
    public void setPreviousBlock(Block previousBlock) { this.previousBlock = previousBlock; }
    public void setPreviousHash(byte[] previousHash) { this.previousHash = previousHash; }
    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }
	public byte[] getCurrentHash(){
		return currentHash;
	}
 	public void setCurrentHash(byte[] currentHash){
		this.currentHash = currentHash;
	}

    public String toString() {
        String cutOffRule = new String(new char[81]).replace("\0", "-") + "\n";
        String prevHashString = String.format("|PreviousHash:|%65s|\n",
                Base64.getEncoder().encodeToString(previousHash));
        String hashString = String.format("|CurrentHash:|%66s|\n",
                Base64.getEncoder().encodeToString(calculateHash()));
        String transactionsString = "";
        for (Transaction tx : transactions) {
            transactionsString += tx.toString();
        }
        return "Block:\n"
                + cutOffRule
                + hashString
                + cutOffRule
                + transactionsString
                + cutOffRule
                + prevHashString
                + cutOffRule;
    }

    // to calculate the hash of current block.
    public byte[] calculateHash() {
        // TODO: implement your code here.
		String hashString = "";
		byte[] hash = null;
		try{
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DataOutputStream dos = new DataOutputStream(baos);
			dos.write(getPreviousHash());
			for(int i = 0; i < transactions.size(); i++){
				String x = "tx|" + transactions.get(i).getSender() + "|" + transactions.get(i).getContent();
				dos.writeUTF(x);
			}

			byte[] bytes = baos.toByteArray();
			hash = digest.digest(bytes);

			hashString = Base64.getEncoder().encodeToString(hash);

		}
		catch (NoSuchAlgorithmException e) {
			return null;
		}
		catch (IOException e) {
			return null;
		}
		setCurrentHash(hash);
		return hash;

    }
	public void printTransactions(){
		for(int i = 0; i < transactions.size(); i++){
			System.out.println(transactions.get(i).toString());
		}
	}
    // implement helper functions here if you need any.
}
