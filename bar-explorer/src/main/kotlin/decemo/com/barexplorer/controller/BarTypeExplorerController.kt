package decemo.com.barexplorer.controller

import decemo.com.barexplorer.facade.BarTypeFacade
import decemo.com.barexplorer.model.BarTypeDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class BarTypeExplorerController {

    @Autowired
    private lateinit var facade: BarTypeFacade

    @GetMapping("/bar/types")
    fun getAllBarTypes(): MutableList<BarTypeDto>{
        return facade.getAllBarTypes()
    }
}