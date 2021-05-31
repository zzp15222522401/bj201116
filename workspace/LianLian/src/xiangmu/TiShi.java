package xiangmu;
import java.util.Scanner;
public class TiShi {
    //输入入口的工具类
        private static Scanner scanner = new Scanner(System.in);
        //读取主菜单方法
        public static char readMenuSelection() {
            char c;
            for (; ; ) {
                String str = readKeyBoard(1, false);//输入长度限制1，回车不允许
                c = str.charAt(0);
                if (c != '1' && c != '2' &&
                        c != '3' && c != '4' && c != '5') {
                    System.out.print("选择错误，请重新输入：");
                } else break;
            }
            return c;
        }

        public static char readChar() {
            String str = readKeyBoard(1, false);
            return str.charAt(0);
        }

        public static char readChar(char defaultValue) {
            String str = readKeyBoard(1, true);
            return (str.length() == 0) ? defaultValue : str.charAt(0);
        }

        public static int readInt() {
            int n;
            for (; ; ) {
                String str = readKeyBoard(2, false);
                try {
                    n = Integer.parseInt(str);
                    break;
                } catch (NumberFormatException e) {
                    System.out.print("数字输入错误，请重新输入：");
                }
            }
            return n;
        }

        public static int readInt(int defaultValue) {
            int n;
            for (; ; ) {
                String str = readKeyBoard(2, true);
                if (str.equals("")) {
                    return defaultValue;
                }

                try {
                    n = Integer.parseInt(str);
                    break;
                } catch (NumberFormatException e) {
                    System.out.print("数字输入错误，请重新输入：");
                }
            }
            return n;
        }

        public static String readString(int limit) {
            return readKeyBoard(limit, false);
        }

        public static String readString(int limit, String defaultValue) {
            String str = readKeyBoard(limit, true);
            return str.equals("")? defaultValue : str;
        }

        public static char readConfirmSelection() {
            char c;
            for (; ; ) {
                String str = readKeyBoard(1, false).toUpperCase();
                c = str.charAt(0);
                if (c == 'Y' || c == 'N') {
                    break;
                } else {
                    System.out.print("选择错误，请重新输入：");
                }
            }
            return c;
        }

        private static String readKeyBoard(int limit, boolean blankReturn) {
            String line = "";//方法定义：限制长度，确定回车（不输入直接换行）是否可行

            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if (line.length() == 0) {
                    if (blankReturn) return line;
                    else continue;
                }

                if (line.length() < 1 || line.length() > limit) {
                    System.out.print("输入长度（不大于" + limit + "）错误，请重新输入：");
                    continue;
                }
                break;
            }

            return line;
        }
    }



