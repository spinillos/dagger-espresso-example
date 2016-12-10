package spinillos.dagger_espresso.presentation.main.view;

import com.squareup.picasso.Picasso;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import spinillos.dagger_espresso.R;
import spinillos.dagger_espresso.presentation.main.model.Picture;

/**
 * Created by Selene on 13/11/16.
 */

public class PictureAdapter extends RecyclerView.Adapter<PictureViewHolder> {

    private Context context;
    private List<Picture> pictures;

    public PictureAdapter(Context context) {
        this.context = context;
        pictures = new ArrayList<>();
    }

    @Override
    public PictureViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_picture, parent, false);
        return new PictureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PictureViewHolder holder, int position) {
        Picture picture = pictures.get(position);
        Picasso.with(context)
                .load(Uri.fromFile(new File(picture.getPath())))
                .resize(300, 300)
                .centerInside()
                .into(holder.getPicture());
    }

    @Override
    public int getItemCount() {
        return pictures.size();
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures.clear();
        this.pictures.addAll(pictures);
        notifyDataSetChanged();
    }

    public void addPicture(Picture picture) {
        this.pictures.add(0, picture);
        notifyDataSetChanged();
    }
}
