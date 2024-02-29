package com.example.uploadingfiles;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.properties.Product;
import com.example.properties.readwriteexcel;
import com.example.uploadingfiles.storage.StorageException;
import com.example.uploadingfiles.storage.StorageFileNotFoundException;
import com.example.uploadingfiles.storage.StorageService;

@Controller
public class FileUploadController {

	private final StorageService storageService;

	@Autowired
	public FileUploadController(StorageService storageService) {
		this.storageService = storageService;
	}

	@GetMapping("/")
	public String listUploadedFiles(Model model) throws IOException {

		model.addAttribute("files", storageService.loadAll().map(
				path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
						"serveFile", path.getFileName().toString()).build().toUri().toString())
				.collect(Collectors.toList()));

		return "uploadForm";
	}

	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}

	@PostMapping("/")
	public String handleFileUpload(@RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes) {
		try {

		Path pathFile = storageService.store(file);
		readwriteexcel obj = new readwriteexcel();
		List<Product> products = obj.ReadExcel(pathFile.toString()); // get pathFile name
		 obj.updateExcelSheet(products, pathFile.toString()); // modify excel sheet
		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded " + file.getOriginalFilename() + "!"); // upload file

		return "redirect:/";
		}
		/* ------------------------------------------------
		 * PURPOSE: to deal with errors
		 * ------------------------------------------------
		 */
		catch (IllegalStateException e) { // redirect to error page with variable type mismatch error
			redirectAttributes.addFlashAttribute("errorTitle", "Variable Type Mismatch Error");
	        redirectAttributes.addFlashAttribute("errorMessage", "An error occurred while processing the request.");
	        redirectAttributes.addFlashAttribute("errorDetails", e.getMessage());

	        return "redirect:/error";
		}
		catch (StorageException e) { // redirect to error page with storage exception error
			redirectAttributes.addFlashAttribute("errorTitle", "Storage Exception Error");
			redirectAttributes.addFlashAttribute("errorMessage", "An error occurred while storing the file");
			redirectAttributes.addFlashAttribute("errorDetails", e.getMessage());
			
			return "redirect:/error"; // You can redirect to a specific error page if necessary
		}
	}
	@ExceptionHandler(StorageFileNotFoundException.class) // exception handler for file not found
	public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}
	
}
