package decemo.com.bardatastore.entity

import javax.persistence.*

@Entity
class Bar(
    @Id
    @GeneratedValue
    var id: Long = 0,
    var name: String,
    var address: String,
    @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "services_id")
    var services: MutableList<Service>? = mutableListOf(),
    @Column(columnDefinition = "json")
    var workTime: String,
    var latitude: String,
    var longitude: String,
    var phoneNumber: String,
    var mainPictureUrl: String,
    @Column(columnDefinition = "json")
    var galleryPictureUrls: String,
    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "bar_type_id")
    var barType: BarType,
    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var events:MutableList<Event> = mutableListOf()
)