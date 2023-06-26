package decemo.com.bardatastore.entity

import com.vladmihalcea.hibernate.type.json.JsonBinaryType
import org.hibernate.annotations.Type
import org.hibernate.annotations.TypeDef
import javax.persistence.*

@Entity
@TypeDef(name = "jsonb", typeClass = JsonBinaryType::class)
class Bar(
    @Id
    @GeneratedValue
    var id: Long = 0,
    var name: String,
    var address: String,
    @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "services_id")
    var services: MutableList<Service>? = mutableListOf(),
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    var workTime: List<String>,
    var latitude: Double,
    var longitude: Double,
    var phoneNumber: String,
    var mainPictureUrl: String,
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    var galleryPictureUrls: List<String>,
    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "bar_type_id")
    var barType: BarType,
    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var events: MutableList<Event> = mutableListOf()
)