package calc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Random;

public class RandomNum {
    private static final Integer LENGTH = 8;

    public RandomNum() {
    }

    public static String RandomNumber8() {
        String no = "";
        int[] defaultNumbers = new int[10];

        for(int i = 0; i < defaultNumbers.length; defaultNumbers[i] = i++) {
        }

        Random random = new Random();
        int[] nums = new int[LENGTH];
        int canUse = 10;

        int i;
        for(i = 0; i < nums.length; ++i) {
            int index = random.nextInt(canUse);
            nums[i] = defaultNumbers[index];
            swap(index, canUse - 1, defaultNumbers);
            --canUse;
        }

        if (nums.length > 0) {
            for(i = 0; i < nums.length; ++i) {
                no = no + nums[i];
            }
        }

      return no;
    }

    private static void swap(int i, int j, int[] nums) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
