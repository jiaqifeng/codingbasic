/**
 * static block and static field depends their's seq in code
 */
public class StaticInitTest {

    static {
	static_call();
    }
    private static final String staticstr=init();

    static String init() {
	System.out.println("in init() called to init static final field");
	return "init"+String.valueOf(2);
    }
    static void static_call() {
	System.out.println("in static_call() of static block");
    }
    public static void main(String[] args) {
	System.out.println("in main");
    }
}

    
