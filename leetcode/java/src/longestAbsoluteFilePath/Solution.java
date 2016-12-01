package longestAbsoluteFilePath;

/*
388. Longest Absolute File Path

Suppose we abstract our file system by a string in the following manner:

The string "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" represents:

dir
    subdir1
    subdir2
        file.ext

The directory dir contains an empty sub-directory subdir1 and a sub-directory subdir2 containing a file file.ext.

The string "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext" represents:

dir
    subdir1
        file1.ext
        subsubdir1
    subdir2
        subsubdir2
            file2.ext

The directory dir contains two sub-directories subdir1 and subdir2. subdir1 contains a file file1.ext and an empty second-level sub-directory subsubdir1. subdir2 contains a second-level sub-directory subsubdir2 containing a file file2.ext.

We are interested in finding the longest (number of characters) absolute path to a file within our file system. For example, in the second example above, the longest absolute path is "dir/subdir2/subsubdir2/file2.ext", and its length is 32 (not including the double quotes).

Given a string representing the file system in the above format, return the length of the longest absolute path to file in the abstracted file system. If there is no file in the system, return 0.

Note:

    The name of a file contains at least a . and an extension.
    The name of a directory or sub-directory will not contain a ..

Time complexity required: O(n) where n is the size of the input string.

Notice that a/aa/aaa/file1.txt is not the longest file path, if there is another path aaaaaaaaaaaaaaaaaaaaa/sth.png.

Origin link: https://leetcode.com/problems/longest-absolute-file-path/
 */

import java.util.*;

/**
 * Created by jack on 16-11-30.
 */

/*
 * This solution is the first right solutions. Which not use common library such as Spring.split and queue.
 * One thing to improve is the sumQ, which could be replaced by store the sum instead of each length.
 */
public class Solution {
    static final boolean debug=true;
    static final int MAX_DEPTH=1000;

    public int lengthLongestPath(String input) {
        int queue[]=new int[MAX_DEPTH]; // store dir path length, plus '/' appended
        int qhead=1;//queue[0] for 0, simplify length adding
        int i=0;
        int indent=1;// '\t' counter, like head, start from 1
        boolean countTab=true;//false: when count name length, it is not said file name may include '\t', but let's make things better here

        int maxlen=0;
        int namelen=0;
        boolean isFile=false;

        queue[0]=0;
        for (i=0; i < input.length(); i++) {
            if (input.charAt(i)=='\t' && countTab==true) {    if (debug) System.out.print("\\t");
                indent++;
            } else if (input.charAt(i)=='.') {    if (debug) System.out.print(".");
                if (i<input.length()-1 && input.charAt(i+1)!='\n')
                    isFile=true;//The name of a file contains at least a . and an extension.
                namelen++;
            } else if (input.charAt(i)=='\n' || i==input.length()-1) {    if (debug) System.out.println("\nfind "+input.charAt(i)+", namelen="+namelen+", depth="+indent+",isFile="+isFile+",before push head="+qhead);
                if (i==input.length()-1) namelen++;//end of input should be file/dir name
                if (!isFile) {
                    qhead=indent+1;
                    queue[indent]=namelen+1+queue[indent-1];//1 for '/'
                } else {
                    qhead=indent;
                    int len = queue[qhead-1] + namelen;
                    if (maxlen < len)
                        maxlen = len;
                }
                // reset stat
                countTab=true;
                namelen=0;
                isFile=false;
                indent=1;
            } else {
                if (debug) System.out.print(input.charAt(i));
                namelen++;
                countTab=false;
            }
        }
        return maxlen;
    }

    public int lengthLongestPathV2(String input) {
        int max=0;
        String[] lines=input.split("\n");
        Deque<Integer> queue=new ArrayDeque<Integer>(10);
        queue.push(0);
        for (String line : lines) {
            int depth=line.lastIndexOf('\t')+1;//if not found, -1+1=0 is ok
            int name=line.length() - depth;
            if (debug) System.out.format("depth="+depth+",len="+name+",queue "+queue.size()+"="+queue.peekLast()+":"+line+"\n");
            while (queue.size()-1>depth) queue.removeLast();
            if (line.contains(".")) {
                max=Math.max(max, name+queue.peekLast());
            } else {
                queue.add(name + 1 + queue.peekLast());
            }
        }
        return max;
    }

    public int lengthLongestPathV3(String input) {
        int max=0;
        String[] lines=input.split("\n");
        ArrayList<Integer> queue=new ArrayList<Integer>();
        queue.add(0);
        for (String line : lines) {
            int depth=line.lastIndexOf('\t')+1;//if not found, -1+1=0 is ok
            int name=line.length() - depth;
            if (debug) System.out.format("depth="+depth+",len="+name+",queue "+queue.size()+"="+queue.get(queue.size()-1)+":"+line+"\n");
            if (line.contains(".")) {
                max=Math.max(max, name+queue.get(queue.size()-1));
            } else {
                if (depth<queue.size())
                    queue.add(name+1+queue.get(depth));
                else
                    queue.set(depth+1, name + 1 + queue.get(depth));
            }
        }
        return max;
    }
    public static void main(String[] args) {
        String t=new String("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.exe");
        String t2=new String("dir\n" +
                "\tsubdir1\n" +
                "\t\tsubdir2\n" +
                "\t\t\tfile.ext\n" +
                "\tsubdir3\n" +
                "\t\tfilealonenamebehere.ttt");
        String t3="dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext";
        Solution so=new Solution();
        int ret=so.lengthLongestPathV2(t3);
        System.out.println("ret "+ret);
        ret = so.lengthLongestPathV3("dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext");
        System.out.println("expect 32 get "+ret);
    }
}
