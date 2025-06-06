import java.io.*;
import java.util.zip.*;

public class MarvellousPacker {
    private String sourceDir;
    private String destFile;
    private static final int SHIFT = 4; // Caesar cipher shift

    public MarvellousPacker(String sourceDir, String destFile) throws IOException {
        this.sourceDir = sourceDir;
        this.destFile = destFile;
        pack();
    }

    private void pack() throws IOException {
        FileOutputStream fos = new FileOutputStream(destFile);
        ZipOutputStream zos = new ZipOutputStream(fos);

        File dir = new File(sourceDir);
        if (!dir.isDirectory()) {
            throw new IOException("Source is not a directory");
        }

        packDirectory(dir, dir.getName(), zos);

        zos.close();
        fos.close();
    }

    private void packDirectory(File dir, String basePath, ZipOutputStream zos) throws IOException {
        File[] files = dir.listFiles();
        if (files == null) return;

        for (File file : files) {
            if (file.isDirectory()) {
                packDirectory(file, basePath + "/" + file.getName(), zos);
            } else {
                packFile(file, basePath, zos);
            }
        }
    }

    private void packFile(File file, String basePath, ZipOutputStream zos) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        ZipEntry ze = new ZipEntry(basePath + "/" + file.getName());
        zos.putNextEntry(ze);

        // Read and encrypt data using Caesar cipher before writing
        byte[] buffer = new byte[4096];
        int read = 0;
        while ((read = fis.read(buffer)) != -1) {
            byte[] encrypted = caesarEncrypt(buffer, read);
            zos.write(encrypted, 0, read);
        }
        zos.closeEntry();
        fis.close();
    }

    private byte[] caesarEncrypt(byte[] data, int length) {
        byte[] encrypted = new byte[length];
        for (int i = 0; i < length; i++) {
            encrypted[i] = (byte)(data[i] + SHIFT);
        }
        return encrypted;
    }
}
