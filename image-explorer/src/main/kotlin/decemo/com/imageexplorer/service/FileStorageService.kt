package decemo.com.imageexplorer.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import org.springframework.web.multipart.MultipartFile
import java.awt.image.BufferedImage
import java.io.*
import java.net.MalformedURLException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import javax.imageio.ImageIO


@Service
class FileStorageService(@Value("\${file.upload-dir}") private var uploadDir: String) {

    fun createDirectories(path:String = ""): Path {
        try {
            val fileStorageLocation: Path = Paths.get("$uploadDir/$path").toAbsolutePath().normalize()
            Files.createDirectories(fileStorageLocation)
            return fileStorageLocation
        } catch (ex: Exception) {
            throw Exception("Could not create the directory where the uploaded files will be stored.", ex)
        }
    }

    fun storeFile(file: MultipartFile): String {
        // Normalize file name
        val fileName = StringUtils.cleanPath(file.originalFilename!!)
        return try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw Exception("Sorry! Filename contains invalid path sequence $fileName")
            }
            val fileStorageLocation =  createDirectories()
            // Copy file to the target location (Replacing existing file with the same name)
            val targetLocation = fileStorageLocation.resolve(fileName)
            Files.copy(file.inputStream, targetLocation, StandardCopyOption.REPLACE_EXISTING)
            fileName
        } catch (ex: IOException) {
            throw Exception("Could not store file $fileName. Please try again!", ex)
        }
    }

    fun storeFile(file: BufferedImage, path:String, fileName:String): String {
        // Normalize file name
        return try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw Exception("Sorry! Filename contains invalid path sequence $fileName")
            }

            // Copy file to the target location (Replacing existing file with the same name)
            val fileStorageLocation = createDirectories(path)
//            val targetLocation = fileStorageLocation.resolve(fileName)
            val fos: File =  File(fileStorageLocation.toString(),fileName)
//            fos.writeBytes(file.toInputStream())
            ImageIO.write(file,"png", fos)
//            Files.copy(fos, targetLocation, StandardCopyOption.REPLACE_EXISTING)
            fileName
        } catch (ex: IOException) {
            throw Exception("Could not store file $fileName. Please try again!", ex)
        }
    }

    fun BufferedImage.toInputStream(): ByteArray {
        val output: ByteArrayOutputStream = object : ByteArrayOutputStream() {
            @Synchronized
            override fun toByteArray(): ByteArray {
                return this.buf
            }
        }
        ImageIO.write(this, "jpg", output)
        return output.toByteArray()
    }

    fun loadFileAsResource(fileName: String): Resource {
        return try {
            val fileStorageLocation: Path = Paths.get("$uploadDir/$fileName").toAbsolutePath().normalize()
            val resource: Resource = UrlResource(fileStorageLocation.toUri())
            if (resource.exists()) {
                resource
            } else {
                throw Exception("File not found $fileName")
            }
        } catch (ex: MalformedURLException) {
            throw Exception("File not found $fileName", ex)
        }
    }
}