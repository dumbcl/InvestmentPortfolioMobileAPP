package com.example.investmentportfolio.ui.history_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.investmentportfolio.ui.history_screen.elements.OperationsHistoryScreen
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.investmentportfolio.ui.theme.AppTheme

class OperationsHistoryFragment : Fragment() {

    private val viewModel: OperationsHistoryViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val navController = findNavController()

        viewModel.init()

        return ComposeView(requireContext()).apply {
            setContent {
                AppTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        OperationsHistoryScreen(
                            uiState = viewModel.uiState.collectAsState().value,
                            navController = navController,
                            reload = viewModel::reload
                        )
                    }
                }
            }
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}