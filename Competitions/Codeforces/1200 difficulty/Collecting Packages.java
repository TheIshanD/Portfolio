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
 
        public void solve(InputReader in, PrintWriter out) {
            int T = in.nextInt();
            while(T-->0)
            {
                int n = in.nextInt();
 
                HashMap<Integer, Integer> maxHeight = new HashMap<>();
                ArrayList<Integer> xVals = new ArrayList<>();
 
 
                int[][] points = new int[n][2];
 
                for(int i = 0; i < n; i++)
                {
                    int x = in.nextInt();
                    int y = in.nextInt();
 
                    points[i][0] = x;
                    points[i][1] = y;
 
                    if(!(maxHeight.containsKey(x)))
                    {
                        xVals.add(x);
                        maxHeight.put(x, y);
 
                    }else{
                        maxHeight.put(x, Math.max(maxHeight.get(x), y));
                    }
 
                }
 
                boolean good = true;
                for(int i = 0; i < n; i++)
                {
                    for(int g = 0; g < n; g++)
                    {
                        if(i==g)continue;
                        if(points[i][0]<points[g][0] && points[i][1]>points[g][1])
                        {
                            good = false;
                            break;
                        }
                    }
                    if(!good)break;
                }
                if(good)
                {
                    Collections.sort(xVals);
                    int len = xVals.size();
                    int currentX = 0;
                    int currentY = 0;
                    String direction = "";
 
                    boolean ok = false;
 
                    for(int i = 0; i < len; i++)
                    {
                        int theInt =xVals.get(i);
                        while(theInt-currentX != 0)
                        {
                            if(theInt < currentX)
                            {
                                ok = true;
                                break;
                            }
                            direction+="R";
                            currentX++;
                        }
                        while(maxHeight.get(theInt) - currentY!= 0)
                        {
                            if(maxHeight.get(theInt) < currentY)
                            {
                                ok = true;
                                break;
                            }
                            direction+="U";
                            currentY++;
                        }
                        if(ok)break;
                    }
                    if(ok)
                    {
                        out.println("NO");
                    }
                    else
                    {
                        out.println("YES");
                        out.println(direction);
                    }
                }else{
                    out.println("NO");
                }
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
