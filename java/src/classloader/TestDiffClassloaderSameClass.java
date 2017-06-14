import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestDiffClassloaderSameClass {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        //创建自定义classloader对象。
        DiskClassLoader diskLoader1 = new DiskClassLoader("./copyclass");
	DiskClassLoader diskLoader2 = new DiskClassLoader("./copyclass");

        try {
            //加载class文件
	    System.out.println("---- start to load Persion by 1 ----");
            Class c1 = diskLoader1.loadClass("Persion");
	    System.out.println("---- start to load Persion by 2 ----");
            Class c2 = diskLoader2.loadClass("Persion");

	    Object p1 = c1.newInstance();
	    System.out.println("---- Persion 1 's class is "+p1.getClass().toGenericString()+","+p1.getClass().hashCode()+" ----");
	    Object p2 = c2.newInstance();
	    System.out.println("---- Persion 2 's class is "+p2.getClass().toGenericString()+","+p2.getClass().hashCode()+" ----");
	    
	    System.out.println("---- same class loaded by 2 class loader, You should see it successed ----");
	    c1.getMethod("setHandle", Object.class).invoke(p1, p2);
	    
	    System.out.println("---- same class loaded by 2 class loader, You should see ClassCastException ----");
	    c1.getMethod("setParent", Object.class).invoke(p1, p2);
	    
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
