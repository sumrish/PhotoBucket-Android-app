package com.example.userphotobucket.ui.photo.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.databinding.library.baseAdapters.BR;
import com.example.userphotobucket.R;
import com.example.userphotobucket.databinding.PhotoItemBinding;
import com.example.userphotobucket.interfaces.IOnItemClick;
import com.example.userphotobucket.ui.photo.model.Photo;
import com.example.userphotobucket.utils.PicassoUtils;

import java.util.List;

public class PhotoAdpater extends RecyclerView.Adapter<PhotoAdpater.ViewHolder> {
    private List<Photo> photoList;
    private PhotoItemBinding mPhotoItem;
    private IOnItemClick iOnItemClick;

    public PhotoAdpater() {
    }

    public void setInterface(IOnItemClick itemClick) {
        iOnItemClick = itemClick;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mPhotoItem = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.photo_item, null, false);
        return new ViewHolder(mPhotoItem);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Photo notification = photoList.get(position);
        holder.bind(notification);
        mPhotoItem.main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iOnItemClick.onItemClick(position+1, notification.getThumbnailUrl());
            }
        });
    }

    @Override
    public int getItemCount() {
        if (photoList != null) {
            return photoList.size();
        } else {
            return 0;
        }

    }

    public void setDataset(List<Photo> listAlbums) {
        photoList = listAlbums;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(PhotoItemBinding userItemBinding) {

            super(userItemBinding.getRoot());
            mPhotoItem = userItemBinding;
        }
        public void bind(Photo obj) {
            mPhotoItem.setVariable(BR.photo, obj);
            PicassoUtils.picassoLoadUrlUsingListTag(  mPhotoItem.imageView, obj.getThumbnailUrl(),"thumbnail",R.drawable.ic_launcher_foreground,0,false);
            mPhotoItem.executePendingBindings();
        }
    }

}
