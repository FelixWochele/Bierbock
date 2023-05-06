package com.bierbock.BackendFolder;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;


//Class used for loading the images from url across different classes
public class ImageLoader {

    public interface ImageLoadCallback {
        void onImageLoaded();
    }

    //Load image with context as a parameter
    public static void loadImage(Context context, String imageUrl, ImageView imageView, int targetWidth, int targetHeight, int fallbackImageResId, ImageLoadCallback callback) {
        Glide.with(context)
                .load(imageUrl)
                .fallback(fallbackImageResId) // If the image loading failed
                .override(targetWidth, targetHeight)
                .centerCrop()
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        if (callback != null) {
                            callback.onImageLoaded();
                        }
                        return false; // Return false to set the fallback image if the loading fails
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        if (callback != null) {
                            callback.onImageLoaded();
                        }
                        return false;
                    }
                })
                .into(imageView);
    }

    //Load image with fragment as a parameter
    public static void loadImage(Fragment fragment, String imageUrl, ImageView imageView, int targetWidth, int targetHeight, int fallbackImageResId, ImageLoadCallback callback) {
        Glide.with(fragment)
                .load(imageUrl)
                .fallback(fallbackImageResId) // If the image loading failed
                .override(targetWidth, targetHeight)
                .centerCrop()
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        if (callback != null) {
                            callback.onImageLoaded();
                        }
                        return false; // Return false to set the fallback image if the loading fails
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        if (callback != null) {
                            callback.onImageLoaded();
                        }
                        return false;
                    }
                })
                .into(imageView);
    }
}

