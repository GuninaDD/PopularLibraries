package com.geekbrains.poplibs4.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geekbrains.poplibs4.R;
import com.geekbrains.poplibs4.data.entities.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersViewHolder> {
    private List<UserEntity> data;

    public UsersAdapter() {
        data = new ArrayList<>();
    }

    public void setData(List<UserEntity> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user, parent, false);
        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
        UserEntity model = data.get(position);
        holder.tvUserName.setText(model.getLogin());
        holder.tvUserId.setText(String.valueOf(model.getId()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class UsersViewHolder extends RecyclerView.ViewHolder {
        private TextView tvUserName;
        private TextView tvUserId;

        UsersViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUserId = itemView.findViewById(R.id.tv_user_id);
            tvUserName = itemView.findViewById(R.id.tv_user_name);
        }
    }

    public List<UserEntity> getData() {
        return data;
    }
}