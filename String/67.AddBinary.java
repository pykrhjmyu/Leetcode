/*
Given two binary strings, return their sum (also a binary string).

The input strings are both non-empty and contains only characters 1 or 0.

Example 1:

Input: a = "11", b = "1"
Output: "100"
*/

class Solution {
    public String addBinary(String a, String b) {
        if(a.length()<b.length()){
            String temp = a;
            a = b;
            b = temp;
        }//guarantee a is the longer string;
        char[] aa = a.toCharArray();
        char[] bb = b.toCharArray();
        boolean carry = false;//carry 1
        /**/
        for(int i = a.length()-1, j = b.length()-1;i>=0;i--,j--){
            if(j>=0){
                if(carry){
                    carry = (aa[i] == '0' && bb[j] == '0')?false:true;
                    aa[i] = ((aa[i] == '0' && bb[j] == '0') || (aa[i] == '1' && bb[j] == '1'))?'1':'0';
                }
                else{
                    carry = (aa[i] == '1' && bb[j] == '1')?true:false;
                    aa[i] = ((aa[i] == '0' && bb[j] == '1') || (aa[i] == '1' && bb[j] == '0'))?'1':'0';
                }
            }
            else{
                if(carry){
                    if(aa[i] == '0'){aa[i] ='1';carry=false;}
                    else aa[i] ='0';
                }
            }
        }
        
        String res = (carry)?("1"+new String(aa)):new String(aa);
        return res;
    }
}