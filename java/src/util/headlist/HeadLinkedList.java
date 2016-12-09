package util.headlist;

/* 
 * This provided an emulation of linux list_head.
 * Key difference to Java LinkedList is to provide O(1) remove operation.
 */

class HeadLinkedList<T> {
    Entry list;

    public void HeadLinkedList() {
    }

    public void addFirst(T t) {
    }

    public void addLast(T t) {
    }

    public class Entry {
	Entry next;
	Entry prev;
	Entry first;
	ListHead<T> data;
    }
}
