import java.util.*;

public class SilverProblemTwo {


    public static boolean checkIfBoundThree(Cow cow, int xMax, int xMin, int yMax, int yMin)
    {
        int x = cow.xPos;
        int y = cow.yPos;

        if(x != xMax && x != xMin && y != yMax && y != yMin)
        {
            return false;
        }
        return true;
    }

    public static boolean checkIfBoth(Cow cow, int xMax, int xMin, int yMax, int yMin)
    {
        int x = cow.xPos;
        int y = cow.yPos;

        int counter = 0;

        if(x == xMax) counter++;
        if(x == xMin) counter++;
        if(y == yMax) counter++;
        if(y == yMin) counter++;

        if(counter >= 2)
        {
            return true;
        }
        return false;
    }

    public static void SilverProblemTwo(String[] args)
    {
        Scanner in = new Scanner(System.in);

        int n = Integer.parseInt(in.nextLine());

        Cow[] cowList = new Cow[n];

        for(int i = 0; i < n; i++)
        {
            int x = in.nextInt();
            int y = in.nextInt();
            in.nextLine();

            Cow tempCow = new Cow(x, y);

            cowList[i] = tempCow;
        }


        int counter = 0;

        for(int i = 0; i < cowList.length; i++)
        {
            for(int i2 = i + 1; i2 < cowList.length; i2++)
            {
                for(int i3 = i2 + 1; i3 < cowList.length; i3++)
                {
                    for(int i4 = i3 + 1; i4 < cowList.length; i4++)
                    {
                        Cow[] list = new Cow[4];

                        list[0] = cowList[i];
                        list[1] = cowList[i2];
                        list[2] = cowList[i3];
                        list[3] = cowList[i4];

                        int minX = Math.min(list[0].xPos, Math.min(list[1].xPos, Math.min(list[2].xPos, list[3].xPos)));
                        int maxX = Math.max(list[0].xPos, Math.max(list[1].xPos, Math.max(list[2].xPos, list[3].xPos)));

                        int minY = Math.min(list[0].yPos, Math.min(list[1].yPos, Math.min(list[2].yPos, list[3].yPos)));
                        int maxY = Math.max(list[0].yPos, Math.max(list[1].yPos, Math.max(list[2].yPos, list[3].yPos)));

                        if(checkIfBoth(list[0], maxX, minX, maxY, minY))
                        {
                            continue;
                        }

                        if(checkIfBoth(list[1], maxX, minX, maxY, minY))
                        {
                            continue;
                        }

                        if(checkIfBoth(list[2], maxX, minX, maxY, minY))
                        {
                            continue;
                        }

                        if(checkIfBoth(list[3], maxX, minX, maxY, minY))
                        {
                            continue;
                        }

                        counter++;
                    }
                }
            }
        }


        for(int i = 0; i < cowList.length; i++)
        {
            for(int i2 = i + 1; i2 < cowList.length; i2++)
            {
                for(int i3 = i2 + 1; i3 < cowList.length; i3++)
                {
                    Cow[] list = new Cow[3];

                    list[0] = cowList[i];
                    list[1] = cowList[i2];
                    list[2] = cowList[i3];

                    int minX = Math.min(list[0].xPos, Math.min(list[1].xPos, list[2].xPos));
                    int maxX = Math.max(list[0].xPos, Math.max(list[1].xPos, list[2].xPos));

                    int minY = Math.min(list[0].yPos, Math.min(list[1].yPos, list[2].yPos));
                    int maxY = Math.max(list[0].yPos, Math.max(list[1].yPos, list[2].yPos));

                    if(!checkIfBoundThree(list[0], maxX, minX, maxY, minY)) continue;
                    if(!checkIfBoundThree(list[1], maxX, minX, maxY, minY)) continue;
                    if(!checkIfBoundThree(list[2], maxX, minX, maxY, minY)) continue;

                    counter++;
                }
            }
        }

        counter += ((n)*(n - 1)) / 2;
        counter += n;

        System.out.println(counter + 1);
    }

    public static class Cow
    {
        int xPos;
        int yPos;

        Cow(int x, int y)
        {
            xPos = x;
            yPos = y;
        }
    }
}
