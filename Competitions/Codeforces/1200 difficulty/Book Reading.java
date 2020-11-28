import javax.print.attribute.SupportedValuesAttribute;
import java.io.*;
import java.util.*;
 
public class Problem1 {
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
            int T = in.nextInt();
            while(T-->0)
            {
                long n = Long.parseLong(in.next());
                long m = Long.parseLong(in.next());
 
                long totalSum = 0;
 
                int sum = 0;
 
                long remainder = n;
 
                if(n/m >= 10)
                {
                    for(int i = 1; i < 10; i++)
                    {
                        sum+=(m*i)%10;
                    }
 
                    totalSum = sum*(n/(10 * m));
                    remainder = n - (n/(10 * m)*(10*m));
                }
 
 
 
                for(int g = 1; g*m <= remainder; g++)
                {
                    totalSum+=(g*m)%10;
                }
 
                System.out.println(totalSum);
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
 
        public long nextLong() {
                return Long.parseLong(next());
            }
 
        public int[] nextIntArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = nextInt();
            return a;
        }
 
    }
}
