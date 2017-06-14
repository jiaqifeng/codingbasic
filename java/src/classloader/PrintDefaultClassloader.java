public class PrintDefaultClassloader {

    public static void main(String[] args) {
        ClassLoader cl = Persion.class.getClassLoader();
        System.out.println("Persion's ClassLoader is:"+cl.toString());
	System.out.println("Persion's ClassLoader parent is:"+cl.getParent().toString());
	System.out.println("Persion's ClassLoader parent's parent is null");//cl.getParent().getParent().toString());
	
	cl = int.class.getClassLoader();
	System.out.println("you should see NullPointerException");
        System.out.println("int's ClassLoader is:"+cl.toString());
    }
}
