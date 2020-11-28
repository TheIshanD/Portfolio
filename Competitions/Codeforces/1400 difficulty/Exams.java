import javax.management.Query;
import javax.print.attribute.SupportedValuesAttribute;
import java.io.*;
import java.security.interfaces.DSAKeyPairGenerator;
import java.sql.Array;
import java.util.*;
import java.util.function.Predicate;
 
 
public class Main {
 
    static class TaskB {
 
        static int oo = Integer.MAX_VALUE - 1;
 
        static int MOD = 1000000007;
 
 
        public void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();
            Pair[] days = new Pair[n];
            for(int i = 0; i < n; i++)
            {
                days[i] = new Pair(in.nextInt(), in.nextInt());
            }
            Arrays.sort(days);
            int curDay = 1;
            for(int i = 0; i < n; i++)
            {
                if(days[i].num2==curDay)
                {
                    continue;
                }
                if(days[i].num2>curDay)
                {
                    curDay=days[i].num2;
                    continue;
                }
                curDay = days[i].num1;
            }
            out.println(curDay);
        }
        public class Pair implements Comparable
        {
            int num1, num2;
 
            Pair(int num1, int num2)
            {
                this.num1 = num1;
                this.num2 = num2;
            }
 
            @Override
            public int compareTo(Object o) {
                if(num1<((Pair)o).num1)return -1;
                else if(num1>((Pair)o).num1)return 1;
                else{
                    if(num2<((Pair)o).num2)return -1;
                    else if(num2>((Pair)o).num2)return 1;
                    else return 0;
                }
            }
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
 
 
 
    static void sort(int[] a)
    {
        ArrayList<Integer> list = new ArrayList<>();
        for(Integer i: a)list.add(i);
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
