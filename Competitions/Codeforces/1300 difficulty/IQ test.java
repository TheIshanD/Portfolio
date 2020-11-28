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
            int[] nums = in.nextIntArray(n);
 
            int evens = 0;
 
            boolean even = true;
            for(int i = 0; i < 3; i++)
            {
                if(nums[i]%2 == 0)
                {
                    evens++;
                }
            }
 
            if(!(evens>=2)) even = false;
            for(int i = 0; i < nums.length; i++)
            {
                if(even)
                {
                    if(nums[i]%2 == 1)
                    {
                        out.print(i+1);
                        break;
                    }
                }else{
                    if(nums[i]%2 == 0)
                    {
                        out.print(i+1);
                        break;
                    }
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
