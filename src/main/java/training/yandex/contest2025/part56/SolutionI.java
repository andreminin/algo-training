package training.yandex.contest2025.part56;

import java.io.*;
import java.util.Arrays;

public class SolutionI {

    /*
      Арифметические выражения, использующие сложение, вычитание, умножение, деление и возведение в степень определяются следующей грамматикой:

<выражение> -> <слагаемое> | <выражение> + <слагаемое> | <выражение> - <слагаемое>

<слагаемое> -> <множитель> | <слагаемое> * <множитель> | <слагаемое> / <множитель>

<множитель> -> <элемент> | <элемент> ^ <множитель>

<элемент> -> <переменная> | (<выражение>)

<переменная> -> a | b | ... | z

Сложение, вычитание, умножение и деление левоассоциативны, а возведение в степень правоассоциативно. Для арифметического выражения определено его дерево разбора. Это двоичное дерево, в котором внутренние узлы соответствуют бинарным операциям, а листья соответствуют переменным. Дерево строится рекурсивно.

Дерево для переменной — это дерево из одной вершины, в которой записана эта переменная.

Дерево для элемента, являющегося выражением в скобках, — это дерево для самого выражения.

Дерево для множителя, являющегося элементом, — это дерево для этого элемента.

Дерево для множителя вида «элемент e, возведенный в степень множитель f» — это дерево, в котором в корне записана операция ‘^’, левое поддерево корня — дерево для элемента ee, правое поддерево корня — дерево для множителя ff.

Деревья для множителя и слагаемого определяются аналогично, с тем лишь различием, что соответствующие операции лево-ассоциативные.

Вам дано арифметическое выражение, выведите его дерево разбора.
Формат ввода

Вводится корректное арифметическое выражение, состоящее не более чем из 400 символов.
Формат вывода

Выведите дерево разбора. Дерево разбора для переменной должно быть размера 1×11×1 и содержать эту переменную. Дерево, в корне которого записана операция, с поддеревьями T1T1​ и T2T2​, которые имеют размеры h1×w1h1​×w1​ и h2×w2h2​×w2​ соответственно, должно быть размера (max[h1;h2]+2)×(w1+w2+5)(max[h1​;h2​]+2)×(w1​+w2​+5). Подробнее о формате вывода можно узнать, изучив пример вывода (см. ниже). Следует использовать следующие вспомогательные символы: минус ‘-’ (код ASCII 45), точка ‘.’ (код ASCII 46), вертикальная черта ‘|’ (код ASCII 124), квадратные скобки ‘[’ и ‘]’ (коды ASCII 91 and 93).
Пример 1
Ввод
Вывод

(a+b+c)*(d-a)



         .----[*]----.
         |           |
   .----[+]-.     .-[-]-.
   |        |     |     |
.-[+]-.     c     d     a
|     |
a     b

Пример 2
Ввод
Вывод

a



a

Пример 3
Ввод
Вывод

a+b



.-[+]-.
|     |
a     b

     */


    static class Node {
        char value;
        Node left, right;

        Node(char value) {
            this.value = value;
        }

        Node(char value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    public static void printTree(Node root, BufferedWriter writer) throws IOException {

        int treeHeight = height(root);

        int rows = treeHeight * 2 + 1;

        int[] layout = horizLayout(root);

        int columns = layout[0];

        char[][] canvas = new char[rows][columns];

        for (char[] row : canvas) {
            Arrays.fill(row, ' ');
        }

        printTree(root, canvas, 0, layout[1]);

        for (char[] row : canvas) {
            writer.write(new String(row));
            writer.newLine();
        }
    }

    private static void printTree(Node node, char[][] canvas, int treeRow, int xCenter) {
        if (node == null) {
            return;
        }

        int y = treeRow * 2;
        canvas[y][xCenter] = node.value;
        if (node.left == null && node.right == null) {
            return;
        }
        canvas[y][xCenter - 1] = '[';
        canvas[y][xCenter + 1] = ']';

        if (node.left != null) {
            int[] layout = horizLayout(node.left);
            int leftCenter = xCenter - 2 - layout[0] + layout[1];
            canvas[y][leftCenter] = '.';
            canvas[y + 1][leftCenter] = '|';

            for (int i = leftCenter + 1; i + 1 < xCenter; i++) {
                canvas[y][i] = '-';
            }

            printTree(node.left, canvas, treeRow + 1, leftCenter);
        }

        if (node.right != null) {
            int[] layout = horizLayout(node.right);
            int rightCenter = xCenter + 3 + layout[1];
            canvas[y][rightCenter] = '.';
            canvas[y + 1][rightCenter] = '|';

            for (int i = xCenter + 2; i < rightCenter; i++) {
                canvas[y][i] = '-';
            }

            printTree(node.right, canvas, treeRow + 1, rightCenter);
        }
    }

    // width, center offset
    private static int[] horizLayout(Node node) {
        if (node == null) {
            return new int[]{1, 0};
        }

        if (node.left == null && node.right == null) {
            return new int[]{1, 0};
        }

        int width = 3;
        int[] childLayout;
        if (node.left != null) {
            childLayout = horizLayout(node.left);
            width += 1 + childLayout[0];
        }
        int offset = width - 2;

        if (node.right != null) {
            childLayout = horizLayout(node.right);
            width += 1 + childLayout[0];
        }

        return new int[]{width, offset};
    }

    private static int height(Node node) {
        if (node == null) {

            return -1;
        }

        return 1 + Math.max(height(node.left), height(node.right));
    }

    private static int pos;
    private static String input;

    // (a+b+c)*(d-a)
    // (a+b+c)*(d-a)+(h-f)^3
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            input = reader.readLine().trim();
            pos = 0;

            Node parseTree = parseExpression();
            printTree(parseTree, writer);
        }
    }

    private static Node parseExpression() {
        Node left = parseTerm();

        while (pos < input.length() && (input.charAt(pos) == '+' || input.charAt(pos) == '-')) {
            char op = input.charAt(pos++);
            Node right = parseTerm();
            left = new Node(op, left, right);
        }

        return left;
    }

    private static Node parseTerm() {
        Node left = parseFactor();

        while (pos < input.length() && (input.charAt(pos) == '*' || input.charAt(pos) == '/')) {
            char op = input.charAt(pos++);
            Node right = parseFactor();
            left = new Node(op, left, right);
        }

        return left;
    }

    private static Node parseFactor() {
        Node left = parseElement();

        if (pos < input.length() && input.charAt(pos) == '^') {
            char op = input.charAt(pos++);
            Node right = parseFactor(); // Right associative
            return new Node(op, left, right);
        }

        return left;
    }

    private static Node parseElement() {
        if (pos < input.length() && input.charAt(pos) == '(') {
            pos++; // skip '('
            Node expr = parseExpression();
            pos++; // skip ')'
            return expr;
        } else {
            char var = input.charAt(pos++);
            return new Node(var);
        }
    }
}
