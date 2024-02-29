package com.example.uploadingfiles.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

	void init();

	Path store(MultipartFile file); // store file
 
	Stream<Path> loadAll();

	Path load(String filename); // load file in

	Resource loadAsResource(String filename);

	void deleteAll();

}

