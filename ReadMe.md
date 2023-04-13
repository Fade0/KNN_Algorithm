# KNN 

Program powinien pobierać argumenty k, train_file, test_file, gdzie:

- k - liczba najblizszych sąsiadów
- train_file - scieżka do pliku ze zbiorem treningowym
- test file - ścieżka do pliku ze zbiorem testowym

Program powinien wypisać każdą obserwację ze zbioru testowego (wektor wejściowy i poprawną klasę) oraz przewidzianą przez algorytm kalsę dla tej obserwacji.
W ostatniej linii program powinien wyświetlić dokładność czyli procent poprawnych odpowiedzi.

-------------------------
### Przykład

1.2, 2.2, 3.1, 4.1, iris-virginica | predicted: iris-virginica |
1.2, 0.2, 3.1, 0.0, iris-setosa    | predicted: iris-virginica |
0.3, 0.1, 1.1, 2.0, iris-setosa     predicted: iris-setosa
0.3, 0.1, 1.3, 2.1, iris-setosa     predicted: iris-setosa

Dokładność: 75.0 %

| 1.2, 2.2, 3.1, 4.1, iris-virginica | predicted: iris-virginica |
|------------------------------------|--------------------------:|
| 1.2, 0.2, 3.1, 0.0, iris-setosa    | predicted: iris-virginica |
| 0.3, 0.1, 1.1, 2.0, iris-setosa    |    predicted: iris-setosa |
| 0.3, 0.1, 1.3, 2.1, iris-setosa    |  predicted: iris-setosa   |


--------------------------------


Zapewnij interfejs użytkownika, może być tekstowy, tak aby można było wybrać :
- czy wpisać własną obserwację i uzyskać przewidywaną klasę na podstawie zbioru treningowego ( nie można dodawać do pliku ze zbiorem testowym)
- zmienić k
- zakończyć program

Program powinien być uniwersalny, to znaczy działać na każdych danych, o każdym wymiarze, jeśli atrybuty są numeryczne z wyłączeniem klasy decyzyjnej.

Zewnętrzne biblioteki nie są dozwolone dla implementacji części matematycznejalgorytmu. Jednak jeśli potrzeba można użyć takich do wczytania danych, transformacji czy wstępnego przetwarzania.
