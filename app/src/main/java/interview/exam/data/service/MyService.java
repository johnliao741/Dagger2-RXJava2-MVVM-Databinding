package interview.exam.data.service;

import interview.exam.data.remote.domain.GetItemsResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface MyService {
    @GET("get_memes")
    Observable<GetItemsResponse> getItems();
}
