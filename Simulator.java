public class Simulator{
    //Simulator Registers, Growth, Overload limit, Max wares, Steps.
    public static void main(String[] args) throws InterruptedException {
	System.out.println("Regsiters set to: "+args[0]);
	System.out.println("Growth set to: "+args[1]);
	System.out.println("Overload limit set to: "+args[2]);
	System.out.println("Max wares set to: "+args[3]);
	System.out.println("Steps set to: "+args[4]);

	int steps = Integer.parseInt(args[4]);
	Simulation s = new Simulation(Integer.parseInt(args[0]),
				      Double.parseDouble(args[1]),
				      Integer.parseInt(args[2]),
				      Integer.parseInt(args[3]));
	
	
	for(int i = 0; i < steps; i++){
	    s.step();
	    Thread.sleep(1500);
	    //Runtime.getRuntime().exec("cls");
	    System.out.println(s);
	}
	
	System.out.println("Finnishing.");
	while (!s.finnish()){

	}

	System.out.println("Final store: ");
	System.out.println(s);
	System.out.println("");
	System.out.println("Simulation finished!");
    }
}
