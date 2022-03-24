package interview.exam.usecase;

import java.util.List;

import javax.inject.Inject;

import interview.exam.data.local.entity.GetItem;
import interview.exam.data.remote.domain.GetItemsResponse;
import interview.exam.data.remote.model.GetItems;
import interview.exam.data.repository.MyRepository;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import kotlin.jvm.functions.Function1;

public class GetItemsUseCase extends AppUseCase {
    private MyRepository repository;
    @Inject GetItemsUseCase(MyRepository repository){
        this.repository = repository;
    }
    @Override
    public Observable<List<GetItem>> invoke(Object request) {
        return repository.getItems()
                .doOnNext(getItemsResponse -> {
                    List<GetItem> items = getItemsResponse.getData().getMemes();
                    for (int i = 0; i < items.size(); i++) {
                        repository.insertGetItem(items.get(i));
                    }
                })
                .flatMap((Function<GetItemsResponse, Observable<List<GetItem>>>)
                        getItemsResponse
                        -> repository.getItemAll());
    }
}
