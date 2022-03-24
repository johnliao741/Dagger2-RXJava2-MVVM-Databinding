package interview.exam.usecase;


import javax.inject.Inject;

import interview.exam.data.local.entity.GetItem;
import interview.exam.data.repository.MyRepository;
import io.reactivex.Observable;

public class UpdateItemUseCase extends AppUseCase<GetItem, Observable<Boolean>>{
    private MyRepository repository;
    public @Inject UpdateItemUseCase(MyRepository repository){
        this.repository = repository;
    }
    @Override
    public Observable<Boolean> invoke(GetItem item) {
        repository.updateItem(item);
        return Observable.just(true);
    }
}
