package interview.exam.dependency_injection.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import interview.exam.ui.home.HomeFragment;
import interview.exam.ui.item_detail.ItemDetailFragment;
@Module
public abstract class FragmentBuilderModule {
    @ContributesAndroidInjector
    abstract HomeFragment contributeHomeFragment();

    @ContributesAndroidInjector
    abstract ItemDetailFragment contributeItemDetailFragment();
}
