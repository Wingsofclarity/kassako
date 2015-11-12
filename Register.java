public class Register extends Queue<Customer>{
    private boolean isOpen = false; 
    
    Register(){
	super();
    }

    public void setStatus(boolean b){
	this.isOpen = b;
    }

    public void open(){
	setStatus(true);
    }
    
    public void close(){
	setStatus(false);
    }

    public final boolean isOpen(){
	return isOpen;
    }

    public void incWait(){
	forall(new ForAllHandler<Customer>() {
		public void handle (Customer elm){
		    elm.incWait();
		}
	    });
    }
}
