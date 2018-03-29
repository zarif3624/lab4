package assignment4;
/*
Critter1 always walks in direction 2 (given it has enough energy)
The fight method for Critter1 will return true if its energy is more than 10
Otherwise Critter1 will not fight and will be charged with a resting penalty
*/

public class Critter1 extends Critter {

    boolean hasMoved = false;

    @Override
    public void doTimeStep() {
        hasMoved = false;
        walk(2);
        hasMoved = true;
    }

    @Override
    public boolean fight(String opponent) {
        if (getEnergy() > 10){
            return true;
        }
        else{
            return false;
        }
    }

    public String toString() {
        return "1";
    }

    public boolean is2HasMoved() {
        return hasMoved;
    }
}
