package mars.marsweather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import mars.marsweather.databinding.FragmentRootBinding
import mars.marsweather.marsphoto.presentation.StartFragment

class RootFragment : Fragment() {
    private var _binding: FragmentRootBinding? = null
    private val binding: FragmentRootBinding
        get() = _binding ?: throw Exception("")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRootBinding.inflate(inflater, container, false)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewPager()
    }

    private fun setUpViewPager() {
        val adapter = RootFragmentRecycler(this, arrayListOf(StartFragment()))
        binding.viewPager.adapter = adapter
    }

    class RootFragmentRecycler(
        fragment: Fragment,
        private val fragmentList: ArrayList<Fragment>
    ) : FragmentStateAdapter(fragment) {
        override fun getItemCount() = fragmentList.size
        override fun createFragment(position: Int) = fragmentList[position]
    }
}