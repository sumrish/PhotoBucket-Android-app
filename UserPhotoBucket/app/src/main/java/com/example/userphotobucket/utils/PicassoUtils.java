package com.example.userphotobucket.utils;

import android.widget.ImageView;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
public class PicassoUtils {

    public static boolean isAllowedPicassoRequest(ImageView imageView, String url) {
        return imageView == null || /*!AppUtils.ifNotNullEmpty(url)*/false;
    }

    public static RequestCreator getPicassoRequestCreator( String url, int fallbackResourceId) {
        RequestCreator picassoRequestCreator = Picasso.get().load(url);
        if (fallbackResourceId > 0) {
            picassoRequestCreator = picassoRequestCreator.error(fallbackResourceId).placeholder(fallbackResourceId);
        }
        return picassoRequestCreator.memoryPolicy(MemoryPolicy.NO_STORE, MemoryPolicy.NO_CACHE);
    }

    public static void picassoLoadUrlUsingListTag( final ImageView imageView,
                                                  final String url, String picassoTag,
                                                  final int errorResId, int size, boolean centerCrop) {
        if (isAllowedPicassoRequest( imageView, url)) return;
        RequestCreator picassoRequestCreator = getPicassoRequestCreator( url, errorResId);
        picassoRequestCreator = picassoRequestCreator.tag(picassoTag);
        if (size > 0)
            picassoRequestCreator = picassoRequestCreator.resize(size, size);
        if (centerCrop) picassoRequestCreator = picassoRequestCreator.centerCrop();
        picassoRequestCreator.memoryPolicy(MemoryPolicy.NO_STORE, MemoryPolicy.NO_CACHE).into(imageView);
    }

}
