import javax.print.attribute.SupportedValuesAttribute;
import java.io.*;
import java.lang.reflect.Array;
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
                int nums = in.nextInt();
                int k = in.nextInt();
 
                ArrayList<Integer> answer = new ArrayList<>();
 
                int theNum = 0;
                long sum = 0;
                for(int i = 0; i < nums; i++)
                {
                    sum+= in.nextInt();
                    if(sum%2==1)
                    {
                        if(answer.size() == k-1  && i!=nums-1)
                        {
                            continue;
                        }
                        answer.add(i+1);
                        sum=0;
                    }
                }
                if(answer.size()==k)
                {
                    out.println("YES");
                    for(int i = 0; i < k; i++)
                    {
                        out.print(answer.get(i)+" ");
                    }
                    out.println();
                }else{
                    out.println("NO");
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
