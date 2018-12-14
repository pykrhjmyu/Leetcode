/*
SubsetI:without any duplicate intergers

Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]

a backtracking problem
develop the answer tree;use iterator or recursion
here use iterator
*/
class SubsetsISolution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>());
        for(int i=0;i<nums.length;i++){
            int N = ans.size();
            for(int j=0;j<N;j++){
                List<Integer> list = listCopyAdd(ans.get(j), nums[i]);
                ans.add(list);
            }
        }
        return ans;
    }
    
    private List<Integer> listCopyAdd(List<Integer> list, int add){
        List<Integer> newList = new ArrayList<>();
        for(int i = 0;i<list.size();i++){newList.add(list.get(i));}
        newList.add(add);
        return newList;
    }
}
/*
SubsetsII with duplicates
Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: [1,2,2]
Output:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]

because the existence of duplicates we need sort first(default is ascending)
here use iterator
based on SubsetsI when meet duplicates only add list generated from those generated last time
use oldCount to count the list generated last time
*/
class SUbsetsIISolution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>());
        Arrays.sort(nums);//sort
        int old = nums[0] - 1;
        int oldCount = 0;//record the number of generated lists last integer
        for(int i=0;i<nums.length;i++){
            if(nums[i]!=old){
                old = nums[i];
                oldCount = 0;
                int N = ans.size();
                for(int j=0;j<N;j++){
                    ans.add(listCopyAdd(ans.get(j), nums[i]));
                    oldCount++;
                }
            }
            else{
                int N = ans.size();
                for(int j=N-oldCount;j<N;j++){
                    ans.add(listCopyAdd(ans.get(j), nums[i]));
                }
            }
        }
        
        return ans;
    }
    
    private List<Integer> listCopyAdd(List<Integer> list, int add){
        List<Integer> newList = new ArrayList<>();
        for(int i = 0;i<list.size();i++){newList.add(list.get(i));}
        newList.add(add);
        return newList;
    }
}
/*
SubsetsII others'beautiful answer by recursion
*/
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        helper(nums, 0, new ArrayList<Integer>(), result);
        return result;
    }
    
    void helper(int[] nums, int start, List<Integer> list, List<List<Integer>> result)
    {
        result.add(list);
        
        for (int i = start; i < nums.length; i++)
        {
            if (i > start && nums[i - 1] == nums[i])
                continue;
            List<Integer> newList = new ArrayList<>(list);
            newList.add(nums[i]);
            helper(nums, i + 1, newList, result);
        }
    }
}