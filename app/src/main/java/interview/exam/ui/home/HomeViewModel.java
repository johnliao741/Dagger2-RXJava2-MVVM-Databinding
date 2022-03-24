package interview.exam.ui.home;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import javax.inject.Inject;

import interview.exam.data.local.entity.GetItem;
import interview.exam.usecase.GetItemsUseCase;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;
import io.reactivex.subjects.Subject;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

public class HomeViewModel extends ViewModel {
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Inject
    public HomeViewModel(
            GetItemsUseCase getItemsUseCase
    ) {
        this.getItemsUseCase = getItemsUseCase;
        searchIdSubject.onNext("");
        itemsSubject.onNext(new ArrayList<>());
    }
    private GetItemsUseCase getItemsUseCase;
    public Subject<String> searchIdSubject = ReplaySubject.createWithSize(1);
    public Subject<List<GetItem>> itemsSubject = ReplaySubject.createWithSize(1);
    public Observable<List<GetItem>> itemsObservable =
            Observable.combineLatest(searchIdSubject,
                    itemsSubject,
                    (search, getItems) -> {
                        ArrayList<GetItem> currentItems = new ArrayList();
                        if(search == null || search.isEmpty()){
                            return getItems;
                        }else {
                            for (int i = 0; i < getItems.size(); i++) {
                                GetItem item = getItems.get(i);
                                if(item.getId().contains(search)){
                                    currentItems.add(item);
                                }
                            }
                        }
                        return currentItems;
                    });

    public LiveData<List<GetItem>> items =
            LiveDataReactiveStreams.fromPublisher(itemsObservable.toFlowable(BackpressureStrategy.LATEST));

    public void getItems() {
        getItemsUseCase.invoke(null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<GetItem>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<GetItem> getItems) {
                        itemsSubject.onNext(getItems);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void searchItemById(String newText) {
        searchIdSubject.onNext(newText);
    }
}