import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
public class test {
    public static void main(String[] args) {
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


            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
