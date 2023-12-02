package mars.marsweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mars.marsweather.databinding.ActivityRootBinding

class RootActivity : AppCompatActivity() {
    private var _binding:ActivityRootBinding? = null
    private val binding:ActivityRootBinding
        get() = _binding ?: throw Exception("binding zero!")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRootBinding.inflate(layoutInflater)


        setContentView(binding.root)
    }
}