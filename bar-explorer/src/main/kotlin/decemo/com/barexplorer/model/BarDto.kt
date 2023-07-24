package decemo.com.barexplorer.model

data class BarDto(
    var id: Long,
    var name: String,
    var address: String,
    var services: MutableList<ServiceDto> = mutableListOf(),
    var workTime: List<String>,
    var latitude: Double,
    var longitude: Double,
    var phoneNumber: String,
    var mainPictureUrl: String,
    var galleryPictureUrls: List<String>,
    var barType: BarTypeDto,
)