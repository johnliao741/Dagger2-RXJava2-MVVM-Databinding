package interview.exam.ui.home;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import java.util.List;

import javax.inject.Inject;

import interview.exam.R;
import interview.exam.data.local.entity.GetItem;
import interview.exam.databinding.HomeFragmentBinding;
import interview.exam.dependency_injection.Injectable;
import interview.exam.dependency_injection.module.FragmentBuilderModule_ContributeHomeFragment;
import interview.exam.ui.home.adapter.ClickCallback;
import interview.exam.ui.home.adapter.ItemOnChangeCallback;
import interview.exam.ui.home.adapter.ItemsAdapter;
import interview.exam.util.ViewModelFactory;
import interview.exam.util.item_decoration.GeneralVerticalItemDecoration;

public class HomeFragment extends Fragment implements Injectable,ClickCallback<GetItem>,ItemOnChangeCallback {

    private HomeViewModel mViewModel;
    private HomeFragmentBinding binding;
    @Inject
    ViewModelFactory viewModelFactory;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.home_fragment,
        container,false
                );

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this,viewModelFactory).get(HomeViewModel.class);
        ItemsAdapter adapter = new ItemsAdapter(this,this);

        binding.rvItems.addItemDecoration(new GeneralVerticalItemDecoration());
        binding.rvItems.setAdapter(adapter);
        binding.svSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mViewModel.searchItemById(newText);
                return false;
            }
        });
        mViewModel.items.observe(getViewLifecycleOwner(), new Observer<List<GetItem>>() {
            @Override
            public void onChanged(List<GetItem> getItems) {
                adapter.submitList(getItems);
            }
        });

        mViewModel.getItems();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void click(GetItem item) {
        NavHostFragment.findNavController(HomeFragment.this).navigate(HomeFragmentDirections.toItemDetailFragment(item.getId()));
    }

    @Override
    public void onChange() {
        binding.rvItems.post(new Runnable() {
            @Override
            public void run() {
                binding.rvItems.getLayoutManager().scrollToPosition(0);
            }
        });
    }
}