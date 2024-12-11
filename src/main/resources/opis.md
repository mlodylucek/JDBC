# Aplikacja serwis

## Aktorzy i przypadki użycia

Kasjer:
1. przyjęcie sprzetu do naprawy (stworzenie zlecenia)
2. wyszukanie zlecenia
3. wydanie sprzętu (naprawionego lub wymienionego)
4. wysyłka sprzętu ????
5. przyjęcie płatności
6. zwrot płatności

Serwisant:
1. Dokonanie naprawy sprzętu
2. Dokonanie wymiany sprzętu
3. Odmowa naprawy
4. Wyświetlenie listy zleceń do wykonania 

## Dekompozycja

Uproszczony model, wymaganych operacji zawiera 5 encji:
* **Customer** - reprezentacji klienta korzystającego z serwisu,
* **Address** - reprezentacja adresu klienta (któy może zostać użyty to wysyłki),
* **Hardware** - reprezentacja serwisowanego sprzetu,
* **Payment** - reprezentacja płatności dokonanej za naprawę bądź zwrot,
* **Order** - reprezentacja zlecenia usługi naprawy.

Rola Kasjer reprezentowana przez interfejs `HelpdeskWorker`

Rola Serwisant reprezentowana jest przez interfejs `ServiceEngineer`

