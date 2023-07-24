package decemo.com.imageexplorer.controller

import decemo.com.imageexplorer.service.FileStorageService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriUtils
import java.awt.image.BufferedImage
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import javax.imageio.ImageIO


@RestController
class PullController(val fileStorageService: FileStorageService) {

    val ur = "https://bekmen.rs/api/slike/"

    @GetMapping("/pull")
    fun pullImages(@RequestBody urls: List<BarDto>) {
        urls.forEach {
            try {
                val url: URL = URL(ur + it.mainPictureUrl.replace(" ", "%20"))
                val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
                connection.setRequestProperty(
                    "User-Agent",
                    "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:37.0) Gecko/20100101 Firefox/37.0"
                )
                val propertImage: BufferedImage = ImageIO.read(connection.inputStream)
                fileStorageService.storeFile(propertImage, it.mainPictureUrl.substringBeforeLast('/'), it.mainPictureUrl.substringAfterLast('/'))
            } catch (e: IOException) {
                println(e.message)
            }
        }
    }

}