package installer.utils;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipArchiveWriter extends ZipOutputStream implements IArchiveWriter {
    public ZipArchiveWriter(OutputStream out)
    {
        super(out);
    }

    @Override
    public void ArchivingFile(File file, String fileName) throws IOException {
        if (file.isDirectory()) {
            if (fileName.endsWith("/")) {
                putNextEntry(new ZipEntry(fileName));
                closeEntry();
            } else {
                putNextEntry(new ZipEntry(fileName + "/"));
                closeEntry();
            }
            File[] children = file.listFiles();
            for (File childFile : children) {
                ArchivingFile(childFile, fileName + "/" + childFile.getName());
            }
            return;
        }
        var FileIn = new FileInputStream(file);
        var zipEntry = new ZipEntry(fileName);
        putNextEntry(zipEntry);

        byte[] bytes = new byte[1024];
        int length;
        while(0 <= (length = FileIn.read(bytes)))
            write(bytes, 0, length);
        closeEntry();
    }

    @Override
    public void ArchivingFile(String filename) throws IOException {
        var file = new File(filename);
        ArchivingFile(file, file.getName());
    }

    @Override
    public void ArchivingFiles(String[] filenames) throws IOException {
        for (var filename: filenames)
            ArchivingFile(filename);
    }
}
