package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner; 
import java.util.Date;
import model.entities.Reservation;

public class Program {

	public static void main(String[] args) throws ParseException {
		
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		System.out.printf("Room number: ");
		int number = sc.nextInt();
		System.out.print("Check-in date (dd/MM/yyyy): ");
		Date checkIn = sdf.parse(sc.next());
		System.out.print("Check-out date (dd/MM/yyyy): ");
		Date checkOut = sdf.parse(sc.next());
		
		// Verifica se data de saída é anterior a data de entrada
		if (!checkOut.after(checkIn)) {
			System.out.println("Error in reservation: Check-out date must be after check-in date");
		} else {
			Reservation reservation = new Reservation(number, checkIn, checkOut);
			System.out.println("Reservation: " + reservation);
			System.out.println();
			System.out.println("Enter data to update the reservation:");
			System.out.print("Check-in date (dd/MM/yyyy): ");
			checkIn = sdf.parse(sc.next());
			System.out.print("Check-out date (dd/MM/yyyy): ");
			checkOut = sdf.parse(sc.next());
			
			String error = reservation.updateDates(checkIn, checkOut); // String error vai mostrar pra mim se retornou erro ou não!
			// Criando a lógica no programa principal (solução muito ruim)
	
			
			if(error != null) {
				System.out.println("Error in reservation: " + error);
			} else { 	
			System.out.println("Reservation: " + reservation);
			}
			
		
		}
		
		
		
		sc.close();
	}

}
