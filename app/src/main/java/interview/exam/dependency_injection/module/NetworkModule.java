package interview.exam.dependency_injection.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import interview.exam.BuildConfig;
import interview.exam.data.service.MyService;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

@Module
public class NetworkModule {
    @Singleton
    @Provides
    MyService provideMyService(Retrofit retrofit){
        return retrofit.create(MyService.class);
    }
    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient(){
        return new OkHttpClient.Builder().build();
    }
    @Singleton
    @Provides
    Retrofit provideRetrofit(OkHttpClient client){
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(String.format(BuildConfig.ApiUriFormat,BuildConfig.Scheme,BuildConfig.ServerIP,BuildConfig.ApiPath))
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
