import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class Hello {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello World");
		 Flowable.just("Hello world").subscribe(System.out::println);
		 
		 Flowable<Integer> flow = Flowable.range(1, 5)
				 .map(v -> v* v)
				 .filter(v -> v % 3 == 0);
		 flow.subscribe(System.out::println);
		 
		 //----------------------
		 
		 Observable.create(emitter -> {
		     while (!emitter.isDisposed()) {
		         long time = System.currentTimeMillis();
		         emitter.onNext(time);
		         if (time % 2 != 0) {
		             emitter.onError(new IllegalStateException("Odd millisecond!"));
		             break;
		         }
		     }
		})
		.subscribe(System.out::println, Throwable::printStackTrace);
		 
		 //-----------------------------
		 
		 Flowable.fromCallable(() -> {
			    Thread.sleep(1000); //  imitate expensive computation
			    return "Done";
			})
			  .subscribeOn(Schedulers.io())
			  .observeOn(Schedulers.single())
			  .subscribe(System.out::println, Throwable::printStackTrace);

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // <--- wait for the flow to finish
	}

}
