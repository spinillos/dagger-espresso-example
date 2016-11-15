package spinillos.dagger_espresso.presentation.main.view;

import com.squareup.picasso.Picasso;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import spinillos.dagger_espresso.R;

/**
 * Created by Selene on 13/11/16.
 */

public class PictureViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.picture)
    ImageView picture;

    public PictureViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setPicture(String path) {
        Picasso.with(itemView.getContext()).load(path).into(picture);
    }
}
