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
 
        int n, m;
        int[] hasCats;
        ArrayList<Integer>[] adj;
 
        public int dfs(int v, int cats, int p)
        {
            if(hasCats[v]==1)cats+=1;
            else cats = 0;
            if(cats > m)return 0;
 
            int curCats = cats;
            int restaurants = 0;
            if(adj[v].size()==1 && v!=0)return 1;
 
            for(int i = 0; i < adj[v].size(); i++)
            {
                if(p!=adj[v].get(i))
                {
                    restaurants+=dfs(adj[v].get(i), cats, v);
                    cats = curCats;
                }
            }
            return restaurants;
        }
        public void solve(InputReader in, PrintWriter out) {
            n = in.nextInt();
            m = in.nextInt();
            hasCats = in.nextIntArray(n);
            adj = new ArrayList[n];
 
            for(int i = 0; i < n; i++)
                adj[i] = new ArrayList<>();
 
            int p, c;
            for(int i = 0; i < n-1; i++)
            {
                p = in.nextInt()-1;
                c = in.nextInt()-1;
                adj[p].add(c);
                adj[c].add(p);
            }
            out.println(dfs(0, 0, -1));
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
