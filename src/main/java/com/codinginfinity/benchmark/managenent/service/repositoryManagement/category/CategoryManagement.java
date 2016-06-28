package com.codinginfinity.benchmark.managenent.service.repositoryManagement.category;

import com.codinginfinity.benchmark.managenent.domain.Category;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.exception.DuplicateCategoryException;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.exception.NonExistentCategoryException;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.request.*;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.response.*;

/**
 * Created by andrew on 2016/06/26.
 */
public interface CategoryManagement<T extends Category> {

    AddCategoryResponse<T> addCategory(AddCategoryRequest<T> request) throws DuplicateCategoryException;
    DeleteCategoryResponse<T> deleteCategory(DeleteCategoryRequest<T> request) throws NonExistentCategoryException;
    UpdateCategoryResponse<T> updateCategory(UpdateCategoryRequest<T> request) throws NonExistentCategoryException;
    GetCategoryByIdResponse<T> getCategoryById(GetCategoryByIdRequest<T> request) throws NonExistentCategoryException;
    GetCategoryByNameResponse<T> getCategoryByName(GetCategoryByNameRequest<T> request) throws NonExistentCategoryException;
}
