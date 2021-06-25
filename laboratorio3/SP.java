

package laboratorio3;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class SP 
{
    public SP()
    {

    }

    public void dijkstra(float graph[][], int size, int source)
    {
        if(source < size && source >= 0)
        {
            float costs[] = new float[size];
            int path[] = new int[size];//ultima escala
            int k = 0;
            boolean visited[] = new boolean[size];
            int currentVertex = source;
            int nVisited = 0;
            int minDestVertex = -1;
            float minDistance = (float)Integer.MAX_VALUE;
            Arrays.fill(costs, (float)Integer.MAX_VALUE);
            Arrays.fill(visited, false);
            costs[currentVertex] = 0;
            

            while(nVisited < size)
            {
                for(int i = 0; i < size; i++)
                {                            //hay una ruta
                    if(i != currentVertex && graph[currentVertex][i] != (float)Integer.MAX_VALUE)
                    {                                            //costo con escala                           
                        costs[i] = Float.min(costs[i],(costs[currentVertex] + graph[currentVertex][i]));
                    }

                }
                nVisited++;
                visited[currentVertex] = true;

                for(int i = 0; i < size; i++)
                {
                    if(!visited[i] && costs[i] < minDistance)
                    {
                        minDistance = costs[i];
                        minDestVertex = i;
                    }
                }

                path[k] = currentVertex;
                k++;
                currentVertex = minDestVertex;
                minDistance = Integer.MAX_VALUE;
            }
        
            System.out.println("DIJKSTRA");
            for(int i = 0; i < size; i++)
            {
                if(costs[i] != Integer.MAX_VALUE){
                System.out.println("Route from " + source + " to " + i + " costs:" + costs[i]);
                }
            }
            System.out.print("Vertex path: ");
            for(int i = 0; i < size; i++)
            {
                if(i>0 && path[i-1] == path[i] ){
                    }else{
                 System.out.print(path[i] + " ");
                }
            }
            System.out.print("\n");
        }
        else
        {
            System.out.println("Please choose a correct source vertex.");
        }
    }

    public static void main(String[] args) 
    {
        float[][] g = new float[6][6];
        int i, j;
        SP test = new SP();
        for(i = 0; i<6 ; i++)
        {
            for(j = 0; j<6 ; j++)
            {
                g[i][j] = Integer.MAX_VALUE;
            }
        }
        
        g[0][1] = 7;
        g[0][2] = 9;
        g[0][5] = 14;
        g[1][0] = 7;
        g[1][2] = 10;
        g[1][3] = 15;
        g[2][0] = 9;
        g[2][1] = 10;
        g[2][3] = 11;
        g[2][5] = 2;
        g[3][1] = 15;
        g[3][2] = 11;
        g[3][4] = 6;
        g[4][3] = 6;
        g[4][5] = 9;
        g[5][0] = 14;
        g[5][2] = 2;
        g[5][4] = 9;

        test.dijkstra(g,6,0);

    }
}