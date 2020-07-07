package top20words;

/**
 * @author Yuriy Tumakha
 */
public class Main {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage:\njava -jar top20words.jar <filename>");
            return;
        }

        new Top20WordsReader(args[0]);
    }

}
