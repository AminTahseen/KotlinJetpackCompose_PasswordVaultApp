package com.example.passwordvaultapp_mvvm_compose.feature_vault_categories.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable

import androidx.compose.ui.unit.dp
import com.example.passwordvaultapp_mvvm_compose.feature_vault_categories.domain.models.VaultCategory
import com.example.passwordvaultapp_mvvm_compose.feature_vault_categories.domain.models.toVaultCategoryDisplay

@Composable
fun CategoryListCompose(
    categoryList: List<VaultCategory>,
){

    LazyColumn(
        contentPadding = PaddingValues(16.dp)
    ) {
        items(categoryList) { category ->
            CategoryListItemCompose(category.toVaultCategoryDisplay())
        }
    }
}