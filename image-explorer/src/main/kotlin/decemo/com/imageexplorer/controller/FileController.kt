package decemo.com.imageexplorer.controller

import decemo.com.imageexplorer.payload.UploadFileResponse
import decemo.com.imageexplorer.service.FileStorageService
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.io.IOException
import java.util.*
import javax.servlet.http.HttpServletRequest

@RestController
class FileController(val fileStorageService: FileStorageService) {

    @PostMapping("/uploadFile")
    fun uploadFile(@RequestParam("file") file: MultipartFile): UploadFileResponse {
        val fileName: String = fileStorageService.storeFile(file)
        val fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/downloadFile/")
            .path(fileName)
            .toUriString()
        return UploadFileResponse(fileName, fileDownloadUri, file.contentType, file.size)
    }

    @PostMapping("/uploadMultipleFiles")
    fun uploadMultipleFiles(@RequestParam("files") files: List<MultipartFile>): List<UploadFileResponse> {
        return files.map { uploadFile(it) }
    }

    @GetMapping("/downloadFile/**")
    fun downloadFile(request: HttpServletRequest): ResponseEntity<Resource> {
        val fileName = request.requestURI
            .split(request.contextPath + "/downloadFile/")[1];
        // Load file as Resource
        val resource: Resource = fileStorageService.loadFileAsResource(fileName)

        // Try to determine file's content type
        var contentType: String? = null
        try {
            contentType = request.servletContext.getMimeType(resource.file.absolutePath)
        } catch (ex: IOException) {
//            FileController.logger.info("Could not determine file type.")
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream"
        }
        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(contentType))
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.filename + "\"")
            .body(resource)
    }

}