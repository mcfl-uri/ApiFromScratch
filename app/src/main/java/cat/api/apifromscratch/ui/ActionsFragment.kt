package cat.api.apifromscratch.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import app.com.retrofitwithrecyclerviewkotlin.RecyclerAdapter
import cat.api.apifromscratch.R
import cat.api.apifromscratch.api.ApiInterface
import cat.api.apifromscratch.api.models.AuthorData
import cat.api.apifromscratch.databinding.FragmentActionsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActionsFragment : Fragment() {

    lateinit var binding: FragmentActionsBinding
    lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: RecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentActionsBinding>(
            inflater,
            R.layout.fragment_actions, container, false
        )



        return binding.root
    }

    fun getAllAuthors() {
        val call = ApiInterface.create().getAllAuthors()

        call.enqueue( object : Callback<List<AuthorData>>{
            override fun onResponse(call: Call<List<AuthorData>>?, response: Response<List<AuthorData>>?) {

                if(response?.body() != null)
                    recyclerAdapter.setMovieListItems(response.body()!!)
            }

            override fun onFailure(call: Call<List<AuthorData>>?, t: Throwable?) {

            }
        })

    }

}