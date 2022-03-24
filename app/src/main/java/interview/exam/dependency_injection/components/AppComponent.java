package interview.exam.dependency_injection.components;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;
import interview.exam.MyApp;
import interview.exam.dependency_injection.module.ActivityBuilderModule;
import interview.exam.dependency_injection.module.AppModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ActivityBuilderModule.class,
        AppModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(MyApp application);
        AppComponent build();
    }

    void inject(MyApp app);
}
