# Java w zastosowaniach produkcyjnych

## Zadanie 6 - Generator transakcji - logowanie

Do aplikacji "Generator transakcji" z poprzednich zajęć, należy dodać logowanie za pomocą Log4J2 lub Logbacka.

Logowanie na konsolę powinno być sformatowane JSonem.

Logowanie do pliku - tekst, z datą/godziną, wątkiem, poziomem logowania oraz wiadomością.

Plik powinien być "rolowany" co 10MB. Poprzedni plik powinien być zarchiwizowany z dopisaniem daty/godziny.

Przykładowe wywołanie:

- java -jar transaction-generator.jar -customerIds 1:20 -dateRange "2018-03-08T00:00:00.000-0100":"2018-03-08T23:59:59.999-0100" -itemsFile items.csv -itemsCount 5:15 -itemsQuantity 1:30 -eventsCount 1000 -outDir ./output

Parametry:

- customerIds: zakres, z jakiego będą generowane wartości do pola "customer_id". Domyślny 1:20

- dateRange: zakres dat, z jakiego będzie generowane pole "timestamp". Domyślny dzień uruchomienia, cała doba

- itemsFile: nazwa pliku csv zawierającego spis produktów. Przykładowy plik (items.csv) dołączony do zadania.

- itemsCount: zakres ilości elementów generowanych w tablicy "items". Domyślny 1:5

- itemsQuantity: zakres z jakiego będzie generowana ilość kupionych produktów danego typu (pole "quantity"). Domyślnie 1:5

- eventsCount: ilość transakcji (pojedynczych plików) do wygenerowania. Domyślnie 100.

- outDir: katalog, do którego mają być zapisane pliki. Domyślnie aktualny katalog roboczy.
