package leetcode;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Aaron.H.Shen
 * @date 2023/6/15 14:03
 */
public class EasyTest {

	@Test
	void test() {
//		int[] arr = {2, 3, 7, 1, 1, 10, 8, 9, 2};
//		System.out.println(rob(arr));
//		System.out.println(thief_DP(arr));

//		int[] arr = {7,1,5,3,6,4};
//		System.out.println(maxProfit(arr));

//		System.out.println(lengthOfLastWord("luffy is still joyboy"));

		String[] arr = {"dog","racecar","car"};
		System.out.println(longestCommonPrefix(arr));
	}


	/**
	 * 打家劫舍
	 * https://leetcode-solution-leetcode-pp.gitbook.io/leetcode-solution/easy/198.house-robber
	 */
	public int rob(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int length = nums.length;
		if (length == 1) {
			return nums[0];
		}
		int prev = nums[0];
		int cur = Math.max(nums[0], nums[1]);
		for (int i = 2; i < length; i++) {
			int temp = cur;
			cur = Math.max(prev + nums[i], cur);
			prev = temp;
		}
		return cur;
	}

	public int thief_DP(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int len = arr.length;
		if (len == 1) return arr[0];
		//创建DP数组，来存储 前面所偷的金额
		int[] dp = new int[len];
		//初始条件
		dp[0] = arr[0];
		dp[1] = Math.max(arr[0], arr[1]);

		for (int i = 2; i < len; i++) {
			dp[i] = Math.max(dp[i - 1], dp[i - 2] + arr[i]);
		}
		return dp[dp.length - 1];
	}

	/**
	 * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
	 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
	 */
	int majorityElement(int[] arr) {
		Arrays.sort(arr);
		return arr[arr.length / 2];
	}


	public int maxProfit(int[] prices) {
		int sum = 0;
//		for (int i = 0; i < prices.length; i++) {
//			int aa = 0;
//			for (int j = i+1; j < prices.length; j++) {
//				if (prices[i] < prices[j]) {
//					aa = Math.max(aa, (prices[j] - prices[i]));
//				}
//			}
//			sum = Math.max(aa, sum);
//		}

		return sum;
	}

	public int lengthOfLastWord(String s) {
//		String[] s1 = s.split(" ");
//		for (int i = s1.length - 1; i > 0; i--) {
//			if (!s1[i].equals(" ")) {
//				return s1[i].length();
//			}
//		}
//		return s.trim().length();
		return s.trim().length() - 1 - s.trim().lastIndexOf(" ");
	}


	public String longestCommonPrefix(String[] strs) {
		String str = strs[0];//Arrays.stream(strs).min(Comparator.comparingInt(String::length)).get();

		for (int i = 0; i < strs.length; i++) {
			while (!strs[i].startsWith(str)) {
				if (str.length() == 0) return "";
				str = str.substring(0, str.length() - 1);
			}
		}
		return str;
	}

}
