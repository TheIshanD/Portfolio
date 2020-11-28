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
            while(n-->0)
            {
                int c = in.nextInt();
                int m = in.nextInt();
                int x = in.nextInt();
 
                int capNum = 0;
 
                if(c<=m)
                {
                    capNum=c;
                    x+=(m-capNum);
                }else{
                    capNum=m;
                    x+=(c-capNum);
                }
 
                if(capNum>x)
                {
                    int g =(int)Math.ceil((capNum-x)/3.0);
                    x+=2*g;
                    capNum-=g;
                }
 
 
                out.println(Math.min(capNum, x));
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
