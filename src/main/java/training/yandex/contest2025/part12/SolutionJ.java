package training.yandex.contest2025.part12;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SolutionJ {


    // Accelerated version of FigonInterpreter - manual commands parsing, regex is too expensive

    public static class FigonInterpreter {
        private final Map<String, FIntList> scope = new HashMap<>();

        private FIntList getScopeVar(String name) {
            FIntList list = scope.get(name);
            if (list == null) throw new IllegalArgumentException("Undefined variable: " + name);
            return list;
        }

        interface FIntList {
            void set(int index, int value);
            int get(int index);
            void add(int value);
            FIntList subList(int from, int to);
        }

        //Array based to optimize memory and performance
        class FIntArrList implements FIntList {
            private int[] arr;
            private int size;

            FIntArrList(int[] data, int size) {
                this.arr = data;
                this.size = size;
            }

            @Override
            public void set(int index, int value) {
                arr[index - 1] = value;
            }

            @Override
            public int get(int index) {
                return arr[index - 1];
            }

            @Override
            public void add(int value) {
                if (size == arr.length) {
                    int[] n = new int[arr.length * 2 + 4];
                    System.arraycopy(arr, 0, n, 0, arr.length);
                    arr = n;
                }
                arr[size++] = value;
            }

            @Override
            public FIntList subList(int from, int to) {
                //Skip "to" bounds check - there is no length/size() method in interface; let it fail in runtime later
                return new FIntSubList(this, from - 1);
            }
        }

        class FIntSubList implements FIntList {
            private final FIntArrList root;
            private final int base;

            //Skip "to" bounds check - there is no length/size() method in interface; let it fail in runtime later
            FIntSubList(FIntArrList root, int fromZeroBased) {
                this.root = root;
                this.base = fromZeroBased;
            }

            @Override
            public void set(int index, int value) {
                root.set(base + index, value);
            }

            @Override
            public int get(int index) {
                return root.get(base + index);
            }

            @Override
            public void add(int value) {
                throw new UnsupportedOperationException("add() is not supported in sublist!");
            }

            @Override
            public FIntList subList(int from, int to) {
                int newFrom = base + (from - 1);
                return new FIntSubList(root, newFrom);
            }
        }

        public Object executeCommand(String cmd) {
            cmd = cmd.trim();

            if (cmd.startsWith("List ")) {
                // "List <name> = new List(...)"
                // "List <name> = <var>.subList(from,to)"
                int eq = cmd.indexOf('=');
                if (eq < 0) throw new IllegalArgumentException("Bad command: " + cmd);
                String left = cmd.substring(5, eq).trim(); // after "List "
                String right = cmd.substring(eq + 1).trim();

                if (right.startsWith("new List(")) {
                    // new List(elements)
                    int open = right.indexOf('(');
                    int close = right.lastIndexOf(')');
                    String inner = right.substring(open + 1, close);
                    // parse comma separated ints
                    List<Integer> values = new ArrayList<>();
                    int i = 0;
                    int len = inner.length();
                    while (i < len) {
                        // skip spaces
                        while (i < len && inner.charAt(i) == ' ') i++;
                        int j = i;
                        while (j < len && inner.charAt(j) != ',') j++;
                        String numStr = inner.substring(i, j).trim();
                        if (!numStr.isEmpty()) values.add(Integer.parseInt(numStr));
                        i = j + 1;
                    }
                    // create root list
                    int[] data = new int[Math.max(values.size(), 4)];
                    for (int k = 0; k < values.size(); k++) data[k] = values.get(k);
                    FIntArrList arrList = new FIntArrList(data, values.size());
                    scope.put(left, arrList);
                    return null;
                } else {
                    // subList form: "<src>.subList(a,b)"
                    int dot = right.indexOf('.');
                    String srcName = right.substring(0, dot);
                    int open = right.indexOf('(', dot);
                    int comma = right.indexOf(',', open);
                    int close = right.indexOf(')', comma);
                    int from = Integer.parseInt(right.substring(open + 1, comma).trim());
                    int to = Integer.parseInt(right.substring(comma + 1, close).trim());
                    FIntList src = getScopeVar(srcName);

                    FIntList newView = src.subList(from, to);
                    scope.put(left, newView);
                    return null;
                }
            } else {
                // forms: "<name>.set(i,x)", "<name>.add(x)", "<name>.get(i)"
                int dot = cmd.indexOf('.');
                String name = cmd.substring(0, dot);
                String rest = cmd.substring(dot + 1);
                if (rest.startsWith("set(")) {
                    int open = rest.indexOf('(');
                    int comma = rest.indexOf(',', open);
                    int close = rest.indexOf(')', comma);
                    int idx = Integer.parseInt(rest.substring(open + 1, comma).trim());
                    int val = Integer.parseInt(rest.substring(comma + 1, close).trim());
                    FIntList list = getScopeVar(name);
                    list.set(idx, val);
                    return null;
                } else if (rest.startsWith("add(")) {
                    int open = rest.indexOf('(');
                    int close = rest.indexOf(')', open);
                    int val = Integer.parseInt(rest.substring(open + 1, close).trim());
                    FIntList list = getScopeVar(name);
                    list.add(val);
                    return null;
                } else if (rest.startsWith("get(")) {
                    int open = rest.indexOf('(');
                    int close = rest.indexOf(')', open);
                    int idx = Integer.parseInt(rest.substring(open + 1, close).trim());
                    FIntList list = getScopeVar(name);
                    return list.get(idx);
                } else {
                    throw new IllegalArgumentException("Unknown command: " + cmd);
                }
            }
        }
    }

    public static void testCase(String testName, String[] commands, Object[] expectedResults) {
        FigonInterpreter interpreter = new FigonInterpreter();

        for (int i = 0; i < commands.length; i++) {
            String command = commands[i];
            Object result = interpreter.executeCommand(command);
            if (expectedResults[i] == null) {
                if (result != null) {
                    throw new RuntimeException("Test \"" + testName + " error: \"" + command + "\" command result=\"" + result + "\", expected: " + expectedResults[i]);
                }
            } else if (!expectedResults[i].equals(result)) {
                throw new RuntimeException("Test \"" + testName + " error: \"" + command + "\" command result=\"" + result + "\", expected: " + expectedResults[i]);
            }
        }
    }


    public static void test() {
        testCase("test1", new String[]{
                "List a = new List(2,3,5)",
                "List b = a.subList(2,3)",
                "b.get(1)"}, new Object[]{null, null, 3});

        testCase("test2", new String[]{
                "List p = new List(2,4,8,16)",
                "p.get(4)",
                "List q = new List(3,9,27)",
                "q.add(5)",
                "q.get(4)"}, new Object[]{null, 16, null, null, 5});

        testCase("test3", new String[]{
                "List x = new List(1,2,5,14,42)",
                "List y = x.subList(1,4)",
                "List z = y.subList(2,4)",
                "y.set(1,7)",
                "x.get(1)",
                "z.get(1)",
                "z.set(2,100)",
                "x.get(3)",
                "y.get(3)",
                "x.add(132)",
                "x.set(5,43)",
                "x.get(5)",
                "y.get(4)"}, new Object[]{null, null, null, null, 7, 2, null, 100, 100, null, null, 43, 14});
    }


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String line = reader.readLine();
        int lineCount = Integer.parseInt(line);

        FigonInterpreter interpreter = new FigonInterpreter();

        for (int i = 0; i < lineCount; i++) {
            Object result = interpreter.executeCommand(reader.readLine());
            if (result != null) {
                writer.write(String.valueOf(result));
                writer.newLine();
            }
        }

        reader.close();
        writer.close();
    }
}
