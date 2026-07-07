package com.springboot.sbecom.service

import com.springboot.sbecom.execption.ApiException
import com.springboot.sbecom.model.Category
import com.springboot.sbecom.repositories.CategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import kotlin.jvm.optionals.getOrNull

@Service
class CategoryServiceImpl: CategoryService {

    @Autowired
    lateinit var categoryRepository: CategoryRepository

    override fun getCategories(): List<Category> {
        if (categoryRepository.findAll().isEmpty()) {
            throw ApiException("Category not created!")
        }
        return categoryRepository.findAll()
    }

    override fun createCategory(category: Category) {
        categoryRepository.findByCategoryName(category.categoryName)?.let {
            throw ApiException("Category with ${category.categoryName} already exists!!!")
        }
        categoryRepository.save(category)
    }

    override fun deleteCategory(id: Long): String {
        val categorySaved = categoryRepository.findById(id).getOrNull()
        ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found!")
        categoryRepository.delete(categorySaved)
        return "Category $id deleted"
    }

    override fun updateCategory(category1: Category, id: Long): Category {
        if (!categoryRepository.existsById(id)) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found!")
        }
        category1.categoryId = id
        categoryRepository.save(category1)
        return category1
    }
}