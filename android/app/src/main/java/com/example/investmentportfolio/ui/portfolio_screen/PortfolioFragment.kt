package com.example.investmentportfolio.ui.portfolio_screen

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
import androidx.navigation.fragment.navArgs
import com.example.investmentportfolio.ui.my_portfolios_screen.elements.MyPortfoliosScreen
import com.example.investmentportfolio.ui.portfolio_screen.elements.PortfolioScreen
import com.example.investmentportfolio.ui.theme.AppTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class PortfolioFragment : Fragment() {

    private val args by navArgs<PortfolioFragmentArgs>()
    private val viewModel: PortfolioViewModel by viewModel()

            override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val navController = findNavController()
        viewModel.init()
        viewModel.id = args.id

        return ComposeView(requireContext()).apply {
            setContent {
                AppTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        PortfolioScreen(
                            uiState = viewModel.uiState.collectAsState().value,
                            navController = navController,
                            showBuyDialog = viewModel::showBuyStockDialog,
                            showSellDialog = viewModel::showSellStockDialog,
                            discardBuyDialog = viewModel::discardBuyStockDialog,
                            discardSellDialog = viewModel::discardSellStockDialog,
                            buyStock = viewModel::buyStock,
                            sellStock = viewModel::sellStock,
                            searchStock = viewModel::searchStocks,
                            showDropdown = viewModel::showDropDown,
                            discardDropdown = viewModel::discardDropDown,
                            chooseStockFromMenu = viewModel::chooseStockFromDropDownMenu,
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