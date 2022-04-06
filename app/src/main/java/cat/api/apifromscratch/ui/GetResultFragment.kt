package cat.api.apifromscratch.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cat.api.apifromscratch.R
import cat.api.apifromscratch.api.ApiInterface
import cat.api.apifromscratch.api.models.AuthorData
import cat.api.apifromscratch.databinding.FragmentActionsBinding
import cat.api.apifromscratch.databinding.FragmentGetResultBinding
import cat.api.apifromscratch.shared.SharedViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetResultFragment : Fragment() {

    lateinit var binding: FragmentActionsBinding
    lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: RecyclerAdapter
    lateinit var model: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentGetResultBinding>(
            inflater,
            R.layout.fragment_get_result, container, false
        )

        model = ViewModelProvider(requireActivity())[SharedViewModel::class.java]

        recyclerView = binding.authorRV
        recyclerAdapter = RecyclerAdapter(requireContext())
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = recyclerAdapter

        if (model.getId() == -1 && model.getNom() == "")
            getAllAuthors()
        else
            getAuthorById(model.getId())

        binding.backButton.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_getResultFragment_to_actionsFragment)
        }

        return binding.root
    }

    fun getAllAuthors() {
        val call = ApiInterface.create().getAllAuthors()

        call.enqueue( object : Callback<List<AuthorData>> {
            override fun onResponse(call: Call<List<AuthorData>>?, response: Response<List<AuthorData>>?) {

                if(response?.body() != null)
                    recyclerAdapter.setAuthorListItems(response.body()!!)
            }

            override fun onFailure(call: Call<List<AuthorData>>?, t: Throwable?) {
                showAlert()
            }
        })
    }

    fun getAuthorById(id: Int) {
        val call = ApiInterface.create().getAuthorById(id)

        call.enqueue(object : Callback<AuthorData> {
            /*override fun onResponse(
                call: Call<List<AuthorData>>,
                response: Response<List<AuthorData>>
            ) {
                if (response?.body() != null)
                    recyclerAdapter.setAuthorListItems(response.body()!!)
            }

            override fun onFailure(call: Call<List<AuthorData>>, t: Throwable) {
                showAlert()
            }*/

            override fun onResponse(call: Call<AuthorData>, response: Response<AuthorData>) {
                if (response?.body() != null) {
                    val tmp: List<AuthorData> = listOf(response.body()!!)
                    recyclerAdapter.setAuthorListItems(tmp)
                }
            }

            override fun onFailure(call: Call<AuthorData>, t: Throwable) {
                showAlert()
            }

        })
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(requireActivity())
        builder.setTitle("Error")
        builder.setMessage("Error al recuperar les dades")
        builder.setPositiveButton("Tanca", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

}