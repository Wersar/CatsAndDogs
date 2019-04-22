package com.test.catsanddogs.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.test.catsanddogs.R;
import com.test.catsanddogs.data.Pet;

import java.util.List;

public class PetsAdapter extends RecyclerView.Adapter<PetsAdapter.ViewHolder> {

    private List<Pet> data;
    private LayoutInflater inflater;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public PetsAdapter(Context context, List<Pet> data) {
        this.data = data;
        this.inflater = LayoutInflater.from(context);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        Pet pet = data.get(i);
        viewHolder.setTitle(pet.getTitle());
        Picasso.get()
                .load(pet.getUrl())
                .resizeDimen(R.dimen.list_item_height, R.dimen.list_item_height)
                .centerCrop()
                .into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(this);
        }

        public void setTitle(String title) {
            textView.setText(title);
        }

        public void setImage(Bitmap bitmap) {
            imageView.setImageBitmap(bitmap);
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            if (pos != RecyclerView.NO_POSITION) {
                if (listener != null) {
                    listener.onItemClick(v, pos);
                }
            }
        }
    }
}
