public class Persion {
    Persion parent;
    Object handle;

    public void setHandle(Object handle) {
	this.handle=handle;
	System.out.println("set handle successed");
    }
    
    public void setParent(Object parent) {
	this.parent=(Persion)parent;
    }
    
    public void say() {
	System.out.println("this persion.say()");
    }
}
