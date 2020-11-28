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
 
        int[] arr;
        
        public boolean rotate(int pointer, int sum)
        {
            if(pointer == arr.length)
            {
                if(sum%360==0)return true;
                return false;
            }
            return(rotate(pointer+1, sum+arr[pointer]) || rotate(pointer+1, sum-arr[pointer]));
 
        }
        public void solve(InputReader in, PrintWriter out) {
            int T = in.nextInt();
 
            arr = new int[T];
            for(int i = 0; i < T; i++)
            {
                arr[i] = in.nextInt();
            }
 
            if(rotate(0, 0))
            {
                out.println("YES");
            }else{
                out.println("NO");
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
