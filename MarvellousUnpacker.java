import java.io.*;
import java.util.zip.*;

public class MarvellousUnpacker {
    private String packedFile;
    private static final int SHIFT = 4; // Caesar cipher shift

    public MarvellousUnpacker(String packedFile) throws IOException {
        this.packedFile = packedFile;
        unpack();
    }

    private void unpack() throws IOException {
        FileInputStream fis = new FileInputStream(packedFile);
        ZipInputStream zis = new ZipInputStream(fis);

        ZipEntry ze;
        while ((ze = zis.getNextEntry()) != null) {
            File outFile = new File(ze.getName());

            // Create directories if needed
            File parent = outFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }

            FileOutputStream fos = new FileOutputStream(outFile);
            byte[] buffer = new byte[4096];
            int read = 0;
            while ((read = zis.read(buffer)) != -1) {
                byte[] decrypted = caesarDecrypt(buffer, read);
                fos.write(decrypted, 0, read);
            }
            fos.close();
            zis.closeEntry();
        }
        zis.close();
        fis.close();
    }

    private byte[] caesarDecrypt(byte[] data, int length) {
        byte[] decrypted = new byte[length];
        for (int i = 0; i < length; i++) {
            decrypted[i] = (byte)(data[i] - SHIFT);
        }
        return decrypted;
    }
}
