package leetcode;


import util.JacksonHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author shuzhuang.su
 * @date 2020-05-26 20:12
 */
public class Solution {


    /**
     * 找出重复数
     * * <p>
     * * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。
     * * 假设只有一个重复的整数，找出这个重复的数。
     *
     * @param nums
     * @return
     */
    public static int findDuplicate(int[] nums) {


        int length = nums.length;

        int size = nums[0];

        for (int i = 1; i < length; i++) {
            if (size < nums[i]) {
                size = nums[i];
            }
        }
        int[] repeat = new int[size + 1];

        for (int num : nums) {
            repeat[num]++;
        }

        for (int j = 0; j < size + 1; j++) {
            if (repeat[j] > 1) {
                return j;
            }
        }

        return -1;
    }


    /**
     * @param A
     * @param K
     * @return
     */
    public static int subarraysDivByK1(int[] A, int K) {
        int result = 0;
        int size = A.length;
        int sum;
        for (int i = 0; i < size; i++) {
            sum = 0;
            for (int j = i; j < size; j++) {
                sum += A[j];
                if (sum % K == 0) {
                    result++;
                }
            }
        }
        return result;
    }

    public static int subarraysDivByK(int[] A, int K) {
        int[] same = new int[K];
        same[0] = 1;
        int sum = 0, ans = 0;
        for (int elem : A) {
            sum += elem;
            int modulus = (sum % K + K) % K;
            ans += same[modulus]++;
        }
        return ans;
    }


    public List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> permutes = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        fill(new int[nums.length], nums, nums.length, 0, list, permutes);
        return permutes;
    }

    /**
     * 递归装配结果
     *
     * @param index  已排列数字的下标
     * @param nums   数组
     * @param size   数组大小
     * @param depth  深度
     * @param list   上层排列的结果
     * @param result 最终结果
     */
    public static void fill(int[] index, int[] nums, int size, int depth, List<Integer> list, List<List<Integer>> result) {
        depth++;
        if (depth > size) {
            return;
        }
        List<Integer> temp = null;
        for (int i = 0; i < size; i++) {
            if (index[i] == 0) {
                temp = new ArrayList<>(list);
                temp.add(nums[i]);
                index[i]++;
                fill(index, nums, size, depth, temp, result);
                index[i]--;
            }
        }
        if (depth == size) {
            result.add(temp);
        }
    }

    public static String decodeString(String s) {
        Stack<Character> stack = decodeStringUseStack(s);
        StringBuilder str = new StringBuilder();
        while (!stack.empty()) {
            str.append(stack.pop());
        }
        return str.reverse().toString();
    }

    public static Stack<Character> decodeStringUseStack(String s) {
        Stack<Character> stack = new Stack<>();
        for (Character c : s.toCharArray()) {
            // 遇到第一个]停止入栈
            if (c == ']') {
                StringBuilder str = new StringBuilder();
                // 寻找最近的[，并将[]内的字符出栈
                while (true) {
                    Character pop = stack.pop();
                    // 找到最近的[
                    if (pop == '[') {
                        break;
                    }
                    str.append(pop);
                }
                // 获取重复次数
                int time = getRepeatTime(stack);
                // 根据重复次数入栈
                char[] chars = str.reverse().toString().toCharArray();
                for (int i = 0; i < time; i++) {
                    for (Character c1 : chars) {
                        stack.push(c1);
                    }
                }
            } else {
                stack.push(c);
            }
        }
        return stack;
    }


    /**
     * @param nums
     * @return
     */
    public static int splitArray(int[] nums) {
        int size = nums.length;
        for (int i = 1; i < size + 1; i++) {
            int[][] arr = new int[i][nums.length];
            for (int j = 0; j < i; j++) {
                arr[j] = sub(nums, j, size + 1 - i);
            }
            for (int[] a : arr) {
                if (yueshu(a) > 1) {
                    return i;
                }
            }

        }
        return size;
    }

    public static int[] sub(int[] nums, int s, int e) {
        int[] temp = new int[e - s];
        int index = 0;
        for (int i = s; i < e; i++) {
            temp[index] = nums[i];
            index++;
        }
        return temp;
    }


    public static int yueshu(int[] nums) {

        int a = nums[0];
        int b = nums[nums.length - 1];
        //求出两个数字之间的小值
        int min = a < b ? a : b;
        for (int i = min; i >= 1; i--) {
            if (a % i == 0 && b % i == 0) {
                return i;
            }
        }
        return 0;
    }


    /**
     * 拿到重复次数
     *
     * @param s
     * @return
     */
    public static int getRepeatTime(Stack<Character> s) {
        StringBuilder str = new StringBuilder();
        while (!s.empty() && Character.isDigit(s.peek())) {
            str.append(s.pop());
        }
        return Integer.valueOf(str.reverse().toString());
    }


    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int max = candies[0];
        int length = candies.length;
        for (int i = 1; i < length; i++) {
            if (max < candies[i]) {
                max = candies[i];
            }
        }

        List<Boolean> list = new ArrayList<>(candies.length);
        for (int candy : candies) {
            if (candy + extraCandies >= max) {
                list.add(true);
            } else {
                list.add(false);
            }
        }
        return list;
    }


    public static int findUnsortedSubarray(int[] nums) {

        int[] temp = nums.clone();
        Arrays.sort(nums);
        int length = nums.length;
        int n = 0;
        boolean before = true;
        boolean after = true;
        for (int i = 0; i < length; i++) {
            if (before && nums[i] == temp[i]) {
                n++;
            } else {
                before = false;
            }
            if (after && nums[length - 1 - i] == temp[length - 1 - i]) {
                n++;
            } else {
                after = false;
            }

            if (!before && !after) {
                break;
            }
        }
        return length - n > 0 ? length - n : 0;
    }

    public static int[] corpFlightBookings(int[][] bookings, int n) {

        int[] r = new int[n];
        for (int[] booking : bookings) {
            for (int j = booking[0]; j <= booking[1]; j++) {
                if (j <= n) {
                    r[j - 1] += booking[2];
                } else {
                    break;
                }
            }
        }
        return r;
    }


    public static void main(String[] args) {
        int[][] arr = {{1, 2, 10}, {2, 3, 20}, {2, 5, 25}};
        int[] unsortedSubarray = corpFlightBookings(arr, 5);
        System.out.println(unsortedSubarray);
    }

}
