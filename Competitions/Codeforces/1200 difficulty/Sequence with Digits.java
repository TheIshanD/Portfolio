import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
 
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskB solver = new TaskB();
        solver.solve(in, out);
        out.close();
    }
 
    static class TaskB {
        public void solve(InputReader in, PrintWriter out) {
            int t = in.nextInt();
            while(t-->0)
            {
                String a1 = in.next();
                long K = Long.parseLong(in.next());
 
 
                for(int i = 1; i < K; i++)
                {
 
                    int min = 9;
                    int max = 0;
 
                    for(int g = 0; g < a1.length(); g++)
                    {
                        min = Math.min(a1.charAt(g)-'0', min);
                        max = Math.max(a1.charAt(g)-'0', max);
                    }
                    if(min == 0)break;
                    a1 = String.valueOf(Long.parseLong(a1) + min*max);
                }
                out.println(a1);
            }
        }
    }
 
        static class InputReader {
        private BufferedReader reader;
        private StringTokenizer tokenizer = new StringTokenizer("");
 
        public InputReader(InputStream inputStream) {
            this.reader = new BufferedReader(
                    new InputStreamReader(inputStream));
        }
 
        public String next() {
            while (!tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }
 
        public int nextInt() {
            return Integer.parseInt(next());
        }
 
        public int[] nextIntArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = nextInt();
            return a;
        }
 
    }
}
