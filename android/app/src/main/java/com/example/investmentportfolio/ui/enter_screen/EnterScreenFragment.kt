package com.example.investmentportfolio.ui.enter_screen

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
import com.example.investmentportfolio.ui.enter_screen.elements.EnterScreen
import com.example.investmentportfolio.ui.my_portfolios_screen.MyPortfoliosViewModel
import com.example.investmentportfolio.ui.my_portfolios_screen.elements.MyPortfoliosScreen
import com.example.investmentportfolio.ui.theme.AppTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class EnterScreenFragment : Fragment() {

    private val viewModel: EnterViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val navController = findNavController()
        viewModel.navController = navController

        return ComposeView(requireContext()).apply {
            setContent {
                AppTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        EnterScreen(
                            uiState = viewModel.uiState.collectAsState().value,
                            toStartForm = viewModel::toStartForm,
                            toLoginForm = viewModel::toLoginForm,
                            toRegistrationForm = viewModel::toRegisterForm,
                            login = viewModel::login,
                            register = viewModel::register,
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