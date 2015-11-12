public class Queue_test {
    public static void main (String[] args){
	Queue<String> q = new Queue<String>();
	for (int i = 0; i<100; i++){
	    q.push("Customer "+ (i+1));

	}
	for(int i = 0; i<90; i++){
	    q.take_last();
	}
	q.test();
	System.out.println(q.toString());
	Register r = new Register();
	Store s = new Store();
    }
}
