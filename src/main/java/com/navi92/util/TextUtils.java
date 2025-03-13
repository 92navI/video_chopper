package com.navi92.util;

public class TextUtils {
    public static String format(String text) {
        if (text.contains("&{")) {
            int index = Integer.parseInt(
                    text.substring(
                            text.indexOf("&{") + 2,
                            text.indexOf("}a")));
            var bufferedText = new StringBuilder(text);
            bufferedText.replace(
                    text.indexOf("&{"),
                    text.indexOf("}a") + 2,
                    index_color(index));
            text = bufferedText.toString();
        }

        return text
                .replace("&&", "&")
                .replace("&B", TextFormat.BLACK)
                .replace("&r", TextFormat.RED)
                .replace("&g", TextFormat.GREEN)
                .replace("&y", TextFormat.YELLOW)
                .replace("&b", TextFormat.BLUE)
                .replace("&p", TextFormat.PURPLE)
                .replace("&c", TextFormat.CYAN)
                .replace("&w", TextFormat.WHITE)

                .replace("<b>", TextFormat.BOLD)
                .replace("</b>", TextFormat.BOLD_R)

                .replace("<i>", TextFormat.ITALIC)
                .replace("</i>", TextFormat.ITALIC_R)

                .replace("<d>", TextFormat.DIM)
                .replace("</d>", TextFormat.DIM_R)

                .replace("<del>", TextFormat.STRIKETHROUGH)
                .replace("</del>", TextFormat.STRIKETHROUGH_R)

                .replace("<ins>", TextFormat.UNDERLINE)
                .replace("</ins>", TextFormat.UNDERLINE_R);
    }

    public static void up(int amount) {
        System.out.print(String.format("\033[%dA\r", amount));
    }

    public static void down(int amount) {
        System.out.print(String.format("\033[%dA\r", (amount * -1)));
    }

    public static void del() {
        System.out.print("\u001B[2k\r");
    }

    public static void clear() {
        System.out.print("\033[2J\r");
    }

    public static void del_up(int amount) {
        for (int i = 0; i <= amount; i++) {
            up(1);
            del();
        }
    }

    public static void del_down(int amount) {
        for (int i = 0; i <= amount; i++) {
            down(1);
            del();
        }
    }

    public static String index_color(int color_code) {
        return "\033[38;5;" + color_code + "m";
    }
}