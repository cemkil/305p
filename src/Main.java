
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Scanner; // Import the Scanner class to read text files
    class Main
    {
        static boolean isValid(int row, int col, int maxRow, int maxCol)
        {

            return (row >= 0) && (row < maxRow) &&
                    (col >= 0) && (col < maxCol);
        }

        static int rowNum[] = {-1, 0, 0, 1};
        static int colNum[] = {0, -1, 1, 0};

        static void backtrace(Point src, QueueNode dest){
            List<Point> q = new LinkedList<>();
            QueueNode curr = dest;
            while (curr.pt.x != src.x || curr.pt.y != src.y){
                q.add(curr.pt);
                curr = curr.prev;
            }
            Collections.reverse(q);

            System.out.println("Route for the testination");
            System.out.print(" { (START_POINT)(" + src.x + "," + src.y +") -> ");
            for (int i = 0; i< q.size(); i++){
                Point elt = q.get(i);
                if (i == q.size() -1){
                    System.out.print(" (" + elt.x + "," + elt.y + ")(END_POINT) }  ====> ");
                }else {
                    System.out.print(" (" + elt.x + "," + elt.y + ") -> ");
                }
            }

        }

        static int BFS(int mat[][], Point src,
                       Point dest)
        {

            if (mat[src.x][src.y] != 1 ||
                    mat[dest.x][dest.y] != 1)
                return -1;

            boolean [][]visited = new boolean[mat.length][mat[0].length];


            visited[src.x][src.y] = true;


            Queue<QueueNode> q = new LinkedList<>();


            QueueNode s = new QueueNode(src, 0, null);
            q.add(s);


            while (!q.isEmpty())
            {
                QueueNode curr = q.peek();
                Point pt = curr.pt;


                if (pt.x == dest.x && pt.y == dest.y){
                    backtrace(src, curr);
                    return curr.dist;
                }

                q.remove();

                for (int i = 0; i < 4; i++)
                {
                    int row = pt.x + rowNum[i];
                    int col = pt.y + colNum[i];

                    if (isValid(row, col, mat.length, mat[0].length) &&
                            mat[row][col] == 1 &&
                            !visited[row][col])
                    {
                        visited[row][col] = true;
                        QueueNode Adjcell = new QueueNode
                                (new Point(row, col),
                                        curr.dist + 1, curr);
                        q.add(Adjcell);
                    }
                }
            }
            // Return -1 if destination cannot be reached
            return -1;
        }

        // Driver Code
        public static void main(String[] args)
        {
            try {
                File myObj = new File(args[0]);
                Scanner myReader = new Scanner(myObj);
                String data = myReader.nextLine();
                int tesAmount = Integer.valueOf(data);
                System.out.println("the number of test is " + tesAmount);
                for (int i = 0; i< tesAmount; i ++){
                    String sizes = myReader.nextLine();
                    int height = Integer.valueOf(sizes.split(" ")[0]);
                    int width = Integer.valueOf(sizes.split(" ")[1]);
                    Point source = null;
                    Point destination = null;
                    int[][] dungeon = new int[height][width];
                    for (int k = 0; k< height; k++){
                        String row = myReader.nextLine();
                        for (int l = 0; l< width; l ++) {
                            Character cell = row.charAt(l);
                            if (cell == 'X'){
                                dungeon[k][l] = 0;
                            }else{
                                dungeon[k][l] = 1;
                                if (cell == 'Y'){
                                    source = new Point(k,l);
                                }else if (cell == 'E'){
                                    destination = new Point(k,l);
                                }
                            }
                        }
                    }
                    int dist = BFS(dungeon, source, destination);

                    if (dist != -1)
                        System.out.println("Shortest Path is " + dist);
                    else
                        System.out.println("Shortest Path doesn't exist");

                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }


        }
    }


