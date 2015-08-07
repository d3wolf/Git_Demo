package leetcode;

public class Solutions {

	public static int hammingWeight(int n) {
		return Integer.bitCount(n);
	}

	public static int titleToNumber(String s) {
		int count = 0;

		byte[] bs = s.getBytes();
		for (int i = 0; i < bs.length; i++) {
			count += (int) ((bs[i] - 64) * Math.pow(26, bs.length - 1 - i));
		}

		return count;
	}

	public static boolean isNumber(String s) {
		if (s == null)
			return false; // check for null string

		s = s.trim();
		if (s.length() == 0)
			return false; // check for empty string

		return s.matches("[-+]?(\\d+\\.?|\\.\\d+)\\d*(e[-+]?\\d+)?");

	}

	public int[] twoSum(int[] nums, int target) {
		int[] indexs = new int[2];
		for (int i = 0; i < nums.length - 1; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[i] + nums[j] == target) {
					indexs[0] = i + 1;
					indexs[1] = j + 1;
				}
			}
		}

		return indexs;
	}

	public static void main(String[] args) {
		System.out.println(isNumber(".e2"));

	}
}
