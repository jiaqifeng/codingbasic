package lfuCache;

/*
 *  460. LFU Cache

    Total Accepted: 1051
    Total Submissions: 5614
    Difficulty: Hard
    Contributors: 1337c0d3r , fishercoder

Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following operations: get and set.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
set(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, it
 should invalidate the least frequently used item before inserting a new item. For the purpose of this problem,
 when there is a tie (i.e., two or more keys that have the same frequency), the least recently used key would be evicted.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LFUCache cache = new LFUCache( 2 // capacity

import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;

);

        cache.set(1, 1);
        cache.set(2, 2);
        cache.get(1);       // returns 1
        cache.set(3, 3);    // evicts key 2
        cache.get(2);       // returns -1 (not found)
        cache.get(3);       // returns 3.
        cache.set(4, 4);    // evicts key 1.
        cache.get(1);       // returns -1 (not found)
        cache.get(3);       // returns 3
        cache.get(4);       // returns 4

 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jack on 16-12-2.
 */
public class LFUCache {
    static final boolean debug=true;
    int capacity;
    Map<Integer, Entry> map;
    Node nodeList;

    public LFUCache(int capacity) {
        this.capacity=capacity;
        map=new HashMap<Integer, Entry>(capacity);
    }

    public int get(int key) {
        Entry entry=map.get(key);
        if (entry==null) return -1;
        entry.update();
        return entry.value;
    }

    public void set(int key, int value) {
        if (capacity<1) return;
        Entry entry=map.get(key);
        if (entry!=null) {
            // set exist key, do not set frequency to 0
            entry.value = value;
            entry.update();
            return;
        }
        if (map.size() == capacity) {
            entry=popEntry();
            //if (debug) printCache("after pop, entry="+entry);
            map.remove(entry.key);
        }
        entry=new Entry(key, value);
        Node node;
        if (nodeList==null) {
            node=new Node(0, entry);
            nodeList=(Node)node.addToListAsLast(nodeList);
        } else if (nodeList.frequency==0) {
            entry.addToListAsLast(nodeList.entry);
            node=nodeList;
        } else {
            node=new Node(0, entry);
            nodeList=(Node)node.addToListAsFist(nodeList);
        }
        entry.setHeadNode(node);
        map.put(key,entry);
    }

    class AbstractDLNode {
        AbstractDLNode next;
        AbstractDLNode prev;
        AbstractDLNode() {
            prev=this;
            next=this;
        }
        // append node next to this
        void addNext(AbstractDLNode node) {
            node.next=this.next;
            node.prev=this;
            this.next.prev=node;
            this.next=node;
        }
        // add node prev to this
        void addPrev(AbstractDLNode node) {
            node.next=this;
            node.prev=this.prev;
            this.prev.next=node;
            this.prev=node;
        }
        // remove this from head
        AbstractDLNode removeFromList(AbstractDLNode list) {
            if (list==null)
                return list;
            if (list==this) {
                if (this.next==this) {
                    list=null;
                } else {
                    list = list.next;
                }
            }
            next.prev=prev;
            prev.next=next;
            this.next=this;
            this.prev=this;
            return list;
        }
        // add this as last of head
        AbstractDLNode addToListAsFist(AbstractDLNode list) {
            if (list!=null) {
                list.addPrev(this);
                return this;
            }
            this.next=this;
            this.prev=this;
            return this;
        }
        // add this as first of head
        AbstractDLNode addToListAsLast(AbstractDLNode list) {
            if (list!=null) {
                list.addPrev(this);
                return list;
            }
            this.next=this;
            this.prev=this;
            return this;
        }
    }
    private class Entry extends AbstractDLNode{
        public int key;
        public int value;
        Node headNode;// fast move to next frequency list

        Entry(int key, int value) {
            super();
            this.key=key;
            this.value=value;
            headNode =null;
        }
        void setHeadNode(Node head) {
            this.headNode=head;
        }
        //I forgot update this.headNode so ... I fix it at last
        void update() {
            int frequency=headNode.frequency;
            Node head=headNode;
            Node nodeAfterHead=(Node)head.next;

            headNode.entry=(Entry)removeFromList(headNode.entry);

            // remove current node if it's empty, this should done at last
            if (headNode.entry==null) {
                if (head==nodeList) {
                    nodeList = (Node) headNode.removeFromList(nodeList);
                    head=nodeList;
                } else {
                    head = (Node) head.prev;
                    nodeList = (Node) headNode.removeFromList(nodeList);
                }
            }

            if (nodeList==null) { // this is impossible
                Node newNode=new Node(frequency+1, this);
                nodeList=newNode;
                this.headNode=newNode;
                return;
            }

            if (nodeAfterHead.frequency==frequency+1) {
                if (debug) System.out.println("find frequency="+frequency+"+1 node");
                addToListAsLast(nodeAfterHead.entry);
                this.headNode=nodeAfterHead;
            } else {
                if (debug) System.out.println("add new frequency="+frequency+" node after");
                Node newNode=new Node(frequency+1, this);
                this.next=this;
                this.prev=this;
                this.headNode=newNode;
                head.addNext(newNode);
            }
        }
    }

    private class Node extends AbstractDLNode { // last recently used list, all elements in headNode has same frequency
        public Entry entry;
        int frequency;
        Node(int frequency, Entry entry) {
            super();
            this.entry=entry;
            this.frequency=frequency;
            entry.headNode=this;
        }
    }
    Entry popEntry() {
        if (nodeList==null)
            return null;
        Node node=nodeList;
        Entry entry=node.entry;
        //if (debug) System.out.println("get entry("+entry.key+","+entry.value);
        node.entry=(Entry)entry.removeFromList(node.entry);
        if (node.entry==null)
            nodeList=(Node)node.removeFromList(nodeList);
        return entry;
    }
    //----------------------------------------------- above could copy to leetcode directly

    void printCache(String message) {
        Node node=nodeList;
        if (node==null) {
            System.out.println("-- cache empty -- "+message);
            return;
        }
        System.out.print("-- cache -- " + message);
        int i,total=0;
        do {
            Entry entry=node.entry;
            i=0;
            System.out.format("\nfreq=%2d:", node.frequency);
            if (entry==null) {
                node=(Node)node.next;
                continue;
            }
            //count entries
            do {
                entry=(Entry)entry.next;i++;
            } while (entry!=node.entry);
            System.out.format("%3d entry", i);

            do {
                System.out.format(" (%4d,%4d)", entry.key, entry.value);
                entry=(Entry)entry.next;
            } while (entry!=node.entry);
            node=(Node)node.next;
            total+=i;
        } while (node!=nodeList);
        System.out.println("\n-- cache end--, total "+total+" entry, total "+map.size()+" in map");
    }
    static boolean assertEq(Object expect, Object value, String message) {
        if (!expect.equals(value)) {
            System.out.println("Assert failure expect " + expect + " but get " + value + ", " + message);
            return false;
        } else {
            System.out.println("test " + message + " passed.");
            return true;
        }
    }

    public static void main4(String[] args) {
        LFUCache cache=new LFUCache(1);
        cache.set(2,1);if (debug) cache.printCache("after set(2,1)");
        assertEq(1, cache.get(2), "case get(2)"); if (debug) cache.printCache("after get(2)");
        cache.set(3,2);if (debug) cache.printCache("after set(3,2)");
        assertEq(-1, cache.get(2), "case get(2)"); if (debug) cache.printCache("after get(2)");
        assertEq(2, cache.get(3), "case get(3)"); if (debug) cache.printCache("after get(3)");
    }
    public static void failed2(String[] args) {
        LFUCache cache=new LFUCache(0);
        cache.set(0,0);if (debug) cache.printCache("after set(0,0)");
        assertEq(0, cache.get(0), "case get(0)");
    }
    public static void failed1(String[] args) {
        LFUCache cache=new LFUCache(2);
        if (debug) cache.printCache("when empty");
        cache.set(1, 1); if (debug) cache.printCache("after set(1,1)");
        cache.set(2, 2); if (debug) cache.printCache("after set(2,2)");
        cache.set(2, 4); if (debug) cache.printCache("after set(2,4)");
        assertEq(1, cache.get(1), "case 1");  if (debug) cache.printCache("after get(1)");     // returns 1
        cache.set(3, 3);                      if (debug) cache.printCache("after set(3,3)");   // evicts key 2
        assertEq(-1, cache.get(2), "case 3"); if (debug) cache.printCache("after get(2)");      // returns -1 (not found)
        assertEq(3, cache.get(3), "case 5");   if (debug) cache.printCache("after get(3)");    // returns 3.
        cache.set(4, 4);                       if (debug) cache.printCache("after set(4,4)");   // evicts key 1.
        assertEq(-1, cache.get(1), "case 6");  if (debug) cache.printCache("after get(1)");     // returns -1 (not found)
        assertEq(3, cache.get(3), "case 7");   if (debug) cache.printCache("after get(3)");    // returns 3
        assertEq(4, cache.get(4), "case 9");   if (debug) cache.printCache("after get(4)");    // returns 4
        cache.set(5, 5);                       if (debug) cache.printCache("after set(5,5)");   // evicts key 1.
        assertEq(5, cache.get(5), "case 9");   if (debug) cache.printCache("after get(5)");    // returns 4
    }

    public static void main9(String[] args) {
        String[] operation={"set","set","set","set","set","get","set","get","get","set","get","set","set","set","get","set","get","get","get","get","set","set","get","get","get","set","set","get","set","get","set","get","get","get","set","set","set","get","set","get","get","set","set","get","set","set","set","set","get","set","set","get","set","set","get","set","set","set","set","set","get","set","set","get","set","get","get","get","set","get","get","set","set","set","set","get","set","set","set","set","get","get","get","set","set","set","get","set","set","set","get","set","set","set","get","get","get","set","set","set","set","get","set","set","set","set","set","set","set"};
        Integer[] expect={null,null,null,null,null,-1,null,19,17,null,-1,null,null,null,-1,null,-1,5,-1,12,null,null,3,5,5,null,null,1,null,-1,null,30,5,30,null,null,null,-1,null,-1,24,null,null,18,null,null,null,null,14,null,null,18,null,null,11,null,null,null,null,null,18,null,null,-1,null,4,29,30,null,12,11,null,null,null,null,29,null,null,null,null,17,-1,18,null,null,null,-1,null,null,null,20,null,null,null,29,18,18,null,null,null,null,20,null,null,null,null,null,null,null};
        Integer[][] parameter=  {{10,13},{3,17},{6,11},{10,5},{9,10},{13},{2,19},{2},{3},{5,25},{8},{9,22},{5,5},{1,30},{11},{9,12},{7},{5},{8},{9},{4,30},{9,3},{9},{10},{10},{6,14},{3,1},{3},{10,11},{8},{2,14},{1},{5},{4},{11,4},{12,24},{5,18},{13},{7,23},{8},{12},{3,27},{2,12},{5},{2,9},{13,4},{8,18},{1,7},{6},{9,29},{8,21},{5},{6,30},{1,12},{10},{4,15},{7,22},{11,26},{8,17},{9,29},{5},{3,4},{11,30},{12},{4,29},{3},{9},{6},{3,4},{1},{10},{3,29},{10,28},{1,20},{11,13},{3},{3,12},{3,8},{10,9},{3,26},{8},{7},{5},{13,17},{2,27},{11,15},{12},{9,19},{2,15},{3,16},{1},{12,17},{9,1},{6,19},{4},{5},{5},{8,1},{11,7},{5,2},{9,28},{1},{2,2},{7,4},{4,22},{7,24},{9,26},{13,28},{11,26}};

        LFUCache cache=new LFUCache(10);
        for (int i=0;i<operation.length;i++) {
            String op = operation[i];
            if (op.equals("set")) {
                System.out.println("* execute set " + parameter[i][0] + "," + parameter[i][1]);
                cache.set(parameter[i][0], parameter[i][1]);
                if (debug) cache.printCache("current cache");
            } else if (op.equals("get")) {
                System.out.println("* execute get " + parameter[i][0] + " expect " + expect[i]);
                boolean pass=assertEq(expect[i], cache.get(parameter[i][0]),"get("+parameter[i][0]+") should be "+expect[i]);
                if (debug) cache.printCache("current cache");
                if (!pass) break;
            }
        }
    }

    public static void main3(String[] args) {
        int[] operation={};
        Integer[] expect={};
        Integer[][] parameter={};

        System.out.println("total "+operation.length+" cases");
        LFUCache cache=new LFUCache(1101);
        for (int i=0;i<operation.length;i++) {
            int op = operation[i];
            if (op==1) {
                System.out.println("* execute set " + parameter[i][0] + "," + parameter[i][1]);
                cache.set(parameter[i][0], parameter[i][1]);
                if (debug) cache.printCache("current cache");
            } else if (op==0) {
                System.out.println("* execute get " + parameter[i][0] + " expect " + expect[i]);
                boolean pass=assertEq(expect[i], cache.get(parameter[i][0]),"get("+parameter[i][0]+") should be "+expect[i]);
                if (debug) cache.printCache("current cache");
                if (!pass) break;
            }
        }
    }

    static String[] read() {
        FileReader fr=null;
        BufferedReader br=null;
        try {
            fr=new FileReader("case20.txt");
            br = new BufferedReader(fr);

            String line=br.readLine();
            operations=line.split(",");
            Pattern p=Pattern.compile("(LFUCache|get|set)");
            Matcher m;
            for (int i=0;i<operations.length;i++) {
                m=p.matcher(operations[i]);
                if (m.find()) {
                    System.out.println("get:"+m.group(0));
                    operations[i]=m.group(0);
                } else {
                    System.out.println("parse fail:"+operations[i]);
                    break;
                }
            }

            String[] pars=br.readLine().split("],\\[");
            parameters=new Integer[pars.length][2];
            p= Pattern.compile("(\\d+),(\\d+)");
            Pattern p1=Pattern.compile("(\\d+)");
            for (int i=0;i<parameters.length;i++) {
                if (pars[i].contains(",")) {
                    m=p.matcher(pars[i]);
                    if (m.find()) {
                        System.out.println("get parameter "+m.group(1)+" "+m.group(2));
                        parameters[i][0]=Integer.parseInt(m.group(1));
                        parameters[i][1]=Integer.parseInt(m.group(2));
                    } else {
                        System.out.println("parse fail:"+pars[i]);
                        break;
                    }
                } else {
                    m=p1.matcher(pars[i]);
                    if (m.find()) {
                        System.out.println("get parameter "+m.group(0));
                        parameters[i][0]=Integer.parseInt(m.group(0));
                        parameters[i][1]=0;
                    } else {
                        System.out.println("parse fail:"+pars[i]);
                        break;
                    }
                }
            }

            p=Pattern.compile("([+-]?\\d+|null)");
            String[] exp=br.readLine().split(",");
            expect=new Integer[exp.length];
            for (int i=0;i<exp.length;i++) {
                m=p.matcher(exp[i]);
                if (m.find()) {
                    System.out.println("get expect "+m.group(0));
                    expect[i]=0;
                    if (!"null".equals(m.group()))
                        expect[i]=Integer.parseInt(m.group(0));
                } else {
                    System.out.println("parse fail:"+exp[i]);
                    break;
                }
            }
            br.close();
            fr.close();
        } catch (FileNotFoundException e) {
            System.out.println("could not find file");
        } catch (IOException e) {
            System.out.println("get io exception"+e);
        }
        return null;
    }
    static String[] operations;
    static Integer[][] parameters;
    static Integer[] expect;

    public static void main(String[] args) {
        read();

        System.out.println("total "+operations.length+" cases");
        LFUCache cache=null;
        for (int i=0;i<operations.length;i++) {
            System.out.println("Run case "+i+"/"+operations.length+" "+operations[i]+" ("+parameters[i][0]+","+parameters[i][1]+") expect "+expect[i]);
            if ("set".equals(operations[i])) {
                System.out.println("* execute set " + parameters[i][0] + "," + parameters[i][1]);
                cache.set(parameters[i][0], parameters[i][1]);
                if (debug) cache.printCache("current cache");
            } else if ("get".equals(operations[i])) {
                System.out.println("* execute get " + parameters[i][0] + " expect " + expect[i]);
                boolean pass=assertEq(expect[i], cache.get(parameters[i][0]),"get("+parameters[i][0]+") should be "+expect[i]);
                if (debug) cache.printCache("current cache");
                if (!pass) break;
            } else if ("LFUCache".equals(operations[i])) {
                cache=new LFUCache(parameters[i][0]);
            }
        }
    }
}
