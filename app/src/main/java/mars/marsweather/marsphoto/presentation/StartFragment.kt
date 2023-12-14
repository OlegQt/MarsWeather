package mars.marsweather.marsphoto.presentation

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import mars.marsweather.R
import mars.marsweather.databinding.FragmentStartBinding

class StartFragment : Fragment() {
    private var _vm: StartVm? = null
    private val vm: StartVm get() = _vm ?: throw Exception("Start viewModel is null")

    private lateinit var _binding: FragmentStartBinding
    private val binding: FragmentStartBinding get() = _binding

    private var image: ImageView? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _vm = ViewModelProvider(this)[StartVm::class.java]
        _binding = FragmentStartBinding.inflate(inflater, container, false)

        // Inflate the layout for this fragment
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // For ViewModel
        setObservers()

        image = view.findViewById(R.id.mars_photo)

        binding.btnAction.setOnClickListener {
            //vm.loadPhotoFromMars(binding.txtInputDate.text.toString())
            vm.uploadPhoto()
        }

        binding.lblDateInput.setStartIconOnClickListener {
            openDatePicker()
        }
    }

    private fun openDatePicker() {
        val dateDlg = DatePickerDialog(requireContext())
        dateDlg.setOnDateSetListener { datePicker, year, month, day ->
            val strDate = with(StringBuilder()) {
                append("$year-")
                append("${month + 1}-")
                append("$day")
            }.toString()
            binding.txtInputDate.setText(strDate)
            vm.chooseNewDate(strDate)
        }
        dateDlg.show()
    }

    private fun setObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.fragmentState.collect {
                    if (it is MarsPhotoFragmentState.Content) renderMarsPhoto(it.imageUrl)
                    if (it is MarsPhotoFragmentState.Loading) showLoadingAnimation()
                    if (it is MarsPhotoFragmentState.Error) showSnack(it.errorMsg)
                }
            }
        }
    }

    private fun showLoadingAnimation() {
        binding.animLoading.isVisible = true
        binding.marsPhoto.isVisible = false

    }

    private fun renderMarsPhoto(imgUrl: String) {
        if (imgUrl.isNotEmpty()) {
            Glide
                .with(this)
                .load(imgUrl)
                .centerCrop()
                .into(image!!)
            binding.marsPhoto.isVisible = true
            binding.animLoading.isVisible = false

        }
    }

    private fun showSnack(txt: String) {
        Snackbar.make(requireView(), txt, Snackbar.LENGTH_INDEFINITE)
            .setTextColor(resources.getColor(R.color.dark_primary, requireContext().theme))
            .setTextMaxLines(20)
            .setAction("OK") { }
            .show()
    }
}