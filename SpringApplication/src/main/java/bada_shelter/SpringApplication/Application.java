package bada_shelter.SpringApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//TODO: Strona z listą zwierząt - ładna - można zrobić podgląd po najechaniu na stronę zwierzęcia jak się uda
//TODO: Zmienić przycisk do rozwijania na telefonie - gdy za mało miejsca np w nagłówku
//TODO: Uzupełnić tabelę zwierzęta w bazie - zdjęcia, opisy
//TODO: Obsługa adoptujących - klasa i w zwierzętach
//TODO: Wyszukiwarka - po wyborze rasy psa powinny być tylko rasy psów.
//TODO: Strona admina - zarządzanie zwierzętami i pracownikami
//TODO: Strona pracownika - zarządzanie zwierzętami
//TODO: Elastyczność CSS - osobny css dla pracowników i głównej oraz wspólny css do wspólnych cech
//TODO: Obsługa błędów
//TODO: Strona zwierzęcia - dodać opis i sprawdzić na przykładzie jak działa, KASTRACJA - T zamienić na tak N na nie w szczegółach
//TODO: Strona główna - jeśli użytkownik jest zalogowany to możliwość wylogowania i przejścia do strony pracownika z menu rozwijanego z przycisku ,,zalogowano jako xyz"
//TODO: Strona admina - rozwijanie do sidebaru
//TODO: Po opcji wyszukaj dodać adminowi możliwość modyfikacji
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
