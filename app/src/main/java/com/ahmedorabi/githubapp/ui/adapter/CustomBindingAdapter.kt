package com.ahmedorabi.githubapp.ui.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.ahmedorabi.githubapp.data.model.Item
import com.bumptech.glide.Glide

@BindingAdapter("setRepoImage")
fun LoadImage(imageView: ImageView, item: Item) {

    item.let { repo ->

        repo.owner.let { owner ->
            val url = owner.avatar_url
            Glide.with(imageView.context).load(url).into(imageView)
        }


//        if (it.media != null) {
//            if (it.media!!.isNotEmpty()) {
//                val meta = it.media!![0]
//
//                if (meta.mediaMetadata != null) {
//                    if (meta.mediaMetadata!!.isNotEmpty()) {
//                        val metaData = meta.mediaMetadata!![0]
//                        Glide.with(imageView.context).load(metaData.url).into(imageView)
//                    }
//
//                }
//
//
//            }
//        }


    }


}