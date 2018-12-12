/*
Given a string, find the length of the longest substring without repeating characters.

Example 1:

Input: "abcabcbb"
Output: 3 
Explanation: The answer is "abc", with the length of 3. 

Use slip window
O(n)
*/
class Solution {
    public int lengthOfLongestSubstring(String s) {
        char[] c = s.toCharArray();
        int[] p = new int[128];//ASCII
        int maxLen = 0;
        
        for(int i=0,j = 0; j<s.length();j++){
            i = Math.max(i, p[c[j]]);
            p[c[j]] = j+1;
            maxLen =Math.max(maxLen, j-i+1);
            while((j+1)<s.length()&&c[j+1]==c[j]){
                j++;
                p[c[j]] = j+1;
            }
            i = Math.max(i, j);
        }
        
        return maxLen;
    }
}