import org.junit.jupiter.api.Test;

import java.security.SecureRandom;

/**
 * @author Aaron.H.Shen
 * @date 2023/6/19 13:24
 */
public class MyTest {

	@Test
	public void test() {

		SecureRandom random = new SecureRandom();
		int rand_int1 = random.nextInt(1000);
		int rand_int2 = random.nextInt(1000);

		// Print random integers
		System.out.println("Random Integers: " + rand_int1);
		System.out.println("Random Integers: " + rand_int2);

		for (int i = 0; i < 10; i++) {
			StringBuilder returnValue = new StringBuilder();
			int randomInt = 0;
			int range = 9;
			for (int j = 0; j < 5; j++) {
				randomInt = random.nextInt(range + 1);
				returnValue.append(randomInt);
			}

			System.out.println(returnValue);
		}

	}

	public static void quickSort(int[] arr, int low, int high) {
		if (low < high) {
			int partitionIndex = partition(arr, low, high);
			quickSort(arr, low, partitionIndex - 1);
			quickSort(arr, partitionIndex + 1, high);
		}
	}

	private static int partition(int[] arr, int low, int high) {
		int pivot = arr[high];
		int i = low - 1;
		for (int j = low; j < high; j++) {
			if (arr[j] < pivot) {
				i++;
				// 交换 arr[i] 和 arr[j]
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		// 交换 arr[i+1] 和 arr[high]，完成 partition 操作
		int temp = arr[i + 1];
		arr[i + 1] = arr[high];
		arr[high]=temp;
		return i + 1;
	}
}
