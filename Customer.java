public class Customer {
    private static int numCus = 0;
    private String name;
    private int wares = 0;
    
    Customer(){
	name = "Customer "+numCus;
	wares = 0;
	numCus++;
    }

    Customer(boolean rand){
	this();
	if (rand) randomize_wares();
    }

    public void randomize_wares(){
	wares = randomWithRange(1,20);
    }

    private int randomWithRange(int min, int max)
    {
	int range = (max - min) + 1;     
	return (int)(Math.random() * range) + min;
    }

    public String toString(){
	return name+" has "+wares+" wares.";
    }

    public void takeWare(){
	wares--;
    }

    public int numWares(){
	return wares;
    }
}
