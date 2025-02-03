package hub.policy.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class FileStorageService {
   
	private static final String UPLOAD_DIR="/uploads/";
	
	public String saveFile(MultipartFile file)throws IOException{
		String fileName=UUID.randomUUID()+"_"+file.getOriginalFilename();
		String filePath=UPLOAD_DIR+fileName;
		Files.write(Paths.get(filePath), file.getBytes());
		return filePath;
		
	}
}
