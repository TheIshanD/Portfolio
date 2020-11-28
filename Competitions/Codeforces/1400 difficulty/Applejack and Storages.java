import javax.print.attribute.SupportedValuesAttribute;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Predicate;
 
 
public class Main{
 
    static class TaskB {
        public void solve(InputReader in, PrintWriter out) {
            int T = in.nextInt();
 
            int[] arr = new int[T];
 
            int[] dp = new int[100005];
            int max = 0;
            int[] dp2 = new int[100005];
            for(int tt = 0; tt < T; tt++)
            {
                int num = in.nextInt();
                arr[tt]= num;
                dp[num]++;
                max = Math.max(num, max);
 
            }
            int count=0;
            for(int tt = 0; tt < max+1; tt++)
            {
                if(dp[tt]>=8)count++;
                dp2[dp[tt]]++;
            }
 
            int q = in.nextInt();
            for(int i = 0; i < q; i++)
            {
                String sign = in.next();
                int num = in.nextInt();
 
 
                if(sign.equals("+"))
                {
                    max = Math.max(num, max);
                    if(dp[num]==7)count++;
                    dp2[dp[num]]--;
                    dp[num]++;
                    dp2[dp[num]]++;
                }else{
                    if(dp[num]==8)count--;
                    dp2[dp[num]]--;
                    dp[num]--;
                    dp2[dp[num]]++;
                }
 
                boolean squareGood = false;
                int rectGood = 0;
 
                if(count>=1)
                {
                    out.println("YES");
                    continue;
                }
                for(int g = 7; g >=1; g--)
                {
                    if(dp2[g]>=1)
                    {
                        if(dp2[g]>=2)
                        {
                            if(g==7 || g==6)
                            {
                                    squareGood=true;
                                    rectGood=2;
                                    break;
                            }
                            else if(g==5 || g==4)
                            {
                                squareGood=true;
                                rectGood=2;
                            }
                            else if(g==3 || g==2)
                            {
                                rectGood=2;
                            }
                        }else{
                            if(g==7 || g==6)
                            {
                                if(!squareGood)
                                {
                                    squareGood=true;
                                    rectGood++;
                                }else{
                                    rectGood=2;
                                    break;
                                }
                            }
                            else if(g==5 || g==4)
                            {
                                if(!squareGood)
                                {
                                    squareGood=true;
                                }else{
                                    rectGood=2;
                                    break;
                                }
                            }
                            else if(g==3 || g==2)
                            {
                                rectGood++;
                            }
                        }
                    }
 
                    if(rectGood>=2 && squareGood)break;
                }
 
                if(rectGood>=2 && squareGood)
                {
                    out.println("YES");
                    continue;
                }
                out.println("NO");
 
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
