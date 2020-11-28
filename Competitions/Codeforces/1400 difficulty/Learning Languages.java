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
 
        int n, m, t;
        LinkedList<Integer>[] arr;
        boolean[] visited;
 
        public void dfs(int v)
        {
            visited[v]=true;
            for(int i = 0; i < arr[v].size(); i++)
            {
                if(!visited[arr[v].get(i)])
                {
                    dfs(arr[v].get(i));
                }
            }
        }
 
        public void solve(InputReader in, PrintWriter out) {
            n = in.nextInt();
            m = in.nextInt();
            t = n+m;
            arr = new LinkedList[t];
            visited = new boolean[t];
 
            boolean all0 = true;
            for(int i = 0; i < t; i++)
            {
                arr[i] = new LinkedList<>();
            }
            for(int i = 0; i < n; i++)
            {
                int langs = in.nextInt();
                for(int g = 0; g < langs; g++)
                {
                    int l = in.nextInt()-1+n;
                    arr[i].add(l);
                    arr[l].add(i);
                }
                if(langs!=0)all0=false;
            }
            if(all0)
            {
                out.print(n);
                return;
            }
 
            int ans = 0;
            for(int i = 0; i < t; i++)
            {
                if(!visited[i] && i < n)
                {
                    dfs(i);
                    ans++;
                }
            }
            out.print(--ans);
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
