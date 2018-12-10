/*
S and T are strings composed of lowercase letters. In S, no letter occurs more than once.

S was sorted in some custom order previously. We want to permute the characters of T so that they match the order that S was sorted. More specifically, if x occurs before y in S, then x should occur before y in the returned string.

Return any permutation of T (as a string) that satisfies this property.

Example :
Input: 
S = "cba"
T = "abcd"
Output: "cbad"
Explanation: 
"a", "b", "c" appear in S, so the order of "a", "b", "c" should be "c", "b", and "a". 
Since "d" does not appear in S, it can be at any position in T. "dcba", "cdba", "cbda" are also valid outputs.
 

Note:

S has length at most 26, and no character is repeated in S.
T has length at most 200.
S and T consist of lowercase letters only.


12/10 Use count sort method
12/11 new method
*/
/*
class Solution {
    public String customSortString(String S, String T) {
        if(S == "") return T;
        int N =26;
        //
        
        int[] count = new int[N+2];
        char[] aux = new char[T.length()];
        for(int i=0;i<T.length();i++){
            count[S.indexOf(T.charAt(i))+2]++;
        }
        for(int i=0;i<N+1;i++){
            count[i+1] += count[i];
        }
        for(int i=0;i<T.length();i++){
            aux[count[S.indexOf(T.charAt(i))+1]++] = T.charAt(i);
        }
        return new String(aux);
    }
}
*/
class Solution {
    public String customSortString(String S, String T) {
        if(S =="" || S.length() == 1) return T;
        int[] count = new int[26];
        char[] ans = T.toCharArray();
        for(char c: ans){
            count[c - 'a']++;
        }//the frequency of every kind letter in T
        
        int index = 0;
        for(char c: S.toCharArray()){
            while(count[c - 'a'] >0){
                ans[index++] = c;//rewrite the T
                count[c - 'a']--;
            }
        }
        for(int i = 0;i<26 && index<T.length();i++){
            while(count[i] > 0){
                ans[index++] = (char)(i+'a');
                count[i]--;
            }
        }
        
        return new String(ans);
    }
}