package com.springboot.sbecom.controller

import com.springboot.sbecom.model.Category
import com.springboot.sbecom.service.CategoryService
import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
class CategoryController @Autowired constructor(val categoryService: CategoryService) {

    companion object {
        private val log = LoggerFactory.getLogger(CategoryController::class.java)
    }

    @GetMapping("api/public/category")
    fun getAllCategories(): ResponseEntity<List<Category>> {
        val categories = categoryService.getCategories()
        return ResponseEntity(categories, HttpStatus.OK)
    }

    @PostMapping("api/public/category")
    fun createCategory(@Valid @RequestBody category: Category): ResponseEntity<String> {
        categoryService.createCategory(category = category)
        return ResponseEntity("Category added successfully!", HttpStatus.CREATED)
    }

    @DeleteMapping("api/admin/category/{categoryId}")
    fun deleteCategory(@PathVariable categoryId: Long): ResponseEntity<String> {
        val status = categoryService.deleteCategory(id = categoryId)
        return ResponseEntity(status, HttpStatus.OK)
    }

    @PutMapping("api/public/category/{categoryId}")
    fun updateCategory(@RequestBody category1: Category, @PathVariable categoryId: Long): ResponseEntity<String> {
        log.info("Updating category ${category1.categoryName}")
        categoryService.updateCategory(category1 = category1, id = categoryId)
        return ResponseEntity("Category added successfully!", HttpStatus.OK)
    }
}