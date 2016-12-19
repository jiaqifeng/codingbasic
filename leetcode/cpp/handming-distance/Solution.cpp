#include <iostream>

using namespace std;

/* 
 * 461. Hamming Distance

    User Accepted: 632
    User Tried: 681
    Total Accepted: 771
    Total Submissions: 933
    Difficulty: Easy

The Hamming distance between two integers is the number of positions at which the corresponding bits are different.

Given two integers x and y, calculate the Hamming distance.

Note:
0 ¡Ü x, y < 231.

Example:

Input: x = 1, y = 4

Output: 2

Explanation:
1   (0 0 0 1)
4   (0 1 0 0)
       ¡ü   ¡ü

The above arrows point to positions where the corresponding bits are different.
*/

class Solution {
public:
  inline int countOnebit(int x) {
    int bit=0x1;
    int sum=0;
    for (int i=0; i< sizeof(int)*8; i++, bit=bit<<1)
      if ((x & bit) != 0) sum++;
    return sum;
  }
  int hammingDistance(int x, int y) {
    return countOnebit(x^y);
  }
};

int main(int argc, char *argv[])
{
  int x=3;
  int bit=0x1 << (sizeof(int)*8-1);
  for (int i = 0; i < sizeof(int)*8; i++) {
    if ((bit & x) !=0)
      cout << "1 ";
    else
      cout << "0 ";
    bit=bit>>1;
  }
  Solution s;
  cout << "sum(x)=" << s.countOnebit(3) << endl;
  cout << "hamming(1,4)=" << s.hammingDistance(1,4) << endl;
  return 0;
}

