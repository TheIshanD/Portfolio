import java.util.*;

public class Main {


    public static boolean isBoundary(Cow cow, int xMax, int xMin, int yMax, int yMin)
    {
        int x = cow.X;
        int y = cow.Y;

        if(x != xMax && x != xMin && y != yMax && y != yMin)
        {
            return false;
        }
        return true;
    }

    public static void main(String[] args)
    {
        Scanner fin = new Scanner(System.in);

        int numCows = Integer.parseInt(fin.nextLine());

        Cow[] cowArr = new Cow[numCows];

        for(int i = 0; i < numCows; i++)
        {
            int x = fin.nextInt();
            int y = fin.nextInt();
            fin.nextLine();

            Cow tempCow = new Cow(x, y);

            cowArr[i] = tempCow;
        }

        int ans = 0;

        for(int i = 0; i < numCows; i++)
        {
            for(int i2 = i + 1; i2 < numCows; i2++)
            {
                for(int i3 = i2 + 1; i3 < numCows; i3++)
                {
                    for(int i4 = i3 + 1; i4 < numCows; i4++)
                    {
                        int xMin = Math.min(cowArr[i].X, Math.min(cowArr[i2].X, Math.min(cowArr[i3].X, cowArr[i4].X)));
                        int xMax = Math.max(cowArr[i].X, Math.max(cowArr[i2].X, Math.max(cowArr[i3].X, cowArr[i4].X)));

                        int yMin = Math.min(cowArr[i].Y, Math.min(cowArr[i2].Y, Math.min(cowArr[i3].Y, cowArr[i4].Y)));
                        int yMax = Math.max(cowArr[i].Y, Math.max(cowArr[i2].Y, Math.max(cowArr[i3].Y, cowArr[i4].Y)));

                        if (!isBoundary(cowArr[i], xMax, xMin, yMax, yMin)) continue;
                        if (!isBoundary(cowArr[i2], xMax, xMin, yMax, yMin)) continue;
                        if (!isBoundary(cowArr[i3], xMax, xMin, yMax, yMin)) continue;
                        if (!isBoundary(cowArr[i4], xMax, xMin, yMax, yMin)) continue;

                        ans++;
                    }
                }
            }
        }


        for(int i = 0; i < numCows; i++)
        {
            for(int i2 = i + 1; i2 < numCows; i2++)
            {
                for(int i3 = i2 + 1; i3 < numCows; i3++)
                {
                    int xMin = Math.min(cowArr[i].X, Math.min(cowArr[i2].X, cowArr[i3].X));
                    int xMax = Math.max(cowArr[i].X, Math.max(cowArr[i2].X, cowArr[i3].X));

                    int yMin = Math.min(cowArr[i].Y, Math.min(cowArr[i2].Y, cowArr[i3].Y));
                    int yMax = Math.max(cowArr[i].Y, Math.max(cowArr[i2].Y, cowArr[i3].Y));

                    if (!isBoundary(cowArr[i], xMax, xMin, yMax, yMin)) continue;
                    if (!isBoundary(cowArr[i2], xMax, xMin, yMax, yMin)) continue;
                    if (!isBoundary(cowArr[i3], xMax, xMin, yMax, yMin)) continue;

                    ans++;
                }
            }
        }

        ans += ((numCows * (numCows - 1)) / 2);
        ans += numCows;

        System.out.println(ans + 1);
    }

    public static class Cow
    {
        int X;
        int Y;

        Cow(int x, int y)
        {
            this.X = x;
            this.Y = y;
        }
    }
}
