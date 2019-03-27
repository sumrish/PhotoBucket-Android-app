package com.example.userphotobucket.ui.user.adapter;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;

import com.example.userphotobucket.R;
import com.example.userphotobucket.databinding.UserItemBinding;
import com.example.userphotobucket.interfaces.IOnItemClick;
import com.example.userphotobucket.ui.user.model.User;
import com.android.databinding.library.baseAdapters.BR;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
        private List<User> userList;
        private UserItemBinding mUserItem;
        private IOnItemClick iOnItemClick;

        public UserAdapter(/*List<User> notificationsList*/) {
//            userList = notificationsList;
        }

        public void setInterface(IOnItemClick itemClick) {
            iOnItemClick = itemClick;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            mUserItem= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.user_item, null, false);
            return new ViewHolder(mUserItem);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            User user = userList.get(position);
            holder.bind(user);
            mUserItem.main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iOnItemClick.onItemClick(position+1);
                }
            });
        }

        @Override
        public int getItemCount() {
            if (userList != null) {
                return userList.size();
            } else {
                return 0;
            }
        }

        public void setDataset(List<User> listUsers) {
            userList = listUsers;
            notifyDataSetChanged();
        }

        @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public ViewHolder(UserItemBinding userItemBinding) {

                super(userItemBinding.getRoot());
                mUserItem = userItemBinding;
            }
            public void bind(User obj) {
                mUserItem.setVariable(BR.user, obj);
                mUserItem.executePendingBindings();
            }
        }

    }
