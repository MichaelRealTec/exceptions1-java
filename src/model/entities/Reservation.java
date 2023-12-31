package model.entities;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exceptions.DomainException;

public class Reservation {
	// Atributos
	private Integer roomNumber;
	private Date checkIn, checkOut;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	// Métodos Getters e Setters
	
	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}
	
	public Integer getRoomNumber() {
		return roomNumber;
	}
	/*
	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}
	*/
	public Date getCheckOut() {
		return checkOut;
	}
	/*
	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}
	*/
	public Date getCheckIn() {
		return checkIn;
	}
	
	// Métodos Construtores
	public Reservation(Integer roomNumber, Date checkIn, Date checkOut)  {
		if (!checkOut.after(checkIn)) {
			throw new DomainException("Check-out date must be after check-in date");
		}
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}
		
	// Métodos
	// Criando um método para calcular a duração dos dias
	public long duration() {
		// Calculando a diferença entre duas datas (milisegundos)
		long diff = checkOut.getTime() - checkIn.getTime(); // pega a diferença entre as duas datas em milisegundos
		// converter de milisegundos para dias (TimeUnit) tipo enumerado complexo
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS); // Converte o valor diff de milesegundos para dias	
	}
	
	// Recebe duas datas novas e atualiza o checkin e checkout 
	public void updateDates(Date checkIn, Date checkOut) {
		// Lógica de atualização
		Date now = new Date();
		if (checkIn.before(now) || checkOut.before(now)) {
			throw new DomainException("Reservation dates for updates must be future dates");
		} if (!checkOut.after(checkIn)) {
			throw new DomainException("Check-out date must be after check-in date");
		}
		// Se passar da lógica atualiza o checkIn e checkOut
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		 
	}
	
	// Imprimir no formato desejado
	@Override
	public String toString() {
		return "Room "
				+ roomNumber
				+ ", check-in: "
				+ sdf.format(checkIn)
				+ ", check-out: "
				+ sdf.format(checkOut)
				+ ", "
				+ duration()
				+ " nights";
	}
}
