package com.app.blingy.reauty.feature.home.presentation.view

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.blingy.reauty.R
import com.app.blingy.reauty.core.domain.model.feed.BeautyTipType
import com.app.blingy.reauty.core.util.exhaustive
import com.app.blingy.reauty.core.util.extension.shortSnackBar
import com.app.blingy.reauty.databinding.FragmentBeautyTipBinding
import com.app.blingy.reauty.databinding.LayoutDialogBeautyTipBinding
import com.app.blingy.reauty.feature.home.presentation.adapter.BeautyTipDetailAdapter
import com.app.blingy.reauty.feature.home.presentation.contract.BeautyTipContract
import com.app.blingy.reauty.feature.home.presentation.viewmodel.BeautyTipViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BeautyTipFragment : Fragment() {

    companion object {
        const val DEFAULT_SPAN_COUNT = 2
    }

    private var _binding: FragmentBeautyTipBinding? = null
    private val binding get() = _binding!!

    val args: BeautyTipFragmentArgs by navArgs()

    private val viewModel: BeautyTipViewModel by viewModels()
    private lateinit var beautyTipDetailAdapter: BeautyTipDetailAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentBeautyTipBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbarBeautyTip.title = args.beautyTipTypeSafeArgs.value
        onBackArrowPressed()
        setupUi()
        sendBeautyTipGetEvent(args.beautyTipTypeSafeArgs)
        initObserver()
        initErrorObserver()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding?.rvBeautyTip?.adapter = null
        _binding = null
    }

    private fun setupUi() {
        setupBeautyTipDetailRecyclerView()
    }

    private fun initObserver() {
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect { uiState ->
                when (uiState.isLoading) {
                    true -> binding.progress.isVisible = true
                    false -> binding.progress.isVisible = false
                }.exhaustive
                when (uiState.beautyTips.isNotEmpty()) {
                    true -> updateBeautyTipUiState(uiState, beautyTipDetailAdapter)
                    false -> showEmptyDataUi()
                }.exhaustive
            }
        }
    }

    private fun initErrorObserver() {
        lifecycleScope.launchWhenStarted {
            viewModel.sideEffect.collect { sideEffect ->
                when (sideEffect) {
                    is BeautyTipContract.BeautyTipSideEffect.ShowSnackBar -> showSnackBar(sideEffect.message)
                }
            }
        }
    }

    private fun setupBeautyTipDetailRecyclerView() {
        beautyTipDetailAdapter = createBeautyTipDetailAdapter()
        val beautyTipDetailLayoutManager =
            GridLayoutManager(requireContext(), DEFAULT_SPAN_COUNT).also {
                it.orientation = LinearLayoutManager.VERTICAL
            }
        binding.rvBeautyTip.apply {
            adapter = beautyTipDetailAdapter
            layoutManager = beautyTipDetailLayoutManager
            setHasFixedSize(true)
        }
    }

    private fun createBeautyTipDetailAdapter(): BeautyTipDetailAdapter {
        return BeautyTipDetailAdapter {
            showDialog(resInt = it)
        }
    }

    // just a walk around for null ViewGroup when inflate the layout
    @SuppressLint("InflateParams")
    private fun createDialog(image: Drawable): MaterialAlertDialogBuilder {
        val dialogView = LayoutDialogBeautyTipBinding
            .inflate(LayoutInflater.from(requireContext()))
        dialogView.imvDialogBeautyTip.setImageDrawable(image)
        return MaterialAlertDialogBuilder(requireContext(), R.style.Dialog_BeautyTip)
            .setView(dialogView.root)
    }

    private fun getImageDrawable(resInt: Int): Drawable? {
        val imageRes = beautyTipDetailAdapter.currentList[resInt].imageUrl
        return ContextCompat.getDrawable(requireContext(), imageRes)
    }

    private fun showDialog(resInt: Int) {
        val image = getImageDrawable(resInt)
        createDialog(image!!)
            .setBackground(ColorDrawable(Color.TRANSPARENT))
            .show()
    }

    private fun sendBeautyTipGetEvent(beautyTipType: BeautyTipType) {
        lifecycleScope.launch {
            when (beautyTipType) {
                BeautyTipType.ACNE_PRONE -> sendEvent(BeautyTipContract.BeautyTipEvent.GetAcneProneTips)
                BeautyTipType.ANTI_AGING -> sendEvent(BeautyTipContract.BeautyTipEvent.GetAntiAgingTips)
                BeautyTipType.CLEAN_BEAUTY -> sendEvent(BeautyTipContract.BeautyTipEvent.GetCleanBeautyTips)
                BeautyTipType.CRUELTY_FREE -> sendEvent(BeautyTipContract.BeautyTipEvent.GetCrueltyFreeTips)
                BeautyTipType.DARK_CIRCLES -> sendEvent(BeautyTipContract.BeautyTipEvent.GetDarkCirclesTips)
                BeautyTipType.DEHYDRATED -> sendEvent(BeautyTipContract.BeautyTipEvent.GetDehydratedTips)
                BeautyTipType.DULL_SKIN -> sendEvent(BeautyTipContract.BeautyTipEvent.GetDullSkinTips)
                BeautyTipType.LARGE_PORES -> sendEvent(BeautyTipContract.BeautyTipEvent.GetLargePoresTips)
                BeautyTipType.MENS_SKIN -> sendEvent(BeautyTipContract.BeautyTipEvent.GetMensSkinTips)
                BeautyTipType.NATURAL_BEAUTY -> sendEvent(BeautyTipContract.BeautyTipEvent.GetNaturalBeautyTips)
                BeautyTipType.REDNESS -> sendEvent(BeautyTipContract.BeautyTipEvent.GetRednessTips)
                BeautyTipType.VEGAN_BEAUTY -> sendEvent(BeautyTipContract.BeautyTipEvent.GetVeganBeautyTips)
            }.exhaustive
        }
    }

    private fun sendEvent(event: BeautyTipContract.BeautyTipEvent) {
        lifecycleScope.launch { viewModel.setEvent(event) }
    }

    private fun updateBeautyTipUiState(
        uiState: BeautyTipContract.BeautyTipViewState, adapter: BeautyTipDetailAdapter
    ) {
        adapter.submitList(uiState.beautyTips)
    }


    private fun showSnackBar(value: String) {
        binding.root.shortSnackBar(message = value)
    }

    private fun showEmptyDataUi() {}

    private fun onBackArrowPressed() {
        // when user clicked the navigation icon (here is back arrow)
        binding.toolbarBeautyTip.setNavigationOnClickListener {
            // find the nav controller and pop backstack
            findNavController().popBackStack()
        }
    }

}