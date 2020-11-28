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
 
 
            int[] oddPrefix = new int[100];
            oddPrefix[0] = 1;
 
            for(int i = 3; i < 200; i+=2)
            {
                oddPrefix[i/2] = i + oddPrefix[i/2 - 1];
            }
 
            while(T-->0)
            {
                int n = in.nextInt();
                int k = in.nextInt();
 
                if(k>n)
                {
                    out.println("NO");
                    continue;
                }
 
                if(n%2==1)
                {
                    if(k%2==0)
                    {
                        out.println("NO");
                        continue;
                    }
                    out.println("YES");
                    for(int i = 0; i < k-1; i++)
                    {
                        out.print(1+" ");
                    }
                    out.println(n-(k-1));
                }else{
                    if(k%2==1)
                    {
                        if(k*2 <= n)
                        {
                            out.println("YES");
                            for(int i = 0; i < k-1; i++)
                            {
                                out.print(2+" ");
                            }
                            out.println(n-2*(k-1));
                        }else{
                            out.println("NO");
                        }
                        continue;
                    }
                    out.println("YES");
                        for(int i = 0; i < k-1; i++)
                        {
                            out.print(1+" ");
                        }
                        out.println(n-(k-1));
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
