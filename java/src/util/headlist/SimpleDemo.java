package util.headlist;

class SimpleDemo {
    static class IntegerListHead implements ListHead<HeadLinkedList<IntegerListHead>.Entry> {
	private HeadLinkedList<IntegerListHead>.Entry entry;
	public HeadLinkedList<IntegerListHead>.Entry getEntry() {return this.entry;}
	public void setEntry(HeadLinkedList<IntegerListHead>.Entry entry) {this.entry=entry;};

	int data;
	IntegerListHead () {data=0;}
	IntegerListHead(int d) {data=d;};
	public String toString() {return String.valueOf(data);}
    }
    public static void main(String[] args) {
	HeadLinkedList<IntegerListHead> list=new HeadLinkedList<IntegerListHead>();
	IntegerListHead item=new IntegerListHead(5);
	list.addFirst(item);
	list.addFirst(new IntegerListHead(3));
	System.out.println("new (5) is "+item);
    }
}




