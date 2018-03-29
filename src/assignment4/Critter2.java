package assignment4;
/*
Critter2 always runs in direction 3 (given it has enough energy)
The fight method for Critter2 will return true if its energy is more than 6
Otherwise Critter2 will not fight and will be charged with a resting penalty
*/

public class Critter2 extends Critter{

    boolean hasMoved = false;

    @Override
    public void doTimeStep() {
        hasMoved = false;
        run(3);
        hasMoved = true;
    }

    @Override
    public boolean fight(String opponent) {
        if (getEnergy() > 6){
            return true;
        }
        else {
            return false;
        }
    }

    public String toString() {
        return "2";
    }

    public boolean isHasMoved() {
        return hasMoved;
    }
}
