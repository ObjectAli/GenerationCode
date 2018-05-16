import java.io.*;
import java.util.ArrayList;
import java.io.IOException;

//Класс, реализующий считывание входных данных из файла
public class Reader {

    public static ArrayList<String> run(){
        ArrayList<String> list = new ArrayList<String>();
        System.out.println("Contents of the file being read:");
        try {
            FileInputStream stream = new FileInputStream("input.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                list.add(strLine);
                System.out.print(strLine+" ");
            }
        } catch (IOException e) {
            System.out.println("Error reading file!");
        }
        System.out.println();
        return list;
    }
}