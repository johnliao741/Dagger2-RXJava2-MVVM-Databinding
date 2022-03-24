package interview.exam.dependency_injection.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import interview.exam.ui.MainActivity;

@Module
public abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(modules = FragmentBuilderModule.class)
    abstract MainActivity contributeMainActivity();
}
