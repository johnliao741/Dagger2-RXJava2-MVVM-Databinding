package interview.exam.usecase;

import java.util.List;

import javax.inject.Inject;

import interview.exam.data.local.entity.GetItem;
import interview.exam.data.repository.MyRepository;
import io.reactivex.Observable;

public class GetItemUseCase extends AppUseCase<String, Observable<GetItem>>{
    private MyRepository repository;
    @Inject
    GetItemUseCase(MyRepository repository){
        this.repository = repository;
    }

    @Override
    public Observable<GetItem> invoke(String item) {
        return repository.getItem(item);
    }


}
