package bada_shelter.SpringApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//TODO: Strona z listą zwierząt - ładna - można zrobić podgląd po najechaniu na stronę zwierzęcia jak się uda
//TODO: Zmienić przycisk do rozwijania na telefonie - gdy za mało miejsca np w nagłówku
//TODO: Uzupełnić tabelę zwierzęta w bazie - zdjęcia, opisy
//TODO: Obsługa adoptujących - klasa i w zwierzętach
//TODO: Wyszukiwarka - po wyborze rasy psa powinny być tylko rasy psów.
//TODO: Strona admina - zarządzanie zwierzętami i adopcjami
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
