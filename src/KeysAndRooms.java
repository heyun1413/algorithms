/**
 * @author ron
 * 有一个房间列表[0, 1, 2, 3, N - 1]
 * <p>
 * 每一个房间还有一个钥匙列表[[0, 1], [2, 3]]
 * 并且每一个房间的钥匙中可能包含其他房间的钥匙
 */
public class KeysAndRooms {

    public static void main(String[] args) {
        int[][] roomsAndKeys = {
                {1},
                {2},
                {3},
                { }
        };
        assert couldOpenAll(roomsAndKeys);

        int[][] roomsAndKeys2 = {
                {1, 3},
                {3, 2, 1},
                {2},
                {0}
        };
        assert couldOpenAll(roomsAndKeys2);
    }

    /**
     * 是否可以打开所有房间
     *
     * @return true 可以 否则不可以
     */
    private static boolean couldOpenAll(int[][] roomsAndKeys) {
        boolean[] result = new boolean[roomsAndKeys.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = false;
        }
        couldOpenAllRoom(roomsAndKeys, 0, result);
        for (boolean b : result) {
            if (!b) {
                return false;
            }
        }
        return true;
    }


    /**
     * 使用深度优先，也可使用stack结构实现该功能
     * @param roomsAndKeys
     * @param index
     * @param result
     */
    private static void couldOpenAllRoom(int[][] roomsAndKeys, int index, boolean[] result) {

        result[index] = true;
        for (int i : roomsAndKeys[index]) {
            if (result[i]) {
                continue;
            }

            couldOpenAllRoom(roomsAndKeys, i, result);
        }
    }

}
