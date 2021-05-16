package com.example.ecoapp.ui.news.article_list

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.core.content.PermissionChecker.checkSelfPermission
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecoapp.R
import com.example.ecoapp.adapter.ArticleListAdapter
import com.example.ecoapp.domain.model.Article
import com.example.ecoapp.ui.news.article.ArticleFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_news.*
import java.util.*


@AndroidEntryPoint
class NewsFragment : Fragment() {

    private val viewModel: NewsViewModel by viewModels()

    private lateinit var adapter: ArticleListAdapter

    //views
    private lateinit var alertImage: ImageView
    private lateinit var alertUploadButton: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViews()
        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel.apply {
            articles.observe(viewLifecycleOwner) {
                adapter.setArticles(it)
            }
        }
    }

    private fun setupViews() {
        adapter = ArticleListAdapter(requireActivity(), listOf())
        news_recycler.adapter = adapter
        news_recycler.layoutManager = LinearLayoutManager(requireContext())
        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    viewModel.newSearch(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    viewModel.onQueryChanged(newText)
                }
                return false
            }
        })
        news_fab.setOnClickListener {
            //todo add new article
            val dialogBuilder = AlertDialog.Builder(requireContext())
            val inflater = this.layoutInflater
            val dialogView: View = inflater.inflate(R.layout.alert_label_editor, null)
            val author = dialogView.findViewById<EditText>(R.id.alert_author)
            val title = dialogView.findViewById<EditText>(R.id.alert_title)
            val description = dialogView.findViewById<EditText>(R.id.alert_description)
            alertImage = dialogView.findViewById<ImageButton>(R.id.alert_image)
            alertUploadButton = dialogView.findViewById<ImageButton>(R.id.alert_upload_button)
            alertUploadButton.setOnClickListener {
                uploadImage()
            }
            dialogBuilder.setView(dialogView)
            dialogBuilder.setTitle("Add New Article")
            dialogBuilder.setPositiveButton("Add") { dialog, index ->
                val date = Calendar.getInstance().time
                viewModel.addArticle(
                    Article(
                        author = author.text?.toString(),
                        title = title.text?.toString(),
                        description = description.text?.toString(),
                        content = description.text?.toString(),
                        publishedAt = date.toString(),
                        imageDrawable = alertImage.drawable
                    )
                )
            }
            val alertDialog = dialogBuilder.create()
            alertDialog.show()
        }
    }

    private fun uploadImage() {
        if (checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            //permission denied
            val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE);
            //show popup to request runtime permission
            requestPermissions(permissions, PERMISSION_CODE);
        } else {
            //permission already granted
            pickImageFromGallery();
        }
    }

    private fun pickImageFromGallery() {
        //Intent to pick image
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    //handle requested permission result
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED
                ) {
                    //permission from popup granted
                    pickImageFromGallery()
                } else {
                    //permission from popup denied
                    Toast.makeText(requireContext(), "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    //handle result of picked image
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            alertImage.visibility = View.VISIBLE
            alertImage.setImageURI(data?.data)
            alertUploadButton.setImageResource(R.drawable.ic_round_arrow_downward_24)
        }
    }

    companion object {
        //image pick code
        private val IMAGE_PICK_CODE = 1000;

        //Permission code
        private val PERMISSION_CODE = 1001;
    }
}
