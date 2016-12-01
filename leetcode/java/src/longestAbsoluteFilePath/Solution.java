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
    boolean countTab=true;//false to count name
    int queue[]=new int[MAX_DEPTH];
    int head=0;
    int maxlen=0;
    int namelen=0;
    boolean isFile=false;
    int sumQ() {
        int sum=0;
        for (int i=0;i<head;i++) {
            System.out.println("queue["+i+"]="+queue[i]);
            sum+=queue[i];
        }
        return sum;
    }
    void init() {
        countTab=true;
        head=0;
        maxlen=0;
        namelen=0;
        isFile=false;
    }
    public int lengthLongestPath(String input) {
        int i=0;
        int depth=0;
        init();
        while (i < input.length()) {
            if (input.charAt(i)=='\t' && countTab==true) {
                if (debug) System.out.print("\\t");
                depth++;//todo seems we could count all \t now and void counttab flag
            } else if (input.charAt(i)=='.') {
                if (debug) System.out.print(".");
                if (i<input.length()-1 && input.charAt(i+1)!='\n')
                    isFile=true;//todo this should not be the last string
                namelen++;
            } else if (input.charAt(i)=='\n' || i==input.length()-1) {
                if (debug) System.out.println("\nfind "+input.charAt(i)+", namelen="+namelen+", depth="+depth+",isFile="+isFile+",before push head="+head);
                if (i==input.length()-1) namelen++;
                if (!isFile) {
                    head=depth+1;
                    queue[depth]=namelen+1;//add / to lenght
                } else {
                    if (debug) System.out.print("sumQ=" + sumQ());
                    head=depth;
                    int len = sumQ() + namelen;
                    if (maxlen < len)
                        maxlen = len;
                }
                // reset stat
                countTab=true;
                namelen=0;
                isFile=false;
                depth=0;
            } else {
                if (debug) System.out.print(input.charAt(i));
                namelen++;
                countTab=false;
            }
            i++;
        }
        return maxlen;
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
        int ret=new Solution().lengthLongestPath(t3);
        System.out.println("ret "+ret);
    }
}
