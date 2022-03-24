package interview.exam.dependency_injection.usecase;

import java.util.List;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import interview.exam.data.local.entity.GetItem;
import interview.exam.data.remote.domain.GetItemsResponse;
import interview.exam.usecase.AppUseCase;
import interview.exam.usecase.GetItemUseCase;
import interview.exam.usecase.GetItemsUseCase;
import interview.exam.usecase.UpdateItemUseCase;
import io.reactivex.Observable;

@Module

public abstract class UseCaseModule {
    @Binds
    @Singleton
    abstract AppUseCase<String, Observable<List<GetItem>>> bindGetItemsUseCase(GetItemsUseCase useCase);

    @Binds
    @Singleton
    abstract AppUseCase<String, Observable<GetItem>> bindGetItemUseCase(GetItemUseCase useCase);

    @Binds
    @Singleton
    abstract AppUseCase<GetItem, Observable<Boolean>> bindUpdateItemUseCase(UpdateItemUseCase useCase);
}
