public class Store {
    private String name = "Unknown store";
    private Register rs[];
    private int overloadLimit = 4;
    private Queue<Customer> outskirt;
    private int entered = 0;
    private int exited = 0;
    private int total_wait = 0;
    private int max_wait = 0;
    

    Store(){
	this(5,4);
    }

    Store (int numRs, int overloadLimit){
	rs = new Register[numRs];
	for (int i = 0; i<numRs; i++){
	    rs[i] = new Register();
	}
	outskirt = new Queue<Customer>();
	this.overloadLimit = overloadLimit;
    }
    
    public void step(){

	//Register work.
	for (int i = 0; i<rs.length; i++) {
	    rs[i].incWait();
	    if (rs[i].head()==null) {}
	    else if (rs[i].head().numWares()>0){
		rs[i].head().takeWare();
	    }
	    else {
		Customer leaving = rs[i].pop();
		total_wait+=leaving.waitTime();
		if (max_wait < leaving.waitTime()){
		    max_wait = leaving.waitTime();
		}
		exited++;
	    }
	}

	//Customer movment
	moveCustomers();

	//Register management.
	if (isOverloaded((double) numCustomers(), (double) numOpenReg())) {
	    openRegister();
	}
	else if (isUnderloaded((double) numCustomers(), (double) numOpenReg())) {
	    closeRegister();
	}
    }

    private void moveCustomers() {
	Register r = shortOpen();
	if (r==null) return;
	while (numOpenReg()>0 && outskirt.size()>0){
	    shortOpen().push(outskirt.pop());
	}

	for (int i = 0; i<rs.length; i++){
	    r = shortOpen();
	    if (rs[i]!=r){
		while (isOverloaded(rs[i].size(),r.size()+1)){
		    r.push(rs[i].take_last());
		}
	    }
	}
    }

    private void openRegister(){
	for (int i = 0; i<rs.length; i++) {
	    if (!rs[i].isOpen()){
		rs[i].open();
		return;
	    }
	    
	}
    }

    private void closeRegister(){
	Register r = shortOpen();
	if (r==null) return;
	r.close();
    }

    private final boolean isOverloaded(double t, double n){
	if (t>0 && n==0) return true;
	else if (t<=0 && n==0) return false;
	else return ((t/n)>overloadLimit);
    }
    
    private final boolean isUnderloaded(double t, double n){
	if (t>0 && n<=1) return false;
	else return ((t/n)*(overloadLimit/2)<overloadLimit);
    }
    
    public void add_customer(Customer cus) {
	Register a = shortOpen();
	entered++;
	if (a==null) {
	    outskirt.add_element(cus);
	    return;
	}
	a.add_element(cus);
    }

    public final String toString(){
	String s = '\n'+name+" has "+rs.length+" registers.\n";
	for (int i = 0; i<rs.length; i++) {
	    s += "Register "+i;
	    
	    if (rs[i].isOpen()) s+=" is open.";
	    else s+=" is closed.";
	    
	    s += "\n"+rs[i].toString() + '\n';
	}
	s+="\nOutskirt is \n"+ outskirt.toString()+'\n';
	return s;
    }

    private final Register shortRegister(){
	if (rs.length <= 0) return null;
	Register a = rs[rs.length-1];
	for (int i = rs.length-2; i>0; i--) {
	    if (rs[i].size()<a.size()){
		a = rs[i];
	    }
	}
	return a;
    }

    private final Register shortOpen(){
	if (rs.length <= 0) return null;
	Register a = null;
	boolean foundone = false;
	
	for (int i = rs.length-1; i>=0; i--) { 
	    if ((rs[i].isOpen() && !foundone) ||
		(rs[i].isOpen() && rs[i].size()<a.size())){
		a = rs[i];
		foundone = true;
	    }
	}
	return a;
    }

    public final int numOpenReg(){
	int a = 0;
	for (int i = 0; i<rs.length; i++) {
	    if (rs[i].isOpen()) a++;
	}
	return a;
    }

    public final int numCustomers(){
	int a = 0;
	for (int i = 0; i<rs.length; i++) {
	    a+=rs[i].size();
	}
	a+=outskirt.size();
	return a;
    }

    public final int numVisits(){
	return entered;
    }

    public final int leftOvers(){
	return entered - exited;
    }
    public void setOverloadLimit(int a){
	if (a<1) a=1;
	overloadLimit = a;
    }
    public final int max_wait(){
        return max_wait;
    }
    public final int total_wait(){
	return total_wait;
    }
    public final int entered(){
	return entered;
    }
    public final int exited(){
	return exited;
    }
	
}
