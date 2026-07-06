package com.springboot.sbecom.service

import com.springboot.sbecom.model.Category

interface CategoryService {
    fun getCategories(): List<Category>
    fun createCategory(category: Category)
    fun deleteCategory(id: Long): String
    fun updateCategory(category1: Category, id: Long): Category
}