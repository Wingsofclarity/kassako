public class Glutton extends Customer{
    private double gluttony = 0.2;
    private double hunger = 0;
    private int eaten = 0;

    Glutton (boolean rand, int max_wares){
	super(rand, max_wares);
	//add_to_name(" glutton.");
    }
    
    public void incWait(){
	super.incWait();
	if (hunger>Math.random()){
	    eat();
	}
	else {
	    hunger+=(gluttony*0.5);
	}
    }

    private void eat(){
	if (numWares()>1){
	    set_numWares(numWares()-1);
	    eaten++;
	    hunger = 0;
	}
    }

    public String toString(){
	return super.toString() +" (glutton "+eaten+") ";
    }
}
