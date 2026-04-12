import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

public class MatchGroupingSymbols {

    private static class Symbol {
        char ch;
        int line;
        int col;
        Symbol(char ch, int line, int col) {
            this.ch = ch; this.line = line; this.col = col;
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("usage: java MatchGroupingSymbols <source-file>");
            System.exit(1);
        }

        File file = new File(args[0]);
        if (!file.isFile()) {
            System.out.println("file not found: " + args[0]);
            System.exit(1);
        }

        try {
            String result = checkFile(file);
            System.out.println(result);
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
            System.exit(2);
        }
    }

    private static String checkFile(File file) throws IOException {
        Deque<Symbol> stack = new ArrayDeque<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int lineNum = 0;
            boolean inBlockComment = false;

            while ((line = br.readLine()) != null) {
                lineNum++;
                int i = 0;
                int len = line.length();
                boolean inString = false;
                boolean inChar = false;
                boolean escape = false;
                while (i < len) {
                    char c = line.charAt(i);

                    if (inBlockComment) {
                        if (c == '*' && i + 1 < len && line.charAt(i + 1) == '/') {
                            inBlockComment = false;
                            i += 2;
                            continue;
                        } else {
                            i++;
                            continue;
                        }
                    }

                    if (!inString && !inChar) {
                        if (c == '/' && i + 1 < len && line.charAt(i + 1) == '/') {
                            break;
                        }
                        if (c == '/' && i + 1 < len && line.charAt(i + 1) == '*') {
                            inBlockComment = true;
                            i += 2;
                            continue;
                        }
                    }

                    if (!inChar && c == '"' && !escape) {
                        inString = !inString;
                        i++;
                        escape = false;
                        continue;
                    }

                    if (!inString && c == '\'' && !escape) {
                        inChar = !inChar;
                        i++;
                        escape = false;
                        continue;
                    }

                    if ((inString || inChar) && c == '\\' && !escape) {
                        escape = true;
                        i++;
                        continue;
                    } else {
                        escape = false;
                    }

                    if (!inString && !inChar) {
                        if (c == '(' || c == '{' || c == '[') {
                            stack.push(new Symbol(c, lineNum, i + 1));
                        } else if (c == ')' || c == '}' || c == ']') {
                            if (stack.isEmpty()) {
                                return String.format("unmatched closing '%c' at line %d, column %d",
                                        c, lineNum, i + 1);
                            }
                            Symbol top = stack.pop();
                            if (!matches(top.ch, c)) {
                                return String.format("mismatched symbol. opening '%c' at line %d, column %d "
                                                + "does not match closing '%c' at line %d, column %d",
                                        top.ch, top.line, top.col, c, lineNum, i + 1);
                            }
                        }
                    }

                    i++;
                }
            }
        }

        if (!stack.isEmpty()) {
            Symbol top = stack.peek();
            return String.format("unmatched opening '%c' at line %d, column %d",
                    top.ch, top.line, top.col);
        }

        return "all grouping symbols are matched correctly";
    }

    private static boolean matches(char open, char close) {
        return (open == '(' && close == ')')
            || (open == '{' && close == '}')
            || (open == '[' && close == ']');
    }
}
