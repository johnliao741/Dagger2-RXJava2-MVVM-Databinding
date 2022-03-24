package interview.exam.util.binding_adapter;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class ItemBindingAdapter {
    @BindingAdapter("android:text")
    public static void setText(TextView view, int amount) {
        view.setText(amount+"");
    }
    @BindingAdapter("android:setImage")
    public static void setImage(ImageView view, String url) {
        if(url != null)
        Glide.with(view.getContext()).load(url).into(view);
    }
}
