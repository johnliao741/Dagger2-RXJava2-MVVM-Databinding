package interview.exam.ui.home.viewHolder;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import interview.exam.data.local.entity.GetItem;
import interview.exam.databinding.ItemAdapterBinding;
import interview.exam.ui.home.adapter.ClickCallback;

public class ItemViewHolder extends RecyclerView.ViewHolder {
    private ItemAdapterBinding binding;
    private ClickCallback clickCallback;

    public ItemViewHolder(@NonNull ItemAdapterBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(GetItem item) {
        binding.setGetItem(item);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCallback.click(item);
            }
        });
        binding.executePendingBindings();
    }

    public void setClickCallback(ClickCallback clickCallback) {
        this.clickCallback = clickCallback;
    }
}
