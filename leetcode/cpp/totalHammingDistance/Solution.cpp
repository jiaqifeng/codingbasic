#include <iostream>
#include <vector>

using namespace std;

/* 
477. Total Hamming Distance

    User Accepted: 395
    User Tried: 620
    Total Accepted: 416
    Total Submissions: 1554
    Difficulty: Medium

The Hamming distance between two integers is the number of positions at which the corresponding bits are different.

Now your job is to find the total Hamming distance between all pairs of the given numbers.

Example:

Input: 4, 14, 2

Output: 6

Explanation: In binary representation, the 4 is 0100, 14 is 1110, and 2 is 0010 (just
showing the four bits relevant in this case). So the answer will be:
HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6.

Note:

    Elements of the given array are in the range of 0 to 10^9
    Length of the array will not exceed 10^4.
*/

class Solution {
public:
  int totalHammingDistance(vector<int>& nums) {
    int counter[32];
    for (int i=0; i < 32; i++)
      counter[i]=0;
    for (vector<int>::const_iterator it= nums.begin(); it!= nums.end(); ++it) {
      int bit=0x1;
      for (int i=0; i<32; i++, bit=bit<<1)
	if ((*it & bit) == 0) counter[i]++;
    }
    int sum=0;
    for (int i=0; i<32; i++)
      sum+=(counter[i] * (nums.size() - counter[i]));
    return sum;
  }
};
int main(int argc, char *argv[])
{
  Solution s;
  int ii[3]={4,14,2};
  vector<int> v(ii, ii+3);
  cout << "total is " << s.totalHammingDistance(v) << endl;
  return 0;
}
