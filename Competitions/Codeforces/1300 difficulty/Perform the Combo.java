import javax.print.attribute.SupportedValuesAttribute;
import java.io.*;
import java.util.*;
 
public class Problem1 {
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
        static HashMap<String, Integer> dp = new HashMap<>();
 
        public void solve(InputReader in, PrintWriter out) {
 
 
            int T = in.nextInt();
            while(T-->0) {
                int n = in.nextInt();
                int m = in.nextInt();
 
                String combo = in.next();
 
                int[] countArr = new int[n+1];
 
                int[] arr = new int[m];
 
                for (int i = 0; i < m; i++)
                {
                    int theInt = in.nextInt();
                    arr[i] =  theInt;
                    countArr[theInt]+=1;
                }
 
                Arrays.sort(arr);
                HashMap<Character, Integer> keyPress = new HashMap<>();
 
                for(int i = 0; i < 26; i++)
                {
                    keyPress.put((char)('a'+i), 0);
                }
 
                int startingPoint = 0;
 
                int g = 0;
                for(int i = 0; i < m; i++)
                {
                    int count =countArr[arr[i]];
                    for(int j = g; j < arr[i]; j++)
                    {
                        char theChar = combo.charAt(j);
                        keyPress.put(theChar, keyPress.get(theChar)+(m-i));
                    }
                    g = arr[i];
                    i+=count-1;
                }
                for(int j = 0; j < n; j++)
                {
                    char theChar = combo.charAt(j);
                    keyPress.put(theChar, keyPress.get(theChar)+1);
                }
 
                for(int i = 0; i < 26; i++)
                {
                    out.print(keyPress.get((char)('a'+i))+" ");
                }
                out.println();
 
 
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
