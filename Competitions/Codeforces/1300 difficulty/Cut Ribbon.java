import java.io.*;
import java.util.StringTokenizer;
 
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
            int a = in.nextInt();
            int b = in.nextInt();
            int c = in.nextInt();
 
            int[] arr = new int[n+1];
 
            int minVal = Math.min(a,Math.min(b,c));
 
            for(int i = 0; i < n+1; i++)
            {
                arr[i]= -n;
            }
 
            arr[0] = 0;
 
            for(int i = minVal; i < n+1; i++)
            {
                if(i-a >= 0) arr[i] = Math.max(arr[i], arr[i-a]+1);
                if(i-b >= 0) arr[i] = Math.max(arr[i], arr[i-b]+1);
                if(i-c >= 0) arr[i] = Math.max(arr[i], arr[i-c]+1);
            }
            out.println(arr[n]);
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
