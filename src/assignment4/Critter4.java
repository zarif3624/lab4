package assignment4;
/*
Critter4 will try to reproduce and create one offspring (given it has enough energy)
The offspring created will be located in one unit above the parent
The fight method for Critter4 will return true if its energy is more than 7
Otherwise Critter4 will not fight and will run in direction 2
*/
public class Critter4 extends Critter{

    boolean hasMoved = false;

    @Override
    public void doTimeStep() {
        hasMoved = false;
        Critter baby = new Critter4();
        reproduce(baby, 2);
    }

    @Override
    public boolean fight(String opponent) {
        if (getEnergy() > 7){
            return true;
        }
        else {
            return false;
        }
    }

    public String toString() {
        return "4";
    }

    public boolean isHasMoved() {
        return hasMoved;
    }
}
