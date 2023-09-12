package App.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {

    public boolean saveToFile(String fileName, String userInfo) throws IOException {
        boolean isSave = false;
        try (FileWriter writer = new FileWriter("App//model//db//" + fileName + ".txt", true)) {
            writer.write(userInfo);
            writer.append("\n");
            writer.flush();
            isSave = true;
        } catch (IOException e) {
            throw new IOException("Ошибка при попытке записи в файл");
        }
        return isSave;
    }

    public String loadFromFile(String surname) throws FileNotFoundException, IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(
                new FileReader("App//model//db//" + surname + ".txt"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append("\n");
            }

        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Информация по указанному сотруднику " + surname + " отсутсвует");
        } catch (IOException e) {
            throw new IOException("Информация по указанному сотруднику " + surname + " отсутсвует");
        }
        return stringBuilder.toString();
    }
}
