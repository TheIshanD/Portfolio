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
                int n = in.nextInt();
                int p1=0, p2=0, c1=0, c2=0;
 
                String toPrint = "YES";
                while(n-->0)
                {
                    p1 = p2;
                    c1 = c2;
 
                    p2 = in.nextInt();
                    c2 = in.nextInt();
 
                    if(p1 > p2)
                    {
                        toPrint="NO";
                        while(n-->0)
                        {
                            in.next();
                            in.next();
                        }
                        break;
                    }
                    if(c1 > c2)
                    {
                        toPrint="NO";
                        while(n-->0)
                        {
                            in.next();
                            in.next();
                        }
                        break;
                    }
                    if(c2>p2)
                    {
                        toPrint="NO";
                        while(n-->0)
                        {
                            in.next();
                            in.next();
                        }
                        break;
                    }
                    if(p2 - p1 < c2-c1)
                    {
                        toPrint="NO";
                        while(n-->0)
                        {
                            in.next();
                            in.next();
                        }
                        break;
                    }
 
                }
                out.println(toPrint);
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
