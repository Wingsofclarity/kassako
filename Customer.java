public class Customer {
    private static int numCus = 0;
    private String name;
    private int wares = 0;
    private int waitTime = 0;
    
    Customer(){
	name = "Customer "+numCus;
	wares = 0;
	numCus++;
    }

    Customer(boolean rand, int max_wares){
	this();
	if (rand) randomize_wares(1, max_wares);
    }

    public void randomize_wares(int a, int b){
	wares = randomWithRange(a,b);
    }

    private final int randomWithRange(int min, int max)
    {
	int range = (max - min) + 1;     
	return (int)(Math.random() * range) + min;
    }

    public String toString(){
	return name+" has "+wares+" wares. Waited: "+waitTime;
    }

    public void takeWare(){
	wares--;
    }

    public void set_numWares(int a){
	if (a<0) a=0;
	wares=a;
    }

    public final int numWares(){
	return wares;
    }

    public void incWait(){
	waitTime++;
    }

    public final int waitTime(){
	return waitTime;
    }

    public void add_to_name(String s){
	name+=s;
    }
}
