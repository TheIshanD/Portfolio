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
            int x1 = in.nextInt();
            int y1 = in.nextInt();
            int x2 = in.nextInt();
            int y2 = in.nextInt();
 
            int x3 = 0, y3 = 0, x4 = 0, y4 = 0;
 
            int side = 0;
 
 
 
 
            if(x1==x2)
            {
 
                side = Math.abs(y1 - y2);
 
                x3 = x1 + side;
                x4 = x1 + side;
 
                y3 = y2;
                y4 = y1;
 
                out.print(x3+" "+y3+" "+x4+" "+y4);
            }
            else if(y1 == y2)
            {
 
                side = Math.abs(x1 - x2);
 
                y3 = y1 + side;
                y4 = y1 + side;
 
                x3 = x2;
                x4 = x1;
 
                out.print(x3+" "+y3+" "+x4+" "+y4);
            }
            else{
                side = Math.abs(x1 - x2);
 
                if(Math.abs(y1-y2)==side)
                {
                    x3 = x2;
                    y3 = y1;
 
                    x4 = x1;
                    y4 = y2;
 
                    out.print(x3+" "+y3+" "+x4+" "+y4);
                }else{
                    out.print(-1);
                }
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
