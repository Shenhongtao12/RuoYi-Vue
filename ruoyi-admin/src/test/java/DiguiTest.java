import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

/**
 * 递归
 *
 * @author Aaron.H.Shen
 * @date 2023/6/17 10:37
 */
public class DiguiTest {

	@Test
	void testMaxNum() {
		int[] arrays = {2, 3, 4, 5, 1, 5, 2, 9, 5, 6, 8, 3, 2};

		int max = arrays[0];

		for (int i : arrays) {
			if (max < i) {
				max = i;
			}
		}
		System.out.println("max: " + max);

		max = maxNum(arrays, 0, arrays.length);

		System.out.println("max: " + max);
	}

	/**
	 * 递归实现求数组中的最大值
	 *
	 * @param arr
	 * @param i
	 * @param length
	 * @return
	 */
	private int maxNum(int[] arr, int i, int length) {
		if (i + 1 == length) {
			return arr[i];
		}

		int a = arr[i];
		int b = maxNum(arr, i + 1, length);

		return Math.max(a, b);
	}

	@Test
	void testSort() {
		int[] arrays = {2, 3, 4, 5, 1, 5, 2, 9, 5, 6, 8, 3, 2};

//		for (int i = 0; i < arrays.length; i++) {
//			for (int j = i + 1; j < arrays.length; j++) {
//				int tmp = arrays[j];
//				if (arrays[i] > arrays[j]) {
//					arrays[j] = arrays[i];
//					arrays[i] = tmp;
//				}
//			}
//		}

		sort(arrays, 0, arrays.length);
		System.out.println(Arrays.toString(arrays));
	}

	/**
	 * 递归实现冒泡排序
	 */
	private void sort(int[] arrays, int index, int length) {
		if (index == length - 1) {
			return;
		}

		for (int i = 0; i < length - 1; i++) {
			int tmp = arrays[i + 1];
			if (arrays[i] > arrays[i + 1]) {
				arrays[i + 1] = arrays[i];
				arrays[i] = tmp;
			}
		}

		sort(arrays, index, length - 1);
	}

	@Test
	void find2Test() {
		int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 11};

		System.out.println(find2(arr, 11, 0, arr.length - 1));
	}

	private int find2(int[] arr, int num, int left, int right) {
		int mid = (left + right) / 2;
		if (arr[mid] == num) {
			return mid;
		}
		if (arr[mid] > num) {
			// 右边
			return find2(arr, num, left, mid - 1);
		} else {
			// 左边
			return find2(arr, num, mid + 1, right);
		}
	}

	@Test
	void quicksort() {
		long[] arr = new long[200000];
		for (int i = 0; i < 200000; i++) {
			arr[i] = Math.round(Math.random() * 1000000);
		}
		long start = System.currentTimeMillis();
//		quickSort(arr, 0, arr.length - 1);

		Arrays.sort(arr);

		System.out.println((System.currentTimeMillis() - start));
//		System.out.println(Arrays.toString(arr));
	}

	public void quickSort(long[] arr, int low, int high) {
		int i, j;
		long temp, t;
		if (low > high) {
			return;
		}
		i = low;
		j = high;
		//temp就是基准位
		temp = arr[low];

		while (i < j) {
			//先看右边，依次往左递减
			while (temp <= arr[j] && i < j) {
				j--;
			}
			//再看左边，依次往右递增
			while (temp >= arr[i] && i < j) {
				i++;
			}
			//如果满足条件则交换
			if (i < j) {
				t = arr[j];
				arr[j] = arr[i];
				arr[i] = t;
			}
		}
		//最后将基准为与i和j相等位置的数字交换
		arr[low] = arr[i];
		arr[i] = temp;
		//递归调用左半数组
		quickSort(arr, low, j - 1);
		//递归调用右半数组
		quickSort(arr, j + 1, high);
	}
}
