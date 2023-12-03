package mars.marsweather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import mars.marsweather.databinding.FragmentRootBinding

class StartFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false)
    }
}

class DefaultFragment<T:ViewBinding> ():Fragment(){

    lateinit var x:(LayoutInflater,ViewGroup?,Boolean)->T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val k = x(inflater,container,false)
        return k.root
        //return super.onCreateView(inflater, container, savedInstanceState)
    }
}