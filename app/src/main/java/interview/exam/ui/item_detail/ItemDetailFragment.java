package interview.exam.ui.item_detail;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import interview.exam.R;
import interview.exam.databinding.ItemDetailFragmentBinding;
import interview.exam.dependency_injection.Injectable;
import interview.exam.ui.home.HomeViewModel;
import interview.exam.util.ViewModelFactory;

public class ItemDetailFragment extends Fragment implements Injectable {

    private ItemDetailViewModel mViewModel;
    private ItemDetailFragmentBinding binding;
    @Inject
    ViewModelFactory viewModelFactory;
    public static ItemDetailFragment newInstance() {
        return new ItemDetailFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.item_detail_fragment,
                container,false
        );
        mViewModel = new ViewModelProvider(this,viewModelFactory).get(ItemDetailViewModel.class);
        binding.setViewModel(mViewModel);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String itemId = ItemDetailFragmentArgs.fromBundle(getArguments()).getItemId();
        binding.etRemark.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        mViewModel.editRemark(s.toString());
                    }
                }
        );
        mViewModel.getItem(itemId);
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

}