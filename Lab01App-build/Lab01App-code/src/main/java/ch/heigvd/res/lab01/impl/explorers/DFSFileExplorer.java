package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.impl.transformers.FileTransformer;
import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits all
 * files in the directory and then moves into the subdirectories.
 *
 * @author Olivier Liechti
 */
public class DFSFileExplorer implements IFileExplorer {

  @Override
  public void explore(File rootDirectory, IFileVisitor vistor) {
    // get list of all files and directories present in root
    File[] listOfFilesAndDirectory = rootDirectory.listFiles();
    vistor.visit(rootDirectory);

    // listFiles() returns non-null array if root denotes a directory
    if (listOfFilesAndDirectory != null)
    {
      for (File file : listOfFilesAndDirectory)
      {
        // if file denotes a directory, recurse for it
        if (file.isDirectory()) {

          explore(file, vistor);
        }
        // if file denotes a file
        else {
          vistor.visit(file);
        }
      }
    }
  }
}