package interview.exam.ui.home.adapter;

import interview.exam.data.local.entity.GetItem;

public interface ClickCallback<T> {
    public void click(T item);
}
