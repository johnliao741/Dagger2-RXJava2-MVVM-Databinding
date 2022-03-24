package interview.exam.dependency_injection.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.Map;

import javax.inject.Provider;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import interview.exam.data.local.dao.RoomDao;
import interview.exam.data.repository.MyRepository;
import interview.exam.data.service.MyService;
import interview.exam.util.ViewModelFactory;

@Module(includes = {RoomModule.class})
public class RepositoryModule {
    @Provides
    @Singleton
    MyRepository provideMyRepository(MyService service, RoomDao dao){
        return new MyRepository(service,dao);
    }

}
