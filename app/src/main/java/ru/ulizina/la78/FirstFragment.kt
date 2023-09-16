package ru.ulizina.la78

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.ulizina.la78.databinding.FragmentFirstBinding
import ru.ulizina.la78.retrofit.NekoApi
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.gif.GifDrawable

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://nekos.best/api/v2/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val nekoApi = retrofit.create(NekoApi::class.java)

/*
        fun getCategories(){
            CoroutineScope(Dispatchers.IO).launch {
                val nekoCateg = nekoApi.getNekoCategories()
                withContext(Dispatchers.Main) {
                    println(nekoCateg.baka)
                }
            }

        }


        getCategories()

*/
        binding.buttonFirst.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val number = 5
                val neko = nekoApi.getRandomNeko(number)

                withContext(Dispatchers.Main) {
                    for (i in 0 until number) {
                        binding.textviewFirst.text = neko.results[i].anime_name
                        Glide.with(requireContext())
                            .asGif()
                            .load(neko.results[i].url)
                            .into(binding.animeImage)
                        println("Neko name $i <3 ${neko.results[i].anime_name}")
                        println("Neko url $i <3 ${neko.results[i].url}")
                    }
                }
            }
        }

        binding.buttonToSecond.setOnClickListener {
            //findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            CoroutineScope(Dispatchers.IO).launch {
                val number = 5
                val neko = nekoApi.getRandomNekoWithCategories("Senko", 2, "happy", 1)

                withContext(Dispatchers.Main) {
                    for (i in 0 until 1) {
                        binding.textviewFirst.text = neko.results[i].anime_name
                        Glide.with(requireContext())
                            .asGif()
                            .load(neko.results[i].url)
                            .into(binding.animeImage)
                        println("Neko name $i <3 ${neko.results[i].anime_name}")
                        println("Neko url $i <3 ${neko.results[i].url}")
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}