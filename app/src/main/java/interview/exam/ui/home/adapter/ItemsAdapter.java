package interview.exam.ui.home.adapter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import interview.exam.R;
import interview.exam.data.local.entity.GetItem;
import interview.exam.databinding.ItemAdapterBinding;
import interview.exam.ui.home.viewHolder.ItemViewHolder;


public class ItemsAdapter extends ListAdapter<GetItem, ItemViewHolder> {
    public ClickCallback clickCallback;
    public ItemOnChangeCallback itemOnChangeCallback;
    public ItemsAdapter(ClickCallback clickCallback,ItemOnChangeCallback itemOnChangeCallback) {
        super(DIFF_CALLBACK);
        this.clickCallback = clickCallback;
        this.itemOnChangeCallback = itemOnChangeCallback;
    }
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(
                ItemAdapterBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.setClickCallback(clickCallback);
        holder.bind(getItem(position));
    }

    @Override
    public void onCurrentListChanged(@NonNull List<GetItem> previousList, @NonNull List<GetItem> currentList) {
        super.onCurrentListChanged(previousList, currentList);
        itemOnChangeCallback.onChange();
    }

    public static final DiffUtil.ItemCallback<GetItem> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<GetItem>() {
                @Override
                public boolean areItemsTheSame(
                        @NonNull GetItem oldItem, @NonNull GetItem newItem) {
                    return oldItem.getId() == newItem.getId();
                }
                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(
                        @NonNull GetItem oldItem, @NonNull GetItem newItem) {
                    return oldItem.getName() == newItem.getName();
                }
            };
}
