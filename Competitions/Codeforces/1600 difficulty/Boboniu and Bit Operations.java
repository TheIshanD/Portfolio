import javax.print.attribute.SupportedValuesAttribute;
import java.io.*;
import java.lang.reflect.Array;
import java.security.interfaces.DSAKeyPairGenerator;
import java.util.*;
import java.util.function.Predicate;
 
 
public class Main{
 
 
 
    static class TaskB {
 
        int MOD = 1000000007;
 
 
        public long GCD(long a, long b){
            if(b==0)
            {
                return a;
            }else{
                return GCD(b, a % b);
            }
        }
 
        public long LCM(long a, long b){
            return (a/GCD(a,b) * b);
        }
 
        static int[][] dp;
        static int n, m;
        static int[] a, b;
 
        public static int recurse(int i, int val)
        {
            if(i==n)return val;
            if(dp[i][val]!=-1)return dp[i][val];
            int min = Integer.MAX_VALUE-1;
            for(int g = 0; g < m; g++)
            {
                min = Math.min(min, recurse(i+1,val|(a[i]&b[g])));
            }
            dp[i][val]=min;
            return min;
        }
        public void solve(InputReader in, PrintWriter out) {
 
            n = in.nextInt();
            m = in.nextInt();
            a= in.nextIntArray(n);
            b = in.nextIntArray(m);
 
            dp = new int[n][600];
            for(int i = 0; i < n; i++)
            {
                Arrays.fill(dp[i], -1);
            }
 
            out.println(recurse(0, 0));
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
 
 
    static class Graph
    {
        int V;
        LinkedList<Integer>[] adj;
        HashMap<String, Integer> convert;
        int stringName;
        boolean[] color;
        Graph(int V)
        {
            this.V = V;
            color = new boolean[V];
            adj = new LinkedList[V];
            convert = new HashMap<>();
            stringName = 0;
 
            for(int i = 0; i < V; i++)
            {
                adj[i] = new LinkedList<>();
            }
        }
 
        void addEdge(String v, String u)
        {
            if(!convert.containsKey(v))
            {
                convert.put(v, stringName);
                stringName++;
            }
            if(!convert.containsKey(u))
            {
                convert.put(u, stringName);
                stringName++;
            }
            addEdge(convert.get(v), convert.get(u));
        }
 
        void addEdge(int v, int u)
        {
            adj[v].add(u);
            //adj[u].add(v);  //USE IN UNDIRECTED GRAPHS
        }
 
        void color(int v) {
            for (int i : adj[v]) {
                color[i] = !color[v];
                color(i);
            }
        }
 
    }
 
 
    static void sort(int[] a)
    {
        ArrayList<Integer> list = new ArrayList<>();
        for(int i: a)list.add(i);
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
