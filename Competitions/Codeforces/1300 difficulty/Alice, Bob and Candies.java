import javax.print.attribute.SupportedValuesAttribute;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Predicate;
 
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
 
        public void solve(InputReader in, PrintWriter out) {
           int T = in.nextInt();
           while(T-->0)
           {
               int n = in.nextInt();
               ArrayList<Integer> arr = new ArrayList<>();
               for(int i = 0; i < n; i++)
               {
                   arr.add(in.nextInt());
               }
 
               int candyEaten =arr.remove(0);
 
               int bobSum = 0;
 
               boolean toBreak = false;
 
               int totalBob = 0, totalAlice = candyEaten;
               int moves = 1;
 
               boolean firstTime = true;
               while(true)
               {
                   bobSum = 0;
                   while(bobSum <= candyEaten)
                   {
                       if(arr.size() == 0)
                       {
                           toBreak=true;
                           break;
                       }
                       bobSum+=arr.remove(arr.size()-1);
                       if(firstTime){
                           moves++;
                           firstTime = false;
                       }
                   }
                   totalBob+=bobSum;
                   if(toBreak)break;
 
 
                   firstTime = true;
                   candyEaten = bobSum;
                   int aliceSum = 0;
                   while(aliceSum <= candyEaten)
                   {
                       if(arr.size() == 0)
                       {
                           toBreak=true;
                           break;
                       }
                       aliceSum+=arr.remove(0);
                       if(firstTime){
                           moves++;
                           firstTime = false;
                       }
                   }
                   totalAlice+=aliceSum;
                   if(toBreak)break;
 
                   candyEaten = aliceSum;
                   firstTime = true;
               }
               out.println(moves+" "+totalAlice+" "+totalBob);
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
