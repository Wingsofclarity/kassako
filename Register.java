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

    public void work(){
	if (head()==null) return;
	if (head().numWares()>0){
	    head().takeWare();
	}
	else pop();
    }
}
