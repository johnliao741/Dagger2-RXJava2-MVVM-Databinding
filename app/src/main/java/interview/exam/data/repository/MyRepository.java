package interview.exam.data.repository;

import java.util.List;

import javax.inject.Inject;

import interview.exam.data.local.dao.RoomDao;
import interview.exam.data.local.entity.GetItem;
import interview.exam.data.remote.domain.GetItemsResponse;
import interview.exam.data.service.MyService;
import io.reactivex.Observable;

public class MyRepository {
    private MyService service;
    private RoomDao dao;
    @Inject
    public MyRepository(MyService service,RoomDao dao) {
        this.service = service;
        this.dao = dao;
    }

    public Observable<GetItemsResponse> getItems(){
        return service.getItems();
    }
    public void insertGetItem(GetItem item){
        dao.insert(item);
    }
    public Observable<List<GetItem>> getItemAll(){
        return dao.getAll();
    }

    public Observable<GetItem> getItem(String item) {
        return dao.findById(item);
    }

    public void updateItem(GetItem item) {
        dao.update(item);
    }
}
