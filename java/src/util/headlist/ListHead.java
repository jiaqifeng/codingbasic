package util.headlist;

/*
 * Any Class used HeadLinkedList should implement this.
 */
interface ListHead<T> {
    T getEntry();
    void setEntry(T entry);
    /*
     * copy below code to you class for convinient
    private HeadLinkedList.Entry<T> entry;
    HeadLinkedList.Entry<T> getEntry() {return this.entry;}
    boolean setEntry(HeadLinkedList.Entry<T> e) {this.entry=e};
    */
}
