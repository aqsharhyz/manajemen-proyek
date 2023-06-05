import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;

public class FileProjectManager {
    public static void read() {
        // try {
        //     File myFile = new File("data.txt");
        //     Scanner myReader = new Scanner(myFile);
        //     while (myReader.hasNextLine()) {
        //         String data = myReader.nextLine();
        //         System.out.println(data);
        //     }
        //     System.out.println("Successfully read from file.");
        //     myReader.close();
        // } catch(IOException e) {
        //     System.out.println("An error occurred.");
        //     e.printStackTrace();
        // }

        try { 
            File myFile = new File("data.txt");
            if(myFile.exists()){
                Scanner myReader = new Scanner(myFile);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    System.out.println(data);
                }
                System.out.println("Successfully read from file.");
                myReader.close();
            }
        } catch(IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void write(ProjectManager pm) {
        try {
            FileWriter myFile = new FileWriter("data.txt"); //trunc file
            //myFile.write("Files in Java might be tricky, but it is fun enough!");
            //write file here
            myFile.close();
            System.out.println("Successfully wrote to file.");
        } catch(IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        write();
    }
}
