# Reactive Programming

## Intro
Intro till reaktiv programmering och RxJava.

### Vad
Reaktiv programmering är en paradigm som kretsar kring fortplantingen av förändring.
Dvs.
Om ett program fortplantar alla förändingar som modifierar dess data till berörda parter kan programmet kallas reaktivt.

`a = b + c`

Vad händer när `b` uppdateras? Skillnaden mellan imperativ och reaktiv programmering.

### Varför
Reaktiv programmering möjligggör, gör det lättare att XXX.


#### Reactive system
- Modular/Dynamic
- Scalable
- Fault-tolerent
- Responsive

Ni kanske har hört om [The Reactive Manifesto](http://www.reactivemanifesto.org) som definierar dessa fyra principer.

### RxJava
RxJava är ett Java-bibliotek som tillhandahåller komponenter för att skriva reaktiv kod i Java.


- Synkront
    - ett värde: T getData()
    - flera värden: Iterable<T> getData()
- Asynkront
    - ett värde: Future<T> getData()
    - flera värden: __Observable<T> getData()__ (rx.Observable)

Asynkront accessa en sekvens av värden.

### Iterator pattern vs Observable
Vid andvändning av en iterator drar (pull) du värden från iteratorn och blockerar på hasNext()/next().
En Observable ger trycks (push) värden ut när de finns tillgängliga.


### Hot & Cold Observables
XXX


<---------------------->
# Transformations
- flatMap vs concatMap
- switchMap
- groupBy

# Accumulating
- scan


# combining
- combineLatest
- concat
- amb
- zipWith
- mergeWith
takeUntil
takeWhile
skipUntil
skipWhile

## Error Handling
TODO