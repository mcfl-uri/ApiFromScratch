package cat.api.apifromscratch.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cat.api.apifromscratch.R
import cat.api.apifromscratch.api.ApiInterface
import cat.api.apifromscratch.api.models.AuthorData
import cat.api.apifromscratch.databinding.FragmentActionsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActionsFragment : Fragment() {

    lateinit var binding: FragmentActionsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentActionsBinding>(
            inflater,
            R.layout.fragment_actions, container, false
        )

        binding.postButton.setOnClickListener {
            if (binding.postInput.text.toString().isBlank()) {
                Toast.makeText(context, "El camp no pot estar buit", Toast.LENGTH_LONG).show()
            } else {
                postAuthor(binding.postInput.text.toString())
            }
        }

        binding.getButton.setOnClickListener {
            /*if (binding.getInput.text.isNullOrBlank()) {

            } else {
                //TODO: GET Concret
            }*/
            view?.findNavController()?.navigate(R.id.action_actionsFragment_to_getResultFragment)
        }

        return binding.root
    }

    fun postAuthor(name: String) {
        val call = ApiInterface.create().postAutor(AuthorData(idAutor = null, nomAutor = name))

        call.enqueue( object : Callback<AuthorData> {

            override fun onResponse(call: Call<AuthorData>, response: Response<AuthorData>) {
                Toast.makeText(context, "Autor pujat", Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<AuthorData>, t: Throwable) {
                showAlert()
            }

        })
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(requireActivity())
        builder.setTitle("Error")
        builder.setMessage("Error afegint al autor")
        builder.setPositiveButton("Tanca", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

}