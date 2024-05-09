package shady_main;

import java.util.Random;
import java.util.Scanner;

public class finalGame implements Runnable{

	Thread t;

	MainPlayer joel;

	Location currentLocation = new Hospital(joel);

	ThreatenCommand threatencmd;
	InteractCommand interactcmd;
	

	private static finalGame instance;

	private finalGame() {
		joel = MainPlayer.getInstance();
		currentLocation = new Hospital(joel);
		threatencmd = new ThreatenCommand(joel);
		t = new Thread(this);
		t.start();
	}

	public static synchronized finalGame getInstance() {
		if(instance == null) 
			instance = new finalGame();
		return instance;	
	}

	public Thread getThread() {
		return t;
	}

	@Override
	public void run() {

		Command[] cmds = {threatencmd, interactcmd};
		ControlPanel cmdsPanel = new ControlPanel(cmds);
		try {
			Thread.sleep(10*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		currentLocation.description();
		UI.printNormal("You and Ellie go in to search for the doctors.\n");


		UI.printNormal("Speak into the phone to call out the doctors.\n");

		try {
			Thread.sleep(15*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(joel.DoctorsFound) {
			joel.talk();

			UI.printNormal("Choose what to do next: ");
			//cmdsPanel.printCommands();
			UI.printNormal("1.Threaten Doctor\n"
					+ "2. Ask Doctor nicely");
			UI.printNormal("");
			
			Random r = new Random();
			int no = r.nextInt(2) + 1;

			if(no == 1 ) {
				cmdsPanel.buttonWasPressed(0);
			}
			else if (no == 2){
				cmdsPanel.buttonWasPressed(1);
			}

			
			try {
				Thread.sleep(10*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			joel.finishedGame = true;
			UI.printNormal("Doctor made a cure in 2 hours!\n"
					+ "THE END\n");
			UI.printNormal("");
			System.exit(0);
		}
	}

}
