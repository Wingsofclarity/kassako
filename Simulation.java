public class Simulation {
    private Store s;
    private double growth = 0;
    private int peak_cus = 0;
    private int peak_reg = 0;
    private int entered = 0;
    private int exited = 0;
    private int total_wait = 0;
    private int max_wait = 0;
    private int max_wares = 1;

    Simulation (int regnum, double growth, int overloadLimit, int max_wares){
	if (regnum<0) regnum=0;
	if (growth<0) growth=0;
	this.growth=growth;
	s = new Store(regnum, overloadLimit);
	this.max_wares=max_wares;
    }
    
    public void step(int a){
	a = Math.abs(a);
	while (a >0){
	    step();
	    a--;
	}
    }
    
    public void step() {
	if (Math.random()<=growth){
	    if (Math.random()<=0.3334){
		Glutton glutt = new Glutton(true, max_wares);
		s.add_customer(glutt);
	    }
	    else {
		Customer cus = new Customer(true, max_wares);
		s.add_customer(cus);
	    }
	}
	s.step();
	update_values(s);
    }

    public void update_values(Store s){
	if (s.numCustomers() > peak_cus){
	    peak_cus=s.numCustomers();
	}
	if (s.numOpenReg() > peak_reg){
	    peak_reg=s.numOpenReg();
	}
	max_wait=s.max_wait();
	total_wait=s.total_wait();
	entered=s.entered();
	exited=s.exited();
	if (entered-exited!=s.numCustomers()){
	    System.out.println("Entered: "+entered);
	    System.out.println("Exited: "+exited);
	    System.out.println("numCustomers(): "+s.numCustomers());
	    System.out.println(s);
	    System.out.println("Crash!");
	    int a = 1/0;
	}
    }

    public final int average_wait(){
	if (exited==0) return 0;
	return total_wait/exited;
    }
    
    public String toString() {
	return s.toString()+
	    "Peak customers: "+peak_cus+'\n'+
	    "Peak open registers: "+peak_reg+'\n'+
	    "Total customers passed: "+exited+'\n'+
	    "Average wait time: "+average_wait()+'\n'+
	    "Peak wait time: "+max_wait+'\n'+
	    "Customers still in system at exit: "+(entered-exited)+'\n';
    }

    public void set_growth(double growth){
	this.growth=growth;
    }

    public boolean finnish(){
	if (s.numCustomers()>0){
	    s.step();
	    update_values(s);
	    return false;
	}
	return true;
    }
}
