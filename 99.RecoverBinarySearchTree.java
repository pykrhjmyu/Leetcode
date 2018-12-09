/**
99. Recover Binary Search Tree
Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.

Example 1:

Input: [1,3,null,null,2]

   1
  /
 3
  \
   2

Output: [3,1,null,null,2]

   3
  /
 1
  \
   2

Tips:
1.Use In-order traversal by recursion or builing stack uses O(n)space.
By in-order traversal, we should get the values of tree nodes in ascending order.
So if the pre.val > cur.val , the first swapped node is the pre.
Then here exists two possibilities. First, we can find the second pair, in which the pre.val > cur.val, and this time the cur is the second swapped node.
Another possibility is that we cannot find the second pair, thus the swapped two nodes are exactly the first pari.

2.Use Morris Traversal Method, whose space complexity is O(1)/constant.
Chinese instruction: https://www.cnblogs.com/AnnieKim/archive/2013/06/15/morristraversal.html

Here MTM is used.
**/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public void recoverTree(TreeNode root) {
        TreeNode cur = root;
        TreeNode bef = null;
        TreeNode pre = null;//predecessor
        TreeNode pair1 = null;
        TreeNode pair2 = null;//swaaped nodes
        TreeNode temp = null;//for the case only one pair detected
        int time = 1;
        /**/
        while(cur != null){
            if(cur.left != null){
                pre = cur.left;
                while(pre.right != null && pre.right != cur){pre = pre.right;}//find the predecessor
                if(pre.right == null){
                    pre.right = cur;//build the back edge
                    cur = cur.left;
                }
                else{
                    pre.right = null;//cut off the egde
                    if(temp != null && bef.val > cur.val){pair2 = cur;}
                    if(bef != null && bef.val > cur.val &&pair2 == null){pair1 = bef;temp = cur;}
                    bef = cur;
                    cur = cur.right;
                }
            }
            else{
                if(temp != null && bef.val > cur.val){pair2 = cur;}
                if(bef != null && bef.val > cur.val &&pair2 == null){pair1 = bef;temp = cur;}
                bef = cur;
                cur = cur.right;
            }
        }
        /**/
        int tmp = pair1.val;
        if(pair2 == null){
            pair1.val = temp.val;
            temp.val = tmp;
        }
        else{pair1.val = pair2.val;pair2.val = tmp;}
    }
}