public class Queue{
    private String elms[];
    
    Queue (){
	elms = new String[0];
    }

    public void add_element(String name){
	resize(elms.length+1);
	elms[elms.length-1]= name;
    }

    public String toString(){
	String s = "";
	for (int i = 0; i<elms.length; i++){
	    s+="   "+i+". "+elms[i]+'\n';
	}
	if (s=="") return "   Empty\n";
	return s;
    }

    public final int size(){
	return elms.length;
    }

    private void resize(int size){
	size = Math.abs(size);
	String[] newelms = new String[size];
	for (int i = 0; i<Math.min(size,elms.length); i++){
	    newelms[i] = elms[i];
	}
	elms = newelms;
    }

    public String pop() {
	String head = head();
	for (int i = 0; i<elms.length-1; i++){
	    elms[i]=elms[i+1];
	}
	resize(size()-1);
	return head;
    }

    public final String head(){
	if (size()<=0) return null;
	return elms[0];
    }
}
