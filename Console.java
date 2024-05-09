package shady_main;

import java.util.Scanner;

public class Console implements Runnable{
	
	int counter = 0;
	boolean running = true;
	Thread t;
	Scanner scanner;
	MainPlayer joel;

	Sarah sarah;
	Doctor doctor;
	Soldier soldier;
	Ellie ellie;
	Infected infected;

	Location currentLocation;
	
	GunItem gun;
	MedkitItem medkit;
	FlashlightItem flashlight;

	ThreatenCommand threatencmd = new ThreatenCommand(joel);
	InteractCommand interactcmd = new InteractCommand(joel);
	Command[] cmds = {interactcmd, threatencmd};
	ControlPanel cmdsPanel = new ControlPanel(cmds);

	private static Console instance;

	private Console(){
		joel = MainPlayer.getInstance();
		sarah = Sarah.getInstance(joel);
		doctor = Doctor.getInstance(joel);
		soldier = Soldier.getInstance(joel);
		ellie = Ellie.getInstance(joel);
		infected = Infected.getInstance(joel);
		currentLocation = new JoelsHouse(joel);
		gun = new GunItem("Gun", joel);
		medkit = new MedkitItem("Medkit", joel);
		flashlight = new FlashlightItem("Flashlight", joel);
		this.scanner = new Scanner(System.in);
		t = new Thread(this);
		t.start();
	}

	public static synchronized Console getInstance() {
		if(instance == null) 
			instance = new Console();
		return instance;	
	}

	public Thread getThread() {
		return t;
	}

	public void exitGame() {
		running = false;
	}

	public void callfirstscene() {
		startGame start = startGame.getInstance();
		try {
			start.getThread().join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void callsecondscene() {
		continueGame start = continueGame.getInstance();
		try {
			start.getThread().join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void callthirdscene() {
		finalGame start = finalGame.getInstance();
		try {
			start.getThread().join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {

		while(running) {
			if(this.counter == 0) {
				this.callfirstscene();
				UI.printNormal("\n" + "Going to next location....");
				try {
					Thread.sleep(10*1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				counter++;
			}
			if(this.counter == 1) {
				this.callsecondscene();
				UI.printNormal("\n" + "Going to next location....");
				try {
					Thread.sleep(10*1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				counter++;
			}
			if(this.counter == 2) {
				this.callthirdscene();
				try {
					Thread.sleep(10*1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				counter++;
			}
			if(this.counter == 3) {
				this.exitGame();
			}
		}
	}
}
