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
        static HashMap<String, Integer> dp = new HashMap<>();
 
        public void solve(InputReader in, PrintWriter out) {
 
 
            int T = in.nextInt();
            while(T-->0) {
                int n = in.nextInt();
                int m = in.nextInt();
 
                int[] arr = in.nextIntArray(n);
                int[] swaps = in.nextIntArray(m);
 
                Arrays.sort(swaps);
 
                boolean swapped = true;
 
                while(swapped)
                {
                    swapped = false;
                    for(int i = 0; i < swaps.length; i++)
                    {
                        if(arr[swaps[i]]<arr[swaps[i]-1]) {
                            int temp = arr[swaps[i]];
                            arr[swaps[i]] = arr[swaps[i] - 1];
                            arr[swaps[i] - 1] = temp;
                            swapped = true;
                        }
                    }
                }
                boolean increasing = true;
                for(int i = 1; i < arr.length; i++)
                {
                    if(arr[i]<arr[i-1]){increasing = false; break;}
                }
                if(!increasing)out.println("NO");
                else out.println("YES");
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
