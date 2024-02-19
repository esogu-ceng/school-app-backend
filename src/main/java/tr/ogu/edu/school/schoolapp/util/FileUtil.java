package tr.ogu.edu.school.schoolapp.util;

import java.io.File;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import tr.ogu.edu.school.schoolapp.exception.DiscFullException;
import tr.ogu.edu.school.schoolapp.exception.FolderNotCreatedException;

@Component
@AllArgsConstructor
public class FileUtil {

	private static final int BYTE_COEFFICIENT = 1024;
	private static final int REQUIRED_DISC_SPACE_MB = 100;
	private static String filesRootPath = "test/"; // FIXME veritabanından alınacak

	public String getFileFullPath(MultipartFile file) {
		checkPaths();
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		StringBuilder builder = new StringBuilder();
		String fileUUID = UUID.randomUUID().toString();
		String filePath = builder.append(filesRootPath).append(fileUUID).append(".").append(extension).toString();
		return filePath;
	}

	public String getFileFullPath(String extention) {
		checkPaths();
		StringBuilder builder = new StringBuilder();
		String fileUUID = UUID.randomUUID().toString();
		String filePath = builder.append(filesRootPath).append(fileUUID).append(".").append(extention).toString();
		return filePath;
	}

	public String getFileFullPath(String filename, String extention) {
		checkPaths();
		StringBuilder builder = new StringBuilder();
		String filePath = builder.append(filesRootPath).append(filename).append(".").append(extention).toString();
		return filePath;
	}

	public boolean checkPaths() {
		File discFolder = new File(filesRootPath);
		if (!discFolder.exists() && !discFolder.mkdir()) {
			throw new FolderNotCreatedException();
		}
		long freeSpace = discFolder.getFreeSpace() / BYTE_COEFFICIENT / BYTE_COEFFICIENT;
		if (freeSpace < REQUIRED_DISC_SPACE_MB) {
			throw new DiscFullException();
		}
		return true;
	}

}
