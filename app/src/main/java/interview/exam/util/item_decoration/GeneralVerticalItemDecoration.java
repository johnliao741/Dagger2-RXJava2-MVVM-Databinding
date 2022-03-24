package interview.exam.util.item_decoration;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GeneralVerticalItemDecoration extends RecyclerView.ItemDecoration {
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int gapSize = 8;
        if(parent.getChildAdapterPosition(view) == 0){
            outRect.top = gapSize;
        }
        outRect.bottom = gapSize;
    }
}
