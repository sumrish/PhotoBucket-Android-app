package com.example.userphotobucket.ui.album.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.databinding.library.baseAdapters.BR;
import com.example.userphotobucket.R;
import com.example.userphotobucket.databinding.AlbumItemBinding;
import com.example.userphotobucket.interfaces.IOnItemClick;
import com.example.userphotobucket.ui.album.model.Album;
import com.example.userphotobucket.utils.PicassoUtils;

import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {
    private List<Album> albumList;
    private AlbumItemBinding mAlbumItem;
    private IOnItemClick iOnItemClick;

    public AlbumAdapter() {
    }

    public void setInterface(IOnItemClick itemClick) {
        iOnItemClick = itemClick;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mAlbumItem = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.album_item, null, false);
        return new ViewHolder(mAlbumItem);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Album album = albumList.get(position);
        holder.bind(album);
        mAlbumItem.main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iOnItemClick.onItemClick(position+1, album.getId() );
            }
        });
    }

    @Override
    public int getItemCount() {
        if (albumList != null) {
            return albumList.size();
        } else {
            return 0;
        }

    }

    public void setDataset(List<Album> listAlbums) {
        albumList = listAlbums;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(AlbumItemBinding userItemBinding) {

            super(userItemBinding.getRoot());
            mAlbumItem = userItemBinding;
        }
        public void bind(Album obj) {
            mAlbumItem.setVariable(BR.album, obj);
//            mAlbumItem.textView25.setText(obj.getTitle());
            PicassoUtils.picassoLoadUrlUsingListTag(  mAlbumItem.textView2, obj.getThumbnail(),"thumbnail",R.drawable.ic_launcher_foreground,0,false);
            mAlbumItem.executePendingBindings();
        }
    }

}
