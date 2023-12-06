package mars.marsweather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar

class StartFragment : Fragment() {
    private var _vm: StartVm? = null
    private val vm:StartVm get() = _vm ?: throw Exception("Start viewModel is null")

    private var image:ImageView? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _vm = ViewModelProvider(this)[StartVm::class.java]
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // For ViewModel
        setObservers()

        image =  view.findViewById(R.id.mars_photo)

        view.findViewById<Button>(R.id.btn_action).setOnClickListener {
            vm.loadPhotoFromMars()
        }
    }

    private fun setObservers() {
        vm.state.observe(viewLifecycleOwner){
            showSnack(it)
        }

        vm.imageMarsUrl.observe(viewLifecycleOwner){
            if (it.isNotEmpty()){
                Glide
                    .with(this)
                    .load(it)
                    .centerCrop()
                    .into(image!!)
            }
        }
    }

    private fun showSnack(txt:String){
        Snackbar.make(requireView(), txt, Snackbar.LENGTH_SHORT)
            .setTextColor(resources.getColor(R.color.dark_primary, requireContext().theme))
            .setTextMaxLines(20)
            .show()
    }
}

class DefaultFragment<T : ViewBinding>() : Fragment() {

    lateinit var x: (LayoutInflater, ViewGroup?, Boolean) -> T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val k = x(inflater, container, false)
        return k.root
        //return super.onCreateView(inflater, container, savedInstanceState)
    }
}