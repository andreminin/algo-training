package training.yandex.test;

import java.io.*;

public class SolutionC {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String[] parts = reader.readLine().split(" ");
            int a = Integer.parseInt(parts[0]);
            int b = Integer.parseInt(parts[1]);

            long c = a + b;

            writer.write(String.valueOf(c));
            writer.newLine();
        }
    }
}
