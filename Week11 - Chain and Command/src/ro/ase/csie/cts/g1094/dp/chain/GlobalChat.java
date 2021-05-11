package ro.ase.csie.cts.g1094.dp.chain;

public class GlobalChat extends ChatHandler{

	@Override
	public void processMessage(Message message) {
		if(message.destination.toLowerCase().equals("@everyone")) {
			System.out.println("Send this to everyone: " + message.text);
		}
		if(this.next != null)
			this.next.processMessage(message);
	}

}
