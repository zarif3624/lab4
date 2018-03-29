package assignment4;
/*
Critter3 always walks in direction 5 (given it has enough energy)
The fight method for Critter3 will return true if its energy is more than 4
Otherwise Critter3 will not fight and will be charged with a resting penalty
*/

public class Critter3 extends Critter{

    boolean hasMoved = false;

    @Override
    public void doTimeStep() {
        hasMoved = false;
        walk(5);
        hasMoved = true;
    }

    @Override
    public boolean fight(String opponent) {
        if (getEnergy() > 4){
            return true;
        }
        else {
            return false;
        }
    }

    public String toString() {
        return "3";
    }

    public boolean isHasMoved() {
        return hasMoved;
    }
}
