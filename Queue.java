public class Queue <ET>{
    private Node<ET> first = null;
    private Node<ET> last = null;

    private class Node<ET>{
	ET elm;
	Node<ET> next=null;

	Node(ET elm){
	    this.elm=elm;
	}

	public String toString(){
	    return elm.toString();
	}
    }
    
    Queue (){
    }

    public void add_element(ET elm){
	Node<ET> newLast =  new Node<ET>(elm);

	if (size()!=0 && last==null) {
	    System.out.println("Debug critcal error. add_element");
	    return;
	}
	if (size()==0){
	    first = newLast;








	}
	else {
	    if (last==null){}
	    last.next = newLast;
	}
	last = newLast;
    }

    public void push(ET elm){
	add_element(elm);
    }

    public ET take_first() {
	if (first==null) return null;
	ET elm = first.elm;
	first = first.next;
	if (first==null) last=null;
	return elm;
    }

    public ET pop(){
	return take_first();
    }

    public ET take_last(){
	if (last==null) return null;
	ET elm = last.elm;

	Node<ET> curnode = first;
	if (curnode.next!=null){
	    while (curnode.next.next!=null){
		curnode=curnode.next;
	    }
	    last = curnode;
	    curnode.next=null;
	}
	else {
	    last = null;
	    first = null;
	}
	return elm;
    }
    public final ET head(){
	if (first == null) return null;
	return first.elm;
    }
    
    public final String toString(){
	return toString("    ", "\n");
    }

    public final String toString(String prefix, String subfix){
	if (size()==0) return prefix+"'Empty'"+subfix;
	Node<ET> curNode = first;
	String s = prefix+curNode.toString() + subfix;
	while (curNode.next!=null) {
	    curNode=curNode.next;
	    s+=prefix+curNode.toString() + subfix;
	}
	return s;
    }
    
    public final int size(){
	Node<ET> curNode = first;
	if (first==null) return 0;
	int i = 1;
	while (curNode.next!=null) {
	    curNode=curNode.next;
	    i++;
	}
	return i;
    }

    public void forall(ForAllHandler<ET> f){
	Node<ET> curNode = first;
	if (first==null) return;
	while (curNode!=null) {
	    f.handle(curNode.elm);
	    curNode=curNode.next;
	}
    }

    public void test(){
	forall(new ForAllHandler<ET>() {
		public void handle(ET elem) {
		    System.out.println(elem);
		} 
	});
    }

    public static interface ForAllHandler<ET> {
	public void handle(ET elem);
    }
}
