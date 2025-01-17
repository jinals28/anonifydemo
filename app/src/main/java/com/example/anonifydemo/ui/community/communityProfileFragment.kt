package com.example.anonifydemo.ui.community

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.example.anonifydemo.R
import com.example.anonifydemo.databinding.FragmentCommunityProfileBinding
import com.example.anonifydemo.ui.dataClasses.FollowingTopic
import com.example.anonifydemo.ui.dataClasses.UserViewModel
import com.example.anonifydemo.ui.home.postRecyclerView.PostRecyclerViewAdapter
import com.facebook.shimmer.ShimmerFrameLayout
import kotlinx.coroutines.launch

class communityProfileFragment : Fragment() {

    private lateinit var rvPosts: RecyclerView
    private var _binding : FragmentCommunityProfileBinding? = null

    private val args : communityProfileFragmentArgs by navArgs()
    private val binding get() = _binding
    private lateinit var btnback : ImageButton
   // private lateinit var btnoption : ImageButton
    private lateinit var btnfollow :Button
    private lateinit var txthashtagnm : TextView
    private lateinit var txtbio : TextView
    private lateinit var txtfollowers : TextView
    private lateinit var txtpo : TextView
    private val userViewModel : UserViewModel by activityViewModels()
    private val viewModel: CommunityProfileViewModel by viewModels()
    private lateinit var adapter : PostRecyclerViewAdapter

    private lateinit var shimmerpostrv: ShimmerFrameLayout
    private lateinit var txtnopost : LinearLayout
    private var userId : String = ""

    private var topicName : String = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCommunityProfileBinding.inflate(layoutInflater, container, false)

        topicName = args.communityName

        userId = userViewModel.getUserId()

        lifecycleScope.launch {
            viewModel.getCommunity(userViewModel.getUserId(), topicName)
        }

        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnback = binding!!.btnback
      //  btnoption = binding!!.btnoptions
        btnfollow = binding!!.btnFollow
        txthashtagnm = binding!!.txthashtagnm
        txtbio = binding!!.txtbio
        txtfollowers = binding!!.txtfollowers
        txtpo= binding!!.txtpo
        txtnopost=binding!!.txtnopost

        rvPosts = binding!!.rvPosts
        shimmerpostrv = binding!!.shimmerpostrv
        shimmerpostrv.startShimmer()

        adapter = PostRecyclerViewAdapter(requireContext(), userViewModel.getUserId())

        rvPosts.adapter = adapter

        btnback.setOnClickListener {

            val navController = findNavController()
            if (findNavController().currentDestination!!.id == R.id.communityProfileFragment){
//                val action = communityProfileFragmentDirections.actionCommunityProfileFragmentToSearchCommunityFragment()
//                findNavController().navigate(action)
                navController.navigateUp()
            }
        }

        viewModel.currentCommunity.observe(viewLifecycleOwner){

            txthashtagnm.text = it.communityName
            txtfollowers.text = it.followerCount.toString()
            txtpo.text = it.postCount.toString()

            if (it.isFollowedByUser){
                btnfollow.text = "Following"
            }
        }

        viewModel.postList.observe(viewLifecycleOwner){
            if (it.isEmpty()) {
                shimmerpostrv.stopShimmer()
                shimmerpostrv.visibility = View.GONE
               // rvPosts.visibility = View.GONE
                 txtnopost.visibility = View.VISIBLE
            } else {
                shimmerpostrv.stopShimmer()
                shimmerpostrv.visibility = View.GONE
                rvPosts.visibility = View.VISIBLE
               // txtnopost.visibility = View.GONE
              //  adapter.submitList(posts.toMutableList())
            }
            adapter.submitList(it.toMutableList())
        }

        btnfollow.setOnClickListener {
            val topic = FollowingTopic(topic = topicName,
                followedAt = System.currentTimeMillis())
            when(btnfollow.text){
                "Follow" ->{

                    viewModel.followCommunity(userId = userId, topic)
                    updateList(topic)
                    btnfollow.text = "Following"
                }
                "Following" -> {
                    viewModel.unfollowCommunity(userId, topic)
                    deleteList(topicName)
                    btnfollow.text = "Follow"
                }
            }
        }
    }

    private fun deleteList(topicName: String) {
        userViewModel.removeTopic(topicName)
    }

    private fun updateList(topicName: FollowingTopic) {
        userViewModel.addTopic(topicName)
    }

}