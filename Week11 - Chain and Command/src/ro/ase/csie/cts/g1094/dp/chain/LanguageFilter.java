package ro.ase.csie.cts.g1094.dp.chain;

public class LanguageFilter extends ChatHandler{
	
	String language;
	public static String[] forbiddenWords = new String[] {"hate", "attack", "kill"};
	
	public LanguageFilter(String language) {
		super();
		this.language = language;
	}

	@Override
	public void processMessage(Message message) {
		
		boolean isOk = true;
		for(String word : forbiddenWords) {
			if(message.text.toLowerCase().contains(word)){
				isOk = false;
				break;
			}
		}
		if(!isOk) {
			System.out.println("Message not allowed!");
		}else {
			if(this.next != null) {
				this.next.processMessage(message);
			}
		}
		
	}
}
