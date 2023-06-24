package decemo.com.bardatastore.service

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

open class BaseRepository<T : Any, D>(protected val repository: JpaRepository<T, Long>) {
    fun save(entity: T): T {
        return repository.save(entity)
    }

    fun saveAll(mutableEntity: MutableList<T>): MutableList<T> {
        return repository.saveAll(mutableEntity)
    }

    fun findAll(): MutableList<T> {
        return repository.findAll()
    }

    fun findAllById(id: Long): Optional<T> {
        return repository.findById(id)
    }

    fun deleteById(id: Long) {
        repository.deleteById(id)
    }
}