package dsw.gerudok.app.core;

import dsw.gerudok.app.repository.Project;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

public interface ISerializer {
    void saveProject(File file, Project project);
    Project openProject(File file);
    List<Project> openWorkspace(File file);
    void saveFile(File file, String content);
    void copyFile(Path path1, Path path2);
    String getTextFromFile(String filePath);
    void removeAllFilesFromDirectory(File directory);
}
