.SUFFIXES: .java .class
JC=javac

comp: Queue.class
	@echo Compiling.
	@$(JC) Simulator.java Store.java Simulation.java Customer.java CustomerTest.java Register.java Queue.java

run: comp
	@echo Running.
	@java Simulator 3 0.5 1 90 900

clean:
	@echo Cleaning.
	@rm -f *.class
	@rm -f *.#
	@rm -f *.*#
	@rm -f .*~

Queue.class: Queue.java
	@$(JC) $^

test_q: Queue_test.java
	@$(JC) $^
	@java Queue_test

Queue_test.java: Queue.java Register.java Store.java
	@$(JC) $^

cr: clean comp run
