/*
Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example 1:

Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.

Solution:
we can find that repeated chars must be palindromic substring, like aaaaaaaa bb cccc
1.from the first char:c[lo], match the following chars (repeated the char) until a mismatch char:c[hi],record the position
2.from the lo to left, hi to right, two-direction increase the palindromic substring until a mismatch pair
3. move the i to the recorded position , repeat 1 2 until the pointer arrives at end of string

we just record the start entry and end entry of target substring, so the space complexity is O(1)(const)---s.substring(start, end+1)
Time complexity:
best condition all chars in the string is same: aaaaaaaaaa only N-1 times compare
worst condition :   (ab)*  for every char 1time compare to the following char (i-1)times pair compare
1+(1+1)+(1+2)+(1+3)+..+(1+N/2) = O(N)+O(N^2) = O(N^2)

**/
class Solution {
    public String longestPalindrome(String s) {
        if(s.isEmpty()|| s.length() == 1) return s;
        char[] c = s.toCharArray();
        int start = 0, end = 0;
        int maxLen = 0;
        
        for(int i = 0; i<s.length();i++){
            int lo = i;
            int k = 1;
            while(i+k<s.length()){
                if(c[i+k] == c[i]) k++;
                else break;
            }
            int t = i+k-1;
            int hi = i+k-1;
            //
            for(int j = 1;j<=i&&i+k-1+j<s.length();j++){
                if(c[i-j] == c[i+k-1+j]){lo--;hi++;}
                else break;
            }
            if(hi - lo + 1>maxLen){
                start = lo;
                end = hi;
                maxLen = hi-lo+1;
            }
            //
            i = t;
            if(s.length()-(t+1) < maxLen/2+1) break;
        }
        return s.substring(start, end+1);
    }
}
