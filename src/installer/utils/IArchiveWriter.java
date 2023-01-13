package installer.utils;

import java.io.File;
import java.io.IOException;

public interface IArchiveWriter {
    public void ArchivingFile(File file, String filename) throws IOException;
    public void ArchivingFile(String filename) throws IOException;
    public void ArchivingFiles(String[] filenames) throws IOException;
}
