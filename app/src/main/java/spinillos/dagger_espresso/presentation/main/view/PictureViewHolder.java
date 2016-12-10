package spinillos.dagger_espresso.presentation.main.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import spinillos.dagger_espresso.R;

public class PictureViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.picture)
    PictureView picture;

    public PictureViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public PictureView getPicture() {
        return picture;
    }
}
