package interview.exam.dependency_injection.module;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;
import interview.exam.MyApp;

@Module(includes = {
        JsonModule.class,
        NetworkModule.class,
        RepositoryModule.class,
        ViewModelModule.class

})
public abstract class AppModule {
    @Binds
    abstract Application bindApplication(MyApp app);

    @Binds
    abstract Context bindContext(MyApp app);
}
