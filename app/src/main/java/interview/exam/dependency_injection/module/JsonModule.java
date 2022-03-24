package interview.exam.dependency_injection.module;

import com.squareup.moshi.Moshi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class JsonModule {
    @Singleton
    @Provides
    Moshi provideMoshi(){
        return new Moshi.Builder().build();
    }
}
