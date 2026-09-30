package training.yandex.test;

import java.io.*;

public class SolutionB {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {

            String[] parts = reader.readLine().split(" ");
            int a = Integer.parseInt(parts[0]);
            int b = Integer.parseInt(parts[1]);

            long c = a + b;

            writer.write(String.valueOf(c));
            writer.newLine();
        }
    }
}
