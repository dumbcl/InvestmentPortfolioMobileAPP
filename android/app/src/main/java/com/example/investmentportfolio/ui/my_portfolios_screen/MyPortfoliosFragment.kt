package com.example.investmentportfolio.ui.my_portfolios_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.example.investmentportfolio.ui.theme.AppTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.fragment.findNavController
import com.example.investmentportfolio.ui.my_portfolios_screen.elements.MyPortfoliosScreen
import com.example.investmentportfolio.ui.search_screen.SearchStocksFragmentDirections
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyPortfoliosFragment : Fragment() {

    private val viewModel: MyPortfoliosViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val navController = findNavController()
        viewModel.init()
        viewModel.navController = navController

        return ComposeView(requireContext()).apply {
            setContent {
                AppTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        MyPortfoliosScreen(
                            uiState = viewModel.uiState.collectAsState().value,
                            navController = navController,
                            reload = viewModel::reload,
                            showCreateDialog = viewModel::showCreateDialog,
                            discardCreateDialog = viewModel::discardCreateDialog,
                            createPortfolio = viewModel::createPortfolio,
                            navigateToPortfolio = navigateToPortfolio(),
                        )
                    }
                }
            }
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun navigateToPortfolio(): (Int) -> Unit = { id ->
        findNavController().navigate(MyPortfoliosFragmentDirections.actionMyPortfoliosFragmentToPortfolioFragment(id))
    }
}