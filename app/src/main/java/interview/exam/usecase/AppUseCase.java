package interview.exam.usecase;

public abstract class AppUseCase<T,R> {
    abstract R invoke(T request);
}
