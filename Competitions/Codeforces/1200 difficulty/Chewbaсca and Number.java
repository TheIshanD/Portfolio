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
            String num = in.next();
            if(num.charAt(0)=='9')
            {
 
            }else{
                if(num.charAt(0)>='5')
                {
                    num =String.valueOf(9-Integer.parseInt(Character.toString(num.charAt(0))))+num.substring(1);
                }
            }
 
            int len = num.length();
 
            for(int i = 1; i < len; i++)
            {
                if(num.charAt(i)>='5')
                {
                    num = num.substring(0,i)+String.valueOf(9-Integer.parseInt(Character.toString(num.charAt(i))))+num.substring(i+1);
                }
            }
 
            out.println(num);
 
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
