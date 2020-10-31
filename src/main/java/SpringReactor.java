import java.util.Arrays;
import java.util.List;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class SpringReactor {
	
	public static void main(String args[]) {

		Flux<String> seq1 = Flux.just("foo", "bar", "foobar");
		seq1.subscribe(i -> { System.out.println("Flux Just example :"+i);});

		List<String> iterable = Arrays.asList("foo", "bar", "foobar");
		Flux<String> seq2 = Flux.fromIterable(iterable);
		seq2.subscribe(i -> { System.out.println("Flux from iterable example :"+i);});
		
		Mono<String> noData = Mono.empty(); 
		noData.subscribe(i ->{System.out.println("mono emty");},error -> {},() -> {System.out.println("mono with empty completed");});
		

		Mono<String> data = Mono.just("foo");
		data.subscribe(i ->{System.out.println("mono just"+i);},error -> {},() -> {System.out.println("mono just completed");});
		
		Flux<Integer> numbersFromFiveToSeven = Flux.range(5, 3);
		numbersFromFiveToSeven.subscribe(i ->{System.out.println("Flux range"+i);},error -> {},() -> {System.out.println("flux range completed");});
		
		Flux<Integer> ints = Flux.range(1, 3); 
		ints.subscribe();
		
		
		Flux<Integer> intsVisible = Flux.range(1, 3); 
		intsVisible.subscribe(i -> System.out.println(i));
		
		Flux<Integer> intsError = Flux.range(1, 4) 
			      .map(i -> { 
			        if (i <= 3) return i; 
			        throw new RuntimeException("Got to 4"); 
			      });
		intsError.subscribe(i -> System.out.println(i), 
			      error -> System.err.println("Error: " + error));
		
		Flux<Integer> intsCom = Flux.range(1, 4); 
		intsCom.subscribe(i -> System.out.println(i),
		    error -> System.err.println("Error " + error),
		    () -> {System.out.println("Done");});
		
		
		}

	
}
