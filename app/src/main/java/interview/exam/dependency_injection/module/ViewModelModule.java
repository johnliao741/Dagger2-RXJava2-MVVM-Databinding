package interview.exam.dependency_injection.module;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

import javax.inject.Provider;

import dagger.Binds;
import dagger.MapKey;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import interview.exam.ui.home.HomeViewModel;
import interview.exam.ui.item_detail.ItemDetailViewModel;
import interview.exam.usecase.GetItemUseCase;
import interview.exam.usecase.GetItemsUseCase;
import interview.exam.usecase.UpdateItemUseCase;
import interview.exam.util.ViewModelFactory;


@Module
public class ViewModelModule {
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @MapKey
    @interface ViewModelKey {
        Class<? extends ViewModel> value();
    }
    @Provides
    ViewModelFactory viewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> providerMap) {
        return new ViewModelFactory(providerMap);
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Provides
    @IntoMap
    @ViewModelKey(HomeViewModel.class)
    ViewModel HomeViewModel(GetItemsUseCase getItemsUseCase) {
        return new HomeViewModel(getItemsUseCase);
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Provides
    @IntoMap
    @ViewModelKey(ItemDetailViewModel.class)
    ViewModel ItemDetailViewModel(GetItemUseCase getItemUseCase, UpdateItemUseCase updateItemUseCase) {
        return new ItemDetailViewModel(getItemUseCase,updateItemUseCase);
    }
}