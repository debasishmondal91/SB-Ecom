package com.springboot.sbecom.repositories

import com.springboot.sbecom.model.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository: JpaRepository<Category, Long>