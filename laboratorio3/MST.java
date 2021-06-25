
package laboratorio3;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Collections;
import laboratorio3.Laboratorio3;

public class MST 
{
    public MST()
    {

    }

    public void prim(float graph[][], int size, int inicio)
    {
        int edge = 0;
        boolean ST[] = new boolean[size]; //Ya visitados
        Arrays.fill(ST,false);
        ST[0] = true;
   
        System.out.println("PRIM");
        System.out.println("Edge : Weight");

        while (edge < size - 1) 
        {
            float min = Integer.MAX_VALUE;
            int x = 0;
            int y = 0;
               
            for (int i = inicio; i < size; i++) 
            {
                if (ST[i] == true) 
                {
                    for (int j = 0; j < size; j++) 
                    {
                        if (!ST[j] && graph[i][j] != (float)Integer.MAX_VALUE)     
                        {
                            if (graph[i][j] < min) 
                            {
                                min = graph[i][j];
                                x = i;
                                y = j;
                            }
                        }
                    }
                }
            }
            if(graph[x][y] != (float)Integer.MAX_VALUE){   
            System.out.println(x + " - " + y + " :  " + graph[x][y]);
            }
            ST[y] = true;
            edge++;
        }
    }

    public static void main(String[] args) 
    {
        float[][] g = new float[6][6];
        int i, j;
        MST test = new MST();
        for(i = 0; i<6 ; i++)
        {
            for(j = 0; j<6 ; j++)
            {
                g[i][j] = (float)Integer.MAX_VALUE;
            }
        }
        HashMap<Float,String> translation = new HashMap<Float,String>();
        translation.put(0f,"A");
        translation.put(1f,"B");
        translation.put(2f,"C");
        translation.put(3f,"D");
        translation.put(4f,"E");
        translation.put(5f,"F");
        g[0][1] = 1f;
        g[1][0] = 1f;
        g[0][3] = 3f;
        g[3][0] = 3f;
        g[1][2] = 3f;
        g[2][1] = 3f;
        g[1][3] = 2f;
        g[3][1] = 2f;
        g[1][4] = 6f;
        g[4][1] = 6f;
        g[1][5] = 5f;
        g[5][1] = 5f;
        g[2][4] = 4f;
        g[4][2] = 4f;
        g[2][5] = 4f;
        g[5][2] = 4f;
        g[3][4] = 3f;
        g[4][3] = 3f;
        g[4][5] = 2f;
        g[5][4] = 2f;

       // test.prim(g, 6);

    }
}