package training.yandex.contest2021;

import java.io.*;

public class SolutionA {

    /*
        В офисе, где работает программист Петр, установили кондиционер нового типа. Этот кондиционер отличается особой простотой в управлении. У кондиционера есть всего лишь два управляемых параметра: желаемая температура и режим работы.

        Кондиционер может работать в следующих четырех режимах:

        «freeze» — охлаждение. В этом режиме кондиционер может только уменьшать температуру. Если температура в комнате и так не больше желаемой, то он выключается.

        «heat» — нагрев. В этом режиме кондиционер может только увеличивать температуру. Если температура в комнате и так не меньше желаемой, то он выключается.

        «auto» — автоматический режим. В этом режиме кондиционер может как увеличивать, так и уменьшать температуру в комнате до желаемой.

        «fan» — вентиляция. В этом режиме кондиционер осуществляет только вентиляцию воздуха и не изменяет температуру в комнате.

        Кондиционер достаточно мощный, поэтому при настройке на правильный режим работы он за час доводит температуру в комнате до желаемой.

        Требуется написать программу, которая по заданной температуре в комнате troomtroom​, установленным на кондиционере желаемой температуре tcondtcond​ и режиму работы определяет температуру, которая установится в комнате через час.
        Формат ввода

        Первая строка входного файла содержит два целых числа troom, и tcond, разделенных ровно одним пробелом ( –50≤troom≤50–50≤troom​≤50, –50≤tcond≤50–50≤tcond​≤50).

        Вторая строка содержит одно слово, записанное строчными буквами латинского алфавита — режим работы кондиционера.
        Формат вывода

        Выходной файл должен содержать одно целое число — температуру, которая установится в комнате через час.
        Пример 1
        Ввод
        Вывод

        10 20
        heat



        20

        Пример 2
        Ввод
        Вывод

        10 20
        freeze

     */

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String[] parts = reader.readLine().split(" ");
            int tRoom = Integer.parseInt(parts[0]);
            int tCond = Integer.parseInt(parts[1]);
            String mode = reader.readLine();
            int result;

            if ("freeze".equals(mode)) {
                result = Math.min(tRoom, tCond);
            } else if ("heat".equals(mode)) {
                result = Math.max(tRoom, tCond);
            } else if ("auto".equals(mode)) {
                result = tCond;
            } else if ("fan".equals(mode)) {
                result = tRoom;
            } else {
                throw new RuntimeException("Unrecognized mode: " + mode);
            }

            writer.write(String.valueOf(result));
        }
    }
}
