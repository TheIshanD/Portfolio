import javax.print.attribute.SupportedValuesAttribute;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Predicate;
 
 
public class Main{
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
 
 
        public static int binarySearch(int arr[], int low, int high, int key)
        {
            int mid = (low + high)/2;
            while( low <= high )
            {
                if ( arr[mid] < key )
                {
                    if(mid+1 != arr.length && arr[mid+1]>key)
                    {
                        return mid;
                    }
                    low = mid + 1;
                }
                else
                {
                    high = mid - 1;
                }
                mid = (low + high)/2;
            }
            if( low > high )
            {
                return -1;
            }
            return -1;
        }
 
        class Graph
        {
            int V;
            LinkedList<Integer>[] adj;
 
            Graph(int V)
            {
                this.V = V;
                adj = new LinkedList[V];
                for(int i = 0; i < V; i++)
                {
                    adj[i] = new LinkedList<>();
                }
            }
            void addEdge(int v, int u)
            {
                adj[v].add(u);
                //adj[u].add(v);  //USE IN UNDIRECTED GRAPHS
            }
 
            int findMaxDepth(int v, boolean[] visited)
            {
                visited[v] = true;
 
                int max = 0;
                for(int i: adj[v])
                {
                    max = Math.max(max, findMaxDepth(i, visited) + 1);
                }
 
                return max;
            }
 
        }
 
        public void solve(InputReader in, PrintWriter out) {
            int T = in.nextInt();
 
            Graph g = new Graph(201);
 
            HashMap<String, Integer> convert = new HashMap<>();
 
            int counter = 0;
            for(int tt = 0; tt < T; tt++)
            {
                String name2 = in.next().toLowerCase();
                in.next();
                String name1 = in.next().toLowerCase();
 
                if(!convert.containsKey(name1))
                {
                    convert.put(name1, counter);
                    counter++;
                }
                if(!convert.containsKey(name2))
                {
                    convert.put(name2, counter);
                    counter++;
                }
 
                g.addEdge(convert.get(name1), convert.get(name2));
            }
 
            out.println(g.findMaxDepth(convert.get("polycarp"), new boolean[convert.size()])+1);
 
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
