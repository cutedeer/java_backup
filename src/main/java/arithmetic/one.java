package arithmetic;

/**
 * 二维数组中的查找
 *
 * @author shuzhuang.su
 * @date 2020-05-12 19:41
 */
public class one {

    public boolean Find(int target, int[][] array) {

        for (int i = 0; i < array.length; i++) {
            for (int j =0; j < array[i].length; j++) {
                if (array[i][j] == target) {
                    return true;
                }
                if (target > array[i][j]) {
                    break;
                }
            }
        }
        return false;
    }
}
