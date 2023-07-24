package decemo.com.imageexplorer.payload

data class UploadFileResponse(
    var fileName: String? = null,
    var fileDownloadUri: String? = null,
    var fileType: String? = null,
    var size: Long = 0
)
