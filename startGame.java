package shady_main;

public class startGame implements Runnable{

	Thread t;

	MainPlayer joel;
	Sarah sarah;

	Soldier soldier;
	Location currentLocation;

	GunItem gun;

	private static startGame instance;

	private startGame() {
		joel = MainPlayer.getInstance();
		sarah = Sarah.getInstance(joel);
		soldier = Soldier.getInstance(joel);
		gun = new GunItem("Gun", joel);
		currentLocation = new JoelsHouse(joel);
		t = new Thread(this);
		t.start();
	}

	public static synchronized startGame getInstance() {
		if(instance == null) 
			instance = new startGame();
		return instance;	
	}

	public Thread getThread() {
		return t;
	}

	@Override
	public void run() {


		UI.printNormal("Welcome to Survivor's Echo\n" +"\n"+ "You are going to play as Joel Miller, father of"
				+ " 13 year old Sarah Miller.\n" +"Joel and Sarah live in Texas, in Joel's house.\n"
				+ "Joel is a hardworking carpenter, who lives to provide and take care of Sarah.");

		try {
			Thread.sleep(5*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		currentLocation.description();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UI.printNormal("Joel goes to his room to fetch his gun for safety.");
		gun.makeItem();

		joel.talk();
		UI.printNormal("");
		UI.printNormal("Joel:- Sarah where are you?");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		joel.FoundSarah = true;

		UI.printNormal("Rotate the phone landscape (left) to unlock main house door..");
		try {
			Thread.sleep(15*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(joel.doorUnlocked) { //door unlocked using sensor
			UI.printNormal("Joel and Sarah head to the car\n"
					+ "They get into the car and start driving to leave town\n"
					+ "As they are driving, "
					+ "they look around and the streets are full of mayhem and chaos filled \n"
					+ "with infected creatures chasing and "
					+ "biting civilians.\n" + "A car crashes into them rendering their car useless\n"
					+ "Sarah can't walk so Joel has to carry her\n"
					+ "He finds a soldier and goes towards him asking for help");
		}
		else {//door not unlocked
			UI.printNormal("Failed to unlock door! Joel and Sarah leave through the garage door..");
		}
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		joel.seeSoldier = true;
		joel.callforSoldier();
		joel.soldierShotGun = true;
		try {
			Thread.sleep(10*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		UI.printNormal("");
		//UI.printNormal("Joel:- Hey, Solider! We need help over here.");
		try {
			Thread.sleep(15*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Swing the phone forward to shoot at Soldier..");
		try {
			Thread.sleep(15*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(joel.shotBack) {	//shooting back at soldier using sensor
			UI.printNormal("You killed the soldier\n " + "Joel checks his body for gunshots but he hears his daughter faintly calling\n"
					+ " out his name. \n"
					+ "He runs to Sarah to find her laying on the ground with a gunshot to her chest.\n"
					+ "Joel cries for help but no one comes.\n"
					+ "He sits with his daughter's corpse in his hand.");

			currentLocation.next();//changing state to Scene 2: Abandoned House
		}
		else { //failed to shoot back
			UI.printNormal("Failed to shoot at solider! Joel runs, leaving Sarah behind..");
			Console.getInstance().exitGame();
		}



	}


}
