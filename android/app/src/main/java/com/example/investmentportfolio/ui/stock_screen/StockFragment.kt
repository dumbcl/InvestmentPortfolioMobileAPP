package com.example.investmentportfolio.ui.stock_screen

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
import com.example.investmentportfolio.ui.search_screen.SearchStocksViewModel
import com.example.investmentportfolio.ui.search_screen.elements.SearchStocksScreen
import com.example.investmentportfolio.ui.stock_screen.elements.StockScreen
import com.example.investmentportfolio.ui.theme.AppTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class StockFragment : Fragment() {

    private val args by navArgs<StockFragmentArgs>()
    private val viewModel: StockViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val navController = findNavController()
        viewModel.init(args.id)

        return ComposeView(requireContext()).apply {
            setContent {
                AppTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        StockScreen(
                            uiState = viewModel.uiState.collectAsState().value,
                            navController
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