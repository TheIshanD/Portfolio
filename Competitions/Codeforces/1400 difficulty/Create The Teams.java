import javax.management.Query;
import javax.print.attribute.SupportedValuesAttribute;
import java.io.*;
import java.lang.reflect.Array;
import java.security.interfaces.DSAKeyPairGenerator;
import java.util.*;
import java.util.function.Predicate;
 
 
public class Solution {
 
    static class TaskB {
        int p;
 
        public int recurse(LinkedList<Integer>[] mat, int picked, int stored) {
            if (picked == p) return stored;
 
            int val = 0;
            for (int i = 0; i < mat.length; i++) {
                if (mat[i].size() == 0) continue;
                int beauty = mat[i].remove(0);
                val = Math.max(val, recurse(mat, picked + 1, stored + beauty));
                mat[i].addFirst(beauty);
            }
            return val;
        }
 
        public void solve(InputReader in, PrintWriter out) {
            int T = in.nextInt();
            for(int tt = 0; tt < T; tt++){
                int n = in.nextInt();
                int x = in.nextInt();
 
                int[] arr = in.nextIntArray(n);
 
                sort(arr);
 
                int ans = 0;
                int cnt = 0;
                int min;
                for(int i = arr.length-1; i>=0; i--){
                    cnt++;
                    min = arr[i];
                    if(cnt*min>=x){
                        cnt = 0;
                        ans++;
                    }
                }
                out.println(ans);
 
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
