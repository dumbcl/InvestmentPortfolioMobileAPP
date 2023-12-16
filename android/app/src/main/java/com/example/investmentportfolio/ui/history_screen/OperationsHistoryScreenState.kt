package com.example.investmentportfolio.ui.history_screen

import com.example.investmentportfolio.data.OperationItem

data class OperationsHistoryScreenState(
    var isLoading: Boolean,
    var isError: Boolean,
    var operations: List<OperationItem>,
)
