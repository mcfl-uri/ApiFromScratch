package cat.api.apifromscratch.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        binding.getButton.setOnClickListener {
            /*if (binding.getInput.text.isNullOrBlank()) {

            } else {
                //TODO: GET Concret
            }*/
            view?.findNavController()?.navigate(R.id.action_actionsFragment_to_getResultFragment)
        }

        return binding.root
    }

}