package decemo.com.barexplorer.model

data class BarDto(
    var id: Long,
    var name: String,
    var address: String,
    var services: MutableList<ServiceDto> = mutableListOf(),
    var workTime: String,
    var latitude: String,
    var longitude: String,
    var phoneNumber: String,
    var mainPictureUrl: String,
    var galleryPictureUrls: String,
    var type: BarTypeDto
)