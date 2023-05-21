package it.indra.SigecAPI.filerepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.util.FileCopyUtils;

/**
* @author  Vito Losito - vlosito@misait.com
* @version 1.0
* @since   2019-11-08 
*/

public class FileRepository {

	public static void saveFile(byte[] fileByte, String fileName, String path) throws IOException {
		if (fileByte.length > 0) {
			File repos = new File(path);
			repos.mkdirs();
			File dest = new File(repos, fileName);
			FileCopyUtils.copy(fileByte, dest);
		}
	}

	public static byte[] getFile(String path) throws IOException {
		File file = new File(path);
		byte[] bytesArray = new byte[(int) file.length()];
		FileInputStream fis = new FileInputStream(file);
		fis.read(bytesArray);
		fis.close();
		return bytesArray;
	}

	public static void deleteFile(String fileName, String path) {
		File f = new File(path, fileName);
		if (f.exists()) {
			f.delete();
		}
	}

}
