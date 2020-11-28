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
        public void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int l = in.nextInt();
            int r= in.nextInt();
            int x = in.nextInt();
 
            int[] arr = in.nextIntArray(n);
 
            int cnt = 0;
            for(int i = 0; i < (1<<n); i++)
            {
                int sum = 0;
                int max = 0;
                int min = 0;
 
                boolean first = true;
                for(int g = n-1; g >=0; g--)
                {
                    if((i & (1 << g)) != 0)
                    {
                        sum+=arr[g];
                        if(first){min = arr[g];first = false;}
                        max = Math.max(max, arr[g]);
                        min = Math.min(min, arr[g]);
                    }
                }
                if(sum >= l && sum <=r && (max-min)>=x)cnt++;
            }
            out.print(cnt);
 
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
