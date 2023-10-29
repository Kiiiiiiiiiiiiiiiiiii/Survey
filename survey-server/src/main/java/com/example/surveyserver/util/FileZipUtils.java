package com.example.surveyserver.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
public class FileZipUtils {
    /***
     * Zip 파일 생성
     * @param path
     * @param branchName
     */
    public void zip(String path, String branchName) {
        File file_ = new File(path);
        String fileName = "namecardimg_" + branchName + ".zip";
        existZip(path, fileName);
        File[] listFiles = file_.listFiles();

        FileOutputStream fos = null;
        ZipOutputStream zipOut = null;
        FileInputStream fis = null;

        try {
            fos = new FileOutputStream(path + "/" + fileName);
            zipOut = new ZipOutputStream(fos);

            for(File fileToZip :  listFiles) {

                fis = new FileInputStream(fileToZip);
                ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
                zipOut.putNextEntry(zipEntry);

                byte[] bytes = new byte[1024];
                int length;
                while((length = fis.read(bytes)) >= 0) {
                    zipOut.write(bytes, 0, length);
                }

                fis.close();
                zipOut.closeEntry();

            }

            zipOut.close();
            fos.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try { if(fis != null)fis.close(); } catch (IOException e1) {System.out.println(e1.getMessage());/*ignore*/}
            try { if(zipOut != null)zipOut.closeEntry();} catch (IOException e2) {System.out.println(e2.getMessage());/*ignore*/}
            try { if(zipOut != null)zipOut.close();} catch (IOException e3) {System.out.println(e3.getMessage());/*ignore*/}
            try { if(fos != null)fos.close(); } catch (IOException e4) {System.out.println(e4.getMessage());/*ignore*/}
        }
    }

    /***
     * 중복 zip 파일 제거
     * @param path
     * @param filename
     * @return
     */
    public boolean existZip(String path, String filename) {
        File zipFileName = new File(path + "/" + filename);

        if (zipFileName.exists()) {
            if(zipFileName.delete()) {
                return true;
            } else {
                return false;
            }
        }else {
            return false;
        }
    }


}
