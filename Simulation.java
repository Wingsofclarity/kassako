public class Simulation {
    Store s;
    double growth = 0;
    double curgrowth = 0;
    int peak_cus = 0;
    int peak_reg = 0;
    
    int a = 0;

    Simulation (int regnum, double growth){
	this.growth=growth;
	s = new Store(regnum);
    }
    
    public void step(int a){
	a = Math.abs(a);
	while (a >0){
	    step();
	    a--;
	}
    }
    
    public void step() {
	curgrowth+=growth;
	while (curgrowth>=1){
	    Customer cus = new Customer(true);
	    s.add_customer(cus);
	    curgrowth--;
	    a++;
	}
	s.step();

	if (s.numCustomers() > peak_cus){
	    peak_cus=s.numCustomers();
	}
	if (s.numOpenReg() > peak_reg){
	    peak_reg=s.numOpenReg();
	}
    }
    
    public String toString() {
	return s.toString()+"Peak customers: "+peak_cus+
	    "\nPeak open registers: "+peak_reg+'\n';
    }
}
