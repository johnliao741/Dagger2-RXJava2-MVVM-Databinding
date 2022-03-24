package interview.exam.data.remote.model;

import java.util.List;

import interview.exam.data.local.entity.GetItem;

public class GetItems {
    private List<GetItem> memes;

    public List<GetItem> getMemes() {
        return memes;
    }

    public void setMemes(List<GetItem> memes) {
        this.memes = memes;
    }

    @Override
    public String toString() {
        return "GetItems{" +
                "memes=" + memes +
                '}';
    }
}
