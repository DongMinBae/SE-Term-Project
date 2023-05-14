/*
 * Restaurant Booking System: example code to accompany
 *
 * "Practical Object-oriented Design with UML"
 * Mark Priestley
 * McGraw-Hill (2004)
 */

package booksys.application.persistency ;

import booksys.application.domain.* ;

class PersistentReservation
  extends Reservation implements PersistentBooking
{


  private int oid ;

  //영구 식별자를 나타내는 정수 id, 예약 고객 수를 나타내는 정수 c, 에약날짜를 나타내는 d 예약 시간을 나타내는 t,
  //Table객체, 고객 객체, 도착 시간을 나타내는 arr 6개를 인수로 생성자를 생성한다.
  public PersistentReservation(int id, int c, java.sql.Date d, java.sql.Time t,
			       Table tab, Customer cust, java.sql.Time arr)
  {
    super(c, d, t, tab, cust, arr) ;
    oid = id ;
  }

  /* public because getId defined in an interface and hence public */
  
  // 영구 예약 객체의 고유 식별자인 oid 인스턴스 변수의 값을 반환한다.
  public int getId() {
    return oid ;
  }
}
