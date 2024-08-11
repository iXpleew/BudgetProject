package com.example.bugdetprojecy;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManagment {
    static File file = new File("user_data.txt");

    static String FileChecker(int deliberateLine) throws IOException  {
        int index = 0;

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file)) ;
        String line;
        while((line = bufferedReader.readLine()) != null){
            if (index == deliberateLine){
                int colonIndex = line.indexOf(':');
                return line.substring(colonIndex + 1).trim();
            }
            index++;
        }
        return null;
    }

    static void FileChanger(int modifyLine, String newLine){
        List<String> lines = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while((line = br.readLine()) != null){
                lines.add(line);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        if(lines.size() > modifyLine) lines.set(modifyLine, newLine);

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for(String line : lines){
                bw.write(line);
                bw.newLine();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
