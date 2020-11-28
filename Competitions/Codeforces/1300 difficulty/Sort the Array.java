import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
 
public class Main {
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
 
        public int findRestaurants(int[][] mat, int cLim, int[] cats, int currentNode, int catsSeen, int parent)
        {
            if(cats[currentNode-1] == 1)
            {
                if(catsSeen+1 >  cLim)
                {
                    return 0;
                }
                catsSeen++;
            }else{
                catsSeen=0;
            }
 
            int sum = 0;
            boolean changed = false;
            for(int i = 1; i <= mat.length; i++)
            {
                if(mat[currentNode-1][i-1]==1)
                {
                    if(i != parent)
                    {
                        changed= true;
                        sum+=findRestaurants(mat, cLim, cats, i,  catsSeen, currentNode);
                    }
                }
            }
            if(!changed) return 1;
            else return sum;
 
 
        }
        public boolean checkIfContains(String[] sA, String s)
        {
            for(String str: sA)
            {
                if(s.equals(str))
                {
                    return true;
                }
            }
            return false;
        }
        public void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();
 
            int[] arr = in.nextIntArray(n);
 
            if(n==1)
            {
                out.println("yes");
                out.print(1+" "+1);
            }else{
                boolean startFlipping = false;
                boolean stopFlipping = false;
 
 
                int startIndex = -1;
                int endIndex = -1;
                for(int i = 1; i < arr.length; i++)
                {
                    if(arr[i-1]>arr[i] && !stopFlipping && !startFlipping)
                    {
                        startFlipping = true;
                        startIndex = i-1;
                    }
                    else if(arr[i-1]<arr[i] && startFlipping && !stopFlipping){
                        stopFlipping = true;
                        endIndex = i-1;
                    }
                }
 
                if(startIndex==-1){
                    out.println("yes");
                    out.print(1+" "+1);
                }else{
                    if(endIndex<0)endIndex=arr.length-1;
                    int[] newArr = new int[n];
                    for(int i = 0; i < startIndex; i++)
                    {
                        newArr[i] = arr[i];
                    }
                    for(int i = startIndex; i <= endIndex; i++)
                    {
                        newArr[i] = arr[endIndex-i+startIndex];
                    }
                    if(n>endIndex+1)
                    {
                        for(int i = endIndex+1; i < n; i++)
                        {
                            newArr[i] = arr[i];
                        }
                    }
                    Arrays.sort(arr);
                    boolean toBreak = false;
 
                    for(int i = 0; i < n; i++)
                    {
                        if(arr[i]!=newArr[i]){
                            toBreak=true;
                            break;
                        }
                    }
                    if(toBreak)
                    {
                        out.print("no");
                    }else{
                        out.println("yes");
                        out.println((startIndex+1)+" "+(endIndex+1));
                    }
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
 
        public int[] nextIntArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = nextInt();
            return a;
        }
 
    }
}
