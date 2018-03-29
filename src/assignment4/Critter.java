package assignment4;
/* CRITTERS Critter.java
 * EE422C Project 4 submission by
 * Aneesh Soni
 * as76745
 * Section: 15460
 * Zarif Choudhury
 * zc3733
 * Section: 15460
 * Spring 2018
 */


import java.util.List;

/* see the PDF for descriptions of the methods and fields in this class
 * you may add fields, methods or inner classes to Critter ONLY if you make your additions private
 * no new public, protected or default-package code or data can be added to Critter
 */



public abstract class Critter {
	private static String myPackage;
	private	static List<Critter> population = new java.util.ArrayList<Critter>();
	private static List<Critter> babies = new java.util.ArrayList<Critter>();



	// Gets the package name.  This assumes that Critter and its subclasses are all in the same package.
	static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
	}
	
	private static java.util.Random rand = new java.util.Random();
	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}
	
	public static void setSeed(long new_seed) {
		rand = new java.util.Random(new_seed);
	}


	/* a one-character long string that visually depicts your critter in the ASCII interface */
	public String toString() { return ""; }
	
	private int energy = 0;
	protected int getEnergy() { return energy; }
	
	private int x_coord;
	private int y_coord;

	/**
	 *
	 * @param direction The direction we want to walk in
	 * This functions takes in the desired direction that a critter wants to walk in
	 * It then proceeds to change the x and y coordinates by a max value of 1 for the specific direction
	 */
	protected final void walk(int direction) {
		//this function will walk one unit based on the direction it was passed
		if(this.getEnergy() > Params.walk_energy_cost) {
			this.energy = this.getEnergy() - Params.walk_energy_cost;
			if(direction == 0){
				this.x_coord++;
				this.x_coord = this.x_coord % Params.world_width;		//modulo accounts for coordinate passing the border, seen in more instances further down
			}
			else if(direction == 1){
				this.x_coord++;
				this.y_coord--;
				this.x_coord = this.x_coord % Params.world_width;
				if(this.y_coord < 0){									//this is another check for passing the border, seen in further instances as well
					this.y_coord = (Params.world_height) + this.y_coord;
				}
			}
			else if(direction == 2) {
				this.y_coord--;
				if (this.y_coord < 0) {
					this.y_coord = (Params.world_height) + this.y_coord;
				}
			}
			else if(direction == 3){
				this.x_coord--;
				this.y_coord--;
				if(this.x_coord < 0){
					this.x_coord = (Params.world_width) + this.x_coord;
				}
				if (this.y_coord < 0) {
					this.y_coord = (Params.world_height) + this.y_coord;
				}
			}
			else if(direction == 4){
				this.x_coord--;
				if(this.x_coord < 0){
					this.x_coord = (Params.world_width) + this.x_coord;
				}
			}
			else if(direction == 5){
				this.x_coord--;
				this.y_coord++;
				if(this.x_coord < 0){
					this.x_coord = (Params.world_width) + this.x_coord;
				}
				this.y_coord = this.y_coord % Params.world_height;
			}
			else if(direction == 6){
				this.y_coord++;
				this.y_coord = this.y_coord % Params.world_height;
			}
			else if(direction == 7) {
				this.x_coord++;
				this.y_coord++;
				this.x_coord = this.x_coord % Params.world_width;
				this.y_coord = this.y_coord % Params.world_height;
			}
		}
		else {
			this.energy = this.getEnergy() - Params.rest_energy_cost;			//takes rest energy if it did not move
		}
		if (this.getEnergy() <= 0){												//checks for dead critters and removes from population
			population.remove(this);
		}
	}


	/**
	 *
	 * @param direction The direction we want to run in
	 * This functions takes in the desired direction that a critter wants to run in
	 * It then proceeds to change the x and y coordinates by a max value of 2 for the specific direction
	 */
	protected final void run(int direction) {
		//similar to walk but moves two spaces instead of 1
		//if enough energy to run, execute
		if(this.getEnergy() > Params.run_energy_cost) {
			this.energy = this.getEnergy() - Params.run_energy_cost;

			if (direction == 0) {
				this.x_coord += 2;
				this.x_coord = this.x_coord % Params.world_width;			//accounts for going past border
			} else if (direction == 1) {
				this.x_coord += 2;
				this.y_coord -= 2;
				this.x_coord = this.x_coord % Params.world_width;
				if (this.y_coord < 0) {
					this.y_coord = (Params.world_height) + this.y_coord;		//another check for border
				}
			} else if (direction == 2) {
				this.y_coord -= 2;
				if (this.y_coord < 0) {
					this.y_coord = (Params.world_height) + this.y_coord;
				}
			} else if (direction == 3) {
				this.x_coord -= 2;
				this.y_coord -= 2;
				if (this.x_coord < 0) {
					this.x_coord = (Params.world_width) + this.x_coord;
				}
				if (this.y_coord < 0) {
					this.y_coord = (Params.world_height) + this.y_coord;
				}
			} else if (direction == 4) {
				this.x_coord -= 2;
				if (this.x_coord < 0) {
					this.x_coord = (Params.world_width) + this.x_coord;
				}
			} else if (direction == 5) {
				this.x_coord -= 2;
				this.y_coord += 2;
				if (this.x_coord < 0) {
					this.x_coord = (Params.world_width) + this.x_coord;
				}
				this.y_coord = this.y_coord % Params.world_height;
			} else if (direction == 6) {
				this.y_coord += 2;
				this.y_coord = this.y_coord % Params.world_height;
			} else if (direction == 7) {
				this.x_coord += 2;
				this.y_coord += 2;
				this.x_coord = this.x_coord % Params.world_width;
				this.y_coord = this.y_coord % Params.world_height;
			}
		}
		else {
			this.energy = this.getEnergy() - Params.rest_energy_cost;		//rests if not enough energy to execute
		}
		if (this.getEnergy() <= 0){											//kills off dead critters that died in the process of running or resting
			population.remove(this);
		}
	}


	/**
	 *
	 * @param offspring Child that is created
	 * @param direction Direction we want to walk in
	 * This function takes an object called offspring (aka the baby we want to create) and the direction that we want to place this critter in
	 * It adds this offspring to the babies list
	 */
	protected final void reproduce(Critter offspring, int direction) {
		//this function reproduces a critter and adds it to "babies"
		int initialEnergy = this.getEnergy();
		//only reproduce if critter has enough energy
		if (this.getEnergy() < Params.min_reproduce_energy){
			this.energy = this.getEnergy() - Params.rest_energy_cost;
			if (this.energy <= 0){
				population.remove(this);
			}
			return;
		}
		//if it does have enough energy to reproduce, create a new critter
		else{
			//Get same coordinates as parent
			int x_parent = this.x_coord;
			int y_parent = this.y_coord;
			offspring.x_coord = x_parent;		//takes parents coordinates and walks in a specific direction to get one unit difference
			offspring.y_coord = y_parent;
			offspring.walk(direction);

			offspring.energy = (this.getEnergy() / 2);		//energy exchange occurs here
			this.energy = (this.getEnergy() + 1)/ 2;

			//Add the offspring into the babies list
			babies.add(offspring);
			return;
		}
	}

	public abstract void doTimeStep();
	public abstract boolean fight(String oponent);

	
	/**
	 * create and initialize a Critter subclass.
	 * critter_class_name must be the unqualified name of a concrete subclass of Critter, if not,
	 * an InvalidCritterException must be thrown.
	 * (Java weirdness: Exception throwing does not work properly if the parameter has lower-case instead of
	 * upper. For example, if craig is supplied instead of Craig, an error is thrown instead of
	 * an Exception.)
	 * @param critter_class_name Class name of critter
	 * @throws InvalidCritterException The exception
	 */
	public static void makeCritter(String critter_class_name) throws InvalidCritterException {
		//need to add the situation where critter_class is not a concrete subclass of Critter***
		try {
			Class c = Class.forName(critter_class_name);
			//new critter type created with random coordinates and pre-determined energy
			Critter v = (Critter) c.newInstance();
			v.energy = Params.start_energy;
			v.x_coord = getRandomInt(Params.world_width);
			v.y_coord = getRandomInt(Params.world_height);

			population.add(v);
		}
		catch (Exception e){
			throw new InvalidCritterException(critter_class_name);
		}
	}
	
	/**
	 * Gets a list of critters of a specific type.
	 * @param critter_class_name What kind of Critter is to be listed.  Unqualified class name.
	 * @return List of Critters. List
	 * @throws InvalidCritterException The exception
	 */
	public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {
		Class<?> classofCritter = null;

		try{
			classofCritter = Class.forName(myPackage + "." + critter_class_name);
		}
		catch (ClassNotFoundException e){
			throw new InvalidCritterException(critter_class_name);
		}

		List<Critter> result = new java.util.ArrayList<Critter>();
		for(Critter c: population){
			if(classofCritter.isInstance(c)){
				result.add(c);
			}
		}
		return result;
	}
	
	/**
	 * Prints out how many Critters of each type there are on the board.
	 * @param critters List of Critters.
	 */
	public static void runStats(List<Critter> critters) {
		System.out.print("" + critters.size() + " critters as follows -- ");
		java.util.Map<String, Integer> critter_count = new java.util.HashMap<String, Integer>();
		for (Critter crit : critters) {
			String crit_string = crit.toString();
			Integer old_count = critter_count.get(crit_string);
			if (old_count == null) {
				critter_count.put(crit_string,  1);
			} else {
				critter_count.put(crit_string, old_count.intValue() + 1);
			}
		}
		String prefix = "";
		for (String s : critter_count.keySet()) {
			System.out.print(prefix + s + ":" + critter_count.get(s));
			prefix = ", ";
		}
		System.out.println();		
	}
	
	/* the TestCritter class allows some critters to "cheat". If you want to 
	 * create tests of your Critter model, you can create subclasses of this class
	 * and then use the setter functions contained here. 
	 * 
	 * NOTE: you must make sure that the setter functions work with your implementation
	 * of Critter. That means, if you're recording the positions of your critters
	 * using some sort of external grid or some other data structure in addition
	 * to the x_coord and y_coord functions, then you MUST update these setter functions
	 * so that they correctly update your grid/data structure.
	 */
	static abstract class TestCritter extends Critter {
		protected void setEnergy(int new_energy_value) {
			super.energy = new_energy_value;
		}
		
		protected void setX_coord(int new_x_coord) {
			super.x_coord = new_x_coord;
		}
		
		protected void setY_coord(int new_y_coord) {
			super.y_coord = new_y_coord;
		}
		
		protected int getX_coord() {
			return super.x_coord;
		}
		
		protected int getY_coord() {
			return super.y_coord;
		}
		

		/*
		 * This method getPopulation has to be modified by you if you are not using the population
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.
		 */
		protected static List<Critter> getPopulation() {
			return population;
		}




		/*
		 * This method getBabies has to be modified by you if you are not using the babies
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.  Babies should be added to the general population 
		 * at either the beginning OR the end of every timestep.
		 */
		protected static List<Critter> getBabies() {
			return babies;
		}
	}

	/**
	 * Clear the world of all critters, dead and alive
	 */
	public static void clearWorld() {
		// Complete this method.
		//clears all critters from the respective arrays
		population.clear();
		babies.clear();
	}

	/**
	 * This function takes in no parameters however it completes the doTimeStep of every critter in the population list
	 * It then checks to see if any critters are in the same location by calling a function named "sameSpace"
	 */
	public static void worldTimeStep() {
		//this function will simulate one time step for all critters and then handle resulting conflicts
		int size = population.size();
		for(int i = 0; i < size ; i++){
			population.get(i).doTimeStep();
			int tempSize = population.size();
			if(size != tempSize){
				i--;
			}
			size = population.size();
		}

		sameSpace();
		//Create more algae
		for(int i = 0; i < Params.refresh_algae_count; i++){
			Critter moreAlgae = new Algae();
			moreAlgae.energy = Params.start_energy;
			moreAlgae.x_coord = getRandomInt(Params.world_width);
			moreAlgae.y_coord = getRandomInt(Params.world_height);
			babies.add(moreAlgae);
		}
		population.addAll(babies);
		babies.clear();				//Clear the babies list for next time step
	}


	/**
	 *
	 * @param x The x coord
	 * @param y The y coord
	 * @return true or false
	 * This function takes in an x and y coordinate of the desired location that a critter wants to go in
	 * It makes sure that the desired location we want to go in is free by checking through all the critters in the population arraylist and checks their x and y coordinates
	 */
	private static boolean isOccupied(int x, int y){
		//this function checks to see if a critter has the option to run to this location
		Critter tempCrit = null;
			System.out.println(population.size());
			for (int i = 0; i < population.size(); i++) {
				tempCrit = population.get(i);
				if(tempCrit == null){
					continue;
				}
				System.out.println(i);
				if (tempCrit.x_coord == x && tempCrit.y_coord == y) {
					return true;
				}
			}
		return false;
	}


	/**
	 * This function takes in nothing and returns nothing. The use of this function is to resolve the conflicts (if any) of critters that are in the same location
	 * Based on how each critter rolls the losing critter (if there is one) will be replaced with "null" in the population ArrayList and deleted after we go through every critter in the ArrayList
	 */
	public static void sameSpace() {
		//this function will check to see which conflicts need to be handles
		Critter critA = null;
		Critter critB = null;

		int size = population.size();
		for(int i = 0; i < size - 1; i++){
			if(population.get(i) == null){
				continue;
			}
			else {
				critA = population.get(i);
			}
			for(int j = i+1; j < size; j++){
				if(population.get(j) == null){
					continue;
				}
				else {
					critB = population.get(j);
				}
				if((critA.x_coord == critB.x_coord) && (critA.y_coord == critB.y_coord)){	///////////////////////////////////////////////////////////
					boolean choiceOfA = critA.fight(critB.toString());
					boolean choiceOfB = critB.fight(critA.toString());
					int rollA = 0;
					int rollB = 0;
					if(choiceOfA == true){
						rollA = getRandomInt(critA.energy);
					}
					if(choiceOfB == true){
						rollB = getRandomInt(critB.energy);
					}


					if((choiceOfA == false) && (critA instanceof Critter1)){
						critA.energy = 	critA.energy - Params.walk_energy_cost;
					}
					else if ((choiceOfA == false) && (critA instanceof Critter2)){
						critA.energy = critA.energy - Params.run_energy_cost;
					}
					else if ((choiceOfA == false) && (critA instanceof Critter3)){
						critA.energy = critA.energy - Params.walk_energy_cost;
					}
					else if ((choiceOfA == false) && (critA instanceof Critter4)){
						int temp_x = critA.x_coord;
						int temp_y = critA.y_coord - 2;
						if (temp_y < 0) {
							temp_y = (Params.world_height) + temp_y;
						}
						if(!(isOccupied(temp_x,temp_y))) {
							critA.run(2);
						}
						else {
							critA.energy = critA.energy - Params.run_energy_cost;
						}
					}
					else if ((choiceOfA == false) && (critA instanceof Algae)){
						critA.energy = critA.energy - Params.rest_energy_cost;
					}



					if((choiceOfB == false) && (critB instanceof Critter1)){
						critB.energy = 	critB.energy - Params.walk_energy_cost;
					}
					else if ((choiceOfB == false) && (critB instanceof Critter2)){
						critB.energy = critB.energy - Params.run_energy_cost;
					}
					else if ((choiceOfB == false) && (critB instanceof Critter3)){
						critB.energy = critB.energy - Params.walk_energy_cost;
					}
					else if ((choiceOfB == false) && (critB instanceof Critter4)){
						int temp_x = critB.x_coord;
						int temp_y = critB.y_coord - 2;
						if(!(isOccupied(temp_x,temp_y))) {
							critB.run(2);
						}
						else {
							critB.energy = critB.energy - Params.run_energy_cost;
						}
					}
					else if ((choiceOfB == false) && (critB instanceof Algae)){
						critB.energy = critB.energy - Params.rest_energy_cost;
					}



					if (rollA > rollB){		//A wins and B dies (B is converted to null)
						critA.energy = critA.energy + (critB.energy/2);
						population.set(j, null);
					}
					else if (rollB > rollA){	//B wins and A dies (A is converted to null)
						critB.energy = critB.energy + (critA.energy/2);
						population.set(i, null);
					}
					else if (rollA == rollB){	//A wins and B dies (B is converted to null)
						critA.energy = critA.energy + (critB.energy/2);
						population.set(j, null);
					}
				}
			}
		}
		int tempSize = population.size();			//kill the critters who got killed
		for(int i = 0; i < tempSize; i++){
			if(population.get(i) == null){
				population.remove(i);
				i--;
			}
			tempSize = population.size();
		}
	}

	/**
	 * This function outputs the grid
	 * It takes in no input
	 * It goes through each coordinate pair and compares it against all the critters in the popoulation ArrayList and prints out only 1 of the occurrences at a specific location
	 */
	public static void displayWorld() {

		boolean flag = false;
		// This part of the code will print the basic grid and critters of the world
		System.out.print("+");
		for(int i = 0; i < Params.world_width; i++){	//make sure this is the correct width
			System.out.print("-");
		}
		System.out.println("+");
		for(int i = 0; i < Params.world_height; i++){
			System.out.print("|");
			for(int j = 0; j < Params.world_width; j++){
				for(int x = 0; x < population.size(); x++){
					if((population.get(x).x_coord == j) && (population.get(x).y_coord == i)){	//double check the y coordinate part
						System.out.print(population.get(x).toString());
						flag = true;
						break;
					}
				}
				if(flag == false){
					System.out.print(" ");
				}
				flag = false;

			}
			System.out.println("|");
		}
		System.out.print("+");
		for(int i = 0; i < Params.world_width; i++){
			System.out.print("-");
		}
		System.out.println("+");


	}
}
