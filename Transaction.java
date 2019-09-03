import java.util.regex.Pattern;

public class Transaction {
    private String sender;
    private String content;

	// getters and setters
    public void setSender(String sender) { this.sender = sender; }
    public void setContent(String content) { this.content = content; }
    public String getSender() { return sender; }
    public String getContent() { return content; }

    public String toString() {
		if(stringVerifier(sender,content)){
			return String.format("|%s|%70s|\n", sender, content);
		}
		else {
			System.exit(1);
		}
		return null;
    }


    // implement helper functions here if you need any
	public boolean stringVerifier(String sender, String content){
		if(sender.length() + content.length() > 70){ //Ensure length is bound by 70
			System.out.println("Invalid size");
			System.exit(1);
		}


		String test = sender + content;
		if(test.contains("|")){ //Ensure there is no delimeter character
			System.out.println("Invalid string: Contains |");
			System.exit(1);
		}

		if(!unikeyVerifier(sender)){ //Launches unikey checker method
			System.out.println("Invalid unikey");
			System.exit(1);
		}

		return true;

	}

	public boolean unikeyVerifier(String sender){
		if(sender.length() != 8){ //Ensure size is bound to 8
			return false;
		}

		if(!Pattern.matches("[a-z]{4}[0-9]{4}", sender)){ //Regex statement checking format of unikey
			return false;
		}
		return true;

	}
}
