package interview.exam.ui.item_detail;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;
import java.util.function.BiFunction;

import javax.inject.Inject;

import interview.exam.data.local.entity.GetItem;
import interview.exam.data.remote.domain.GetItemsResponse;
import interview.exam.usecase.GetItemUseCase;
import interview.exam.usecase.UpdateItemUseCase;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.ReplaySubject;
import io.reactivex.subjects.Subject;

public class ItemDetailViewModel extends ViewModel {
    private GetItemUseCase getItemUseCase;
    private UpdateItemUseCase updateItemUseCase;
    public MutableLiveData<GetItem> item = new MutableLiveData();
    private Subject<String> editRemarkSubject = ReplaySubject.createWithSize(1);
    private Observable<Boolean> canEditRemark = editRemarkSubject.map(
            new Function<String, Boolean>() {
                @Override
                public Boolean apply(@NonNull String s) throws Exception {
                    return !s.isEmpty();
                }
            }
    );
    public LiveData<Boolean> enableRemark =
            LiveDataReactiveStreams.fromPublisher(canEditRemark.toFlowable(BackpressureStrategy.LATEST));
    private String remark;

    public @Inject ItemDetailViewModel(GetItemUseCase getItemUseCase,UpdateItemUseCase updateItemUseCase){
        this.getItemUseCase = getItemUseCase;
        this.updateItemUseCase = updateItemUseCase;
    }
    public void getItem(String itemId) {
        getItemUseCase.invoke(itemId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetItem>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull GetItem getItem) {
                        item.postValue(getItem);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public void updateItem(View view){
        GetItem updateItem = item.getValue();
        updateItem.setRemark(remark);
        Observable.just(updateItem)
                .doOnNext(item -> updateItemUseCase.invoke(item))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetItem>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull GetItem item) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void editRemark(String remark) {
        this.remark = remark;
        editRemarkSubject.onNext(remark);
    }
}