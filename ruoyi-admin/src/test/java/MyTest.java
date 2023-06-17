import org.junit.jupiter.api.Test;

/**
 * @author as2i
 * @date 5/28/2022 1:24 PM
 */
public class MyTest {

    @Test
    void stringFormat() {
        int aa = 111;
        String bb = "aaaa";

        System.out.printf("aefawef %d, awegfawe [%s]%n", aa, bb);
    }

    @Test
    void test() {
        Double i1 = 100.0;
        Double i2 = 100.0;
        Double i3 = 200.0;
        Double i4 = 200.0;

        System.out.println(i1 == i2);
        System.out.println(i3.equals(i4));


        byte a = 127;
        byte b = 127;
        // 报编译错误:cannot convert from int to byte
        // b = a + b;
        b += a;


    }

    @Test
    void leetCode() {
        int[] nums = {1,4,4};
        System.out.println(minSubArrayLen(4, nums));
    }

    public int[] twoSum(int[] numbers, int target) {
        int i = 0;
        int j = numbers.length - 1;
        while (i < j) {
            if (numbers[i] + numbers[j] == target) {
                return new int[]{i+1, j+1};
            } else if (numbers[i] + numbers[j] < target) {
                i++;
            } else {
                j--;
            }
        }
        return null;
    }

    public String reverseVowels(String s) {
        int i = 0;
        int j = s.length() - 1;
        char[] array = s.toCharArray();
        while (i < j) {
            while (i < j && !((array[i] == 'a')||(array[i] == 'e')||(array[i] == 'i')
                    ||(array[i] == 'o')||(array[i] == 'u')||(array[i] == 'A')||(array[i] == 'E')||(array[i] == 'I')
                    ||(array[i] == 'O')||(array[i] == 'U'))) {
                i++;
            }

            while (i < j && !((array[j] == 'a')||(array[j] == 'e')||(array[j] == 'i')
                    ||(array[j] == 'o')||(array[j] == 'u')||(array[j] == 'A')||(array[j] == 'E')||(array[j] == 'I')
                    ||(array[j] == 'O')||(array[j] == 'U'))) {
                j--;
            }
            char temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            i++;
            j--;
        }
        return new String(array);
    }


    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int Smax = 0;
        int shorter = 0, length = 0, S = 0;
        while (left < right)
        {
            shorter = (height[left] < height[right]) ? height[left] : height[right];
            length = right - left;
            if (height[left] < height[right])
            {
                left++;
            }
            else
                right--;
            S = shorter * length;
            Smax = (S > Smax) ? S : Smax;
        }
        return Smax;
    }

    public int minSubArrayLen(int target, int[] nums) {
        int left = 0,right = -1;
        int sum = 0;
        int minLen = nums.length + 1;
        while(left < nums.length){
            if(sum < target && right + 1 < nums.length)
                sum += nums[++right];
            else
                sum -= nums[left++];
            if(sum >= target) {
                minLen = Math.min(right - left + 1, minLen);
            }

        }
        if(minLen == nums.length + 1)
            return 0;
        return minLen;
    }


}
