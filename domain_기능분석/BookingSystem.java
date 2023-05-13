/*
 * Restaurant Booking System: example code to accompany
 *
 * "Practical Object-oriented Design with UML"
 * Mark Priestley
 * McGraw-Hill (2004)
 */

package booksys.application.domain ;

import java.sql.Date ;
import java.sql.Time ;
import java.util.* ;

//레스토랑에서 테이블 예약을 처리하는 시스템에 대한 구현

public class BookingSystem
{
  // Attributes:

  Date currentDate ; //현재 사용자의 날짜
  Date today ;  
  
  // Associations:

  Restaurant restaurant = null ;  //예약 시스템과 관련된 레스토랑 객체
  Vector currentBookings ; //현재 존재하는 예약들의 벡터
  Booking selectedBooking ; //현재 선택된 예약 객체

  // Singleton:
  
  private static BookingSystem uniqueInstance ;

  public static BookingSystem getInstance()//정적 메서드 BookingSystem클래스의 고유한(unique)인스턴스를 반환하는 정적 메서드.
  {
    if (uniqueInstance == null) {
      uniqueInstance = new BookingSystem() ;
    }
    return uniqueInstance ;
  }

  private BookingSystem()//BookingSysytem클래스의 생성자. 현재 Date타입(today)과 새로운 restaurant객체를 생성한다.
  {
    today = new Date(Calendar.getInstance().getTimeInMillis()) ;
    restaurant = new Restaurant() ;
  }

  // Observer: this is `Subject/ConcreteSubject'



  //여기는 무슨 말인지 모르겠어요...ㅠㅠ

  Vector observers = new Vector() ;

  public void addObserver(BookingObserver o)
  {
    observers.addElement(o) ;
  }
  
  public void notifyObservers()
  {
    Enumeration enum = observers.elements() ;
    while (enum.hasMoreElements()) {
      BookingObserver bo = (BookingObserver) enum.nextElement() ;
      bo.update() ;
    }
  }

  public boolean observerMessage(String message, boolean confirm)
  {
    BookingObserver bo = (BookingObserver) observers.elementAt(0) ;
    return bo.message(message, confirm) ;
  }
  
  // System messages:

  public void display(Date date)
  {
    currentDate = date ;
    currentBookings = restaurant.getBookings(currentDate) ;
    selectedBooking = null ;
    notifyObservers() ;
  }
  
  public void makeReservation(int covers, Date date, Time time, int tno,
			      String name, String phone)
  {
    if (!doubleBooked(time, tno, null) && !overflow(tno, covers)) {
      Booking b
	= restaurant.makeReservation(covers, date, time, tno, name, phone) ;
      currentBookings.addElement(b) ;
      notifyObservers() ;
    }
  }
 
  public void makeWalkIn(int covers, Date date, Time time, int tno)
  {
    if (!doubleBooked(time, tno, null) && !overflow(tno, covers)) {
      Booking b = restaurant.makeWalkIn(covers, date, time, tno) ;
      currentBookings.addElement(b) ;
      notifyObservers() ;
    }
  }
  
  public void selectBooking(int tno, Time time)
  {
    selectedBooking = null ;
    Enumeration enum = currentBookings.elements() ;
    while (enum.hasMoreElements()) {
      Booking b = (Booking) enum.nextElement() ;
      if (b.getTableNumber() == tno) {
	if (b.getTime().before(time)
	    && b.getEndTime().after(time)) {
	  selectedBooking = b ;
	}
      }
    }
    notifyObservers() ;
  }

  public void cancel()
  {
    if (selectedBooking != null) {
      if (observerMessage("Are you sure?", true)) {
	currentBookings.remove(selectedBooking) ;
	restaurant.removeBooking(selectedBooking) ;
	selectedBooking = null ;
	notifyObservers() ;
      }
    }
  }
  
  public void recordArrival(Time time)
  {
    if (selectedBooking != null) {
      if (selectedBooking.getArrivalTime() != null) {
	observerMessage("Arrival already recorded", false) ;
      }
      else {
	selectedBooking.setArrivalTime(time) ;
	restaurant.updateBooking(selectedBooking) ;
	notifyObservers() ;
      }
    }
  }

  public void transfer(Time time, int tno)
  {
    if (selectedBooking != null) {
      if (selectedBooking.getTableNumber() != tno) {
	if (!doubleBooked(selectedBooking.getTime(), tno, selectedBooking)
	    && !overflow(tno, selectedBooking.getCovers())) {
	  selectedBooking.setTable(restaurant.getTable(tno)) ;
	  restaurant.updateBooking(selectedBooking) ;
	}
      }
      notifyObservers() ;
    }
  }
  
  private boolean doubleBooked(Time startTime, int tno, Booking ignore)
  {
    boolean doubleBooked = false ;

    Time endTime = (Time) startTime.clone() ;
    endTime.setHours(endTime.getHours() + 2) ;
    
    Enumeration enum = currentBookings.elements() ;
    while (!doubleBooked && enum.hasMoreElements()) {
      Booking b = (Booking) enum.nextElement() ;
      if (b != ignore && b.getTableNumber() == tno
	  && startTime.before(b.getEndTime())
	  && endTime.after(b.getTime())) {
	doubleBooked = true ;
	observerMessage("Double booking!", false) ;
      }
    }
    return doubleBooked ;
  }
  
  private boolean overflow(int tno, int covers)
  {
    boolean overflow = false ;
    Table t = restaurant.getTable(tno) ;
      
    if (t.getPlaces() < covers) {
      overflow = !observerMessage("Ok to overfill table?", true) ;
    }
    
    return overflow ;
  }
  
  // Other Operations:

  public Date getCurrentDate()
  {
    return currentDate ;
  }
  
  public Enumeration getBookings()
  {
    return currentBookings.elements() ;
  }

  public Booking getSelectedBooking()
  {
    return selectedBooking ;
  }

  public static Vector getTableNumbers()
  {
    return Restaurant.getTableNumbers() ;
  }
}
