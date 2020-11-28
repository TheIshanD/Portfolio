import javax.print.attribute.SupportedValuesAttribute;
import java.io.*;
import java.util.*;
import java.util.function.Predicate;
 
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
            int n = in.nextInt();
            int[] arr = in.nextIntArray(n);
 
            Arrays.sort(arr);
 
            long[] postfixSum = new long[n];
 
            postfixSum[n-1] = arr[n-1];
            for(int i = n-2; i >=0; i--)
            {
                postfixSum[i]=postfixSum[i+1]+arr[i];
            }
 
            long sum = 0;
            for(int i = 0; i < n; i++)
            {
                sum+=postfixSum[i]+arr[i];
            }
 
            out.println(sum-arr[n-1]);
 
 
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
