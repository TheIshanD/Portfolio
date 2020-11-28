import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
 
public class Main {
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
 
        public boolean checkIfContains(String[] sA, String s)
        {
            for(String str: sA)
            {
                if(s.equals(str))
                {
                    return true;
                }
            }
            return false;
        }
        public void solve(InputReader in, PrintWriter out) {
            int t = in.nextInt();
            HashMap<String, Integer> sCount = new HashMap<>();
 
            for(int i = 0; i < t; i++)
            {
                String s = in.next();
                if(sCount.containsKey(s))
                {
                    int l = sCount.get(s);
                    out.println(s+l);
                    sCount.put(s, 1+l);
                }else{
                    sCount.put(s, 1);
                    out.println("OK");
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
 
        public int[] nextIntArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = nextInt();
            return a;
        }
 
    }
}
