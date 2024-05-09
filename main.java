package shady_main;

import java.io.IOException;

public class main {

	public static void main(String[] args) {
		MainPlayer joel = MainPlayer.getInstance();
		
		Sarah.getInstance(joel);
		Soldier.getInstance(joel);
		
		Ellie.getInstance(joel);
		Infected.getInstance(joel);
		
		Doctor.getInstance(joel);
		
		TCP_Client tcp = TCP_Client.getInstance();
		
		Console game = Console.getInstance();
		
		try {
			joel.getThread().join();
			tcp.getThread().join();
			game.getThread().join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
 }
}

