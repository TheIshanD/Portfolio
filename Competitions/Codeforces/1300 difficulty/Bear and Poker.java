import javax.management.Query;
import javax.print.attribute.SupportedValuesAttribute;
import java.io.*;
import java.lang.reflect.Array;
import java.security.interfaces.DSAKeyPairGenerator;
import java.util.*;
import java.util.function.Predicate;
 
 
public class Main {
 
    static class TaskB {
 
        static int oo = Integer.MAX_VALUE-1;
 
        public void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int[] arr = in.nextIntArray(n);
 
            boolean good = true;
            HashMap<Integer, Integer> table = new HashMap<>();
            int start = reduce(arr[0]);
            table.put(arr[0], start);
            for(int i = 1; i < n; i++)
            {
                if(table.containsKey(arr[i]))
                {
                    continue;
                }
                int reduced = reduce(arr[i]);
                table.put(arr[i], reduced);
                if(reduced!=start)
                {
                    good = false;
                    break;
                }
            }
            out.println((good)?"Yes":"No");
 
        }
 
        public int reduce(int g)
        {
            while(g%3==0)
            {
                g/=3;
            }
            while(g%2==0)
            {
                g/=2;
            }
            return g;
        }
    }
 
 
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskB solver = new TaskB();
        solver.solve(in, out);
        out.close();
    }
 
 
 
    static void sort(long[] a)
    {
        ArrayList<Long> list = new ArrayList<>();
        for(Long i: a)list.add(i);
        Collections.sort(list);
        for(int i = 0; i < a.length; i++)a[i]=list.get(i);
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
