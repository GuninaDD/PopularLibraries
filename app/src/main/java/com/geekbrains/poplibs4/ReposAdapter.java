package com.geekbrains.poplibs4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geekbrains.poplibs4.rest.RetrofitRepoModel;

import java.util.ArrayList;
import java.util.List;

public class ReposAdapter extends RecyclerView.Adapter<ReposAdapter.ReposViewHolder> {
    private List<RetrofitRepoModel> data;
    private OnItemClickListener listener;

    ReposAdapter(OnItemClickListener listener) {
        this.listener = listener;
        this.data = new ArrayList<>();
    }

    void setData(List<RetrofitRepoModel> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ReposViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_repo, parent, false);
        return new ReposViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReposViewHolder holder, int position) {
        RetrofitRepoModel repo = data.get(position);
        holder.tvRepoName.setText(repo.getName());
        int iconId = repo.isPrivate() ? R.drawable.ic_invisible : R.drawable.ic_visible;
        holder.imRepoPrivacy.setImageResource(iconId);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ReposViewHolder extends RecyclerView.ViewHolder {
        private TextView tvRepoName;
        private ImageView imRepoPrivacy;

        ReposViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRepoName = itemView.findViewById(R.id.tv_repo_name);
            imRepoPrivacy = itemView.findViewById(R.id.im_repo_privacy);
            itemView.setOnClickListener(v -> {
                RetrofitRepoModel model = data.get(getAdapterPosition());
                listener.onItemClick(model);
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(RetrofitRepoModel model);
    }
}
