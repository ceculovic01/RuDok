package dsw.gerudok.app.serializer;

import dsw.gerudok.app.core.ISerializer;
import dsw.gerudok.app.repository.Project;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class Serializer implements ISerializer {

    public Serializer(){

    }

    @Override
    public void saveProject(File file, Project project) {
        ObjectOutputStream os;
        try {
            os = new ObjectOutputStream(new FileOutputStream(file));
            os.writeObject(project);

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public Project openProject(File file) {
        Project p = null;
        try {
          ObjectInputStream os = new ObjectInputStream(new FileInputStream(file));
          p = (Project) os.readObject();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return p;

    }

    @Override
    public List<Project> openWorkspace(File file) {

            List<Project> projectList = new ArrayList<>();
            try {
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    ObjectInputStream os = new ObjectInputStream(new FileInputStream(line));
                    Project p = (Project) os.readObject();
                    projectList.add(p);
                }

            fileReader.close();
            } catch (FileNotFoundException e) {
                return  projectList;
            } catch (IOException e) {
                return  projectList;
            } catch (ClassNotFoundException e) {
                return  projectList;
            }
            return  projectList;

    }

    @Override
    public void saveFile(File file, String content) {
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.write(content);
            bufferedWriter.close();

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @Override
    public void copyFile(Path path1, Path path2) {
        try {
            Files.copy(path1, path2, StandardCopyOption.REPLACE_EXISTING);
        }catch (NoSuchFileException e){
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getTextFromFile(String filePath) {
            byte[] encoded = new byte[0];
            String s = "";
            try {
                encoded = Files.readAllBytes(Paths.get(filePath));
                s = new String(encoded, StandardCharsets.UTF_8);
            } catch (FileNotFoundException e){
                s = "";
            }
            catch (NoSuchFileException e){
                s = "";

            } catch(IOException e) {
                s= "";
            }
            return  s;

    }

    @Override
    public void removeAllFilesFromDirectory(File directory) {
        File[] files = directory.listFiles();
        if(files == null)
            return;

        for (File file : files){
            if (!file.delete()){
                System.out.println("Failed to delete "+file);
            }
        }
    }
}
