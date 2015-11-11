public class Simulator{

    public static void main(String[] args) throws InterruptedException {
        int steps = 50;
        Simulation s = new Simulation(5, 1);
        for(int i = 0; i < steps; i++){
            s.step();
            Thread.sleep(0);
	    //Runtime.getRuntime().exec("cls");
        }
	System.out.println(s);
        System.out.println("");
        System.out.println("Simulation finished!");
	
    }
}
